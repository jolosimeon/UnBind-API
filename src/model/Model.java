package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import parser.DirectedEdge;
import parser.GraphNode;
import pather.Node;
import pather.Way;

import com.mysql.jdbc.Connection;


public class Model
{
	static ArrayList<Way> traffic = new ArrayList<>();
	static ArrayList<Way> flooded = new ArrayList<>();
	static int weather = 0;
	static int departure = 0;

	static ArrayList<Integer> speeds = new ArrayList<Integer>();

    private static DBConnection db;
    public static void addNode (GraphNode node) {
    	db = new DBConnection();
        java.sql.Connection connection = db.getConnection();
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;
            String query = "INSERT INTO nodes (id, lon, lat) VALUES (?, ?, ?)";
            pstmt = connection.prepareStatement( query );
            pstmt.setLong(1, node.getId());
            pstmt.setDouble( 2, node.getLon());
            pstmt.setDouble( 3, node.getLat());
            pstmt.executeUpdate();
            connection.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        db.close();
    }

    public static void addEdge (DirectedEdge edge) {
    	db = new DBConnection();
        java.sql.Connection connection = db.getConnection();
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;
            String query = "INSERT INTO edges_start (startnode, endnode, way_id, speedMax, length, name, isOneWay, normalweight, type) VALUES (?, ?, ?, ?, ?, ?, ? ,?, ?)";
            pstmt = connection.prepareStatement( query );
            pstmt.setLong(1, edge.from().getId());
            pstmt.setLong(2, edge.to().getId());
            pstmt.setLong(3, edge.getWayId());
            pstmt.setInt( 4, edge.getSpeedMax());
            pstmt.setDouble( 5, edge.getLength());
            pstmt.setString( 6, edge.getName());
            if (edge.isOneway())
            	pstmt.setInt( 7, 1);
            else
            	pstmt.setInt( 7, 0);
            pstmt.setDouble( 8, edge.getWeight());
            pstmt.setString( 9, edge.getType());
            pstmt.executeUpdate();
            connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
    }

    public static Node getNode (long id) {
    	Node node = null;
    	db = new DBConnection();
        java.sql.Connection connection = db.getConnection();
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;
            String query = "SELECT * FROM nodes WHERE id = ? ";
            pstmt = connection.prepareStatement( query );
			pstmt.setLong( 1, id);
			rs = pstmt.executeQuery();

            if (rs.next())
            {
                node = new Node(rs.getLong("id"), rs.getDouble("lat"), rs.getDouble("lon"));
            }
            connection.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
    	return node;
    }

    public static Node getNode (double lat, double lon) {
    	Node node = null;
    	db = new DBConnection();
        java.sql.Connection connection = db.getConnection();
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;
            String query = "SELECT * FROM nodes WHERE lat = ? AND lon =?";
            pstmt = connection.prepareStatement( query );
			pstmt.setDouble(1, lat);
			pstmt.setDouble( 2, lon);
			rs = pstmt.executeQuery();

            if (rs.next())
            {
                node = new Node(rs.getLong("id"), rs.getDouble("lat"), rs.getDouble("lon"));
            }
            connection.close();

        } catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
    	return node;
    }

    public static ArrayList<Node> getNeighbors (Node node) {
    	db = new DBConnection();
    	db.getConnection();
        java.sql.Connection connection = db.getConnection();
        ArrayList<Node> list = new ArrayList<>();
        try
        {
            ResultSet rs;
            PreparedStatement pstmt;
            String query = "SELECT startnode, endnode FROM edges_start WHERE startnode = ?";
            pstmt = connection.prepareStatement( query );
            pstmt.setLong(1, node.getId());
            rs = pstmt.executeQuery();

            while (rs.next())
            {
            	Node n = getNode(rs.getLong("endnode"));
            	n.setG(node.getG() + getTravelTime(new Way(node, n, 1)));
            	list.add(n);
            }
            query = "SELECT startnode, endnode FROM edges_end WHERE endnode = ?";
            pstmt = connection.prepareStatement( query );
            pstmt.setLong(1, node.getId());
            rs = pstmt.executeQuery();

            while (rs.next())
            {
            	Node n = getNode(rs.getLong("startnode"));
            	n.setG(node.getG() + getTravelTime(new Way(node, n, 1)));
            	list.add(n);
            }
            connection.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
        db.close();
		return list;
    }

    public static double getTravelTime(Way way) {
		int speed = 27;
		for (int i = 0; i < traffic.size(); i++) {
			if (traffic.get(i) == way) {
				speed = speeds.get(traffic.get(i).getTraffic_status());
			}
		}
		speed -= weather;

		return  (way.getDistance()/speed);
	}


}
