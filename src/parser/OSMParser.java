package parser;

//@author Sandeep Sasidharan

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;

import model.Model;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class OSMParser {
	
	static final String FILE_LOC = "C:\\Users\\Jolo Simeon\\Desktop\\smallest.osm";
	ArrayList<GraphNode> nodes;
	ArrayList<DirectedEdge> edges;
	RoadGraph g = new RoadGraph();
	
	public static void main(String[] args) throws FileNotFoundException, IOException, XmlPullParserException {
		new OSMParser();

	}
	
	public OSMParser () throws FileNotFoundException, IOException, XmlPullParserException{
		System.out.println("Run started at"+ LocalDateTime.now() );
		XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
		factory.setNamespaceAware(true);
		XmlPullParser xpp = factory.newPullParser();
		xpp.setInput ( new FileReader (FILE_LOC));
 
		g.osmGraphParser(xpp);
		nodes = g.nodes;
		edges = g.edges;
		
		System.out.println("Adding to database now");
		for (int i = 0; i < nodes.size(); i ++) {
			System.out.println("Adding node " + i + " out of " + nodes.size() + " to database");
			Model.addNode((GraphNode) nodes.get(i));
		}
		for (int i = 0; i < edges.size(); i ++) {
			System.out.println("Adding edge " + i + " out of " + edges.size() + " to database");
			Model.addEdge((DirectedEdge) edges.get(i));
		}
		System.out.println("Parsing and storing ended at"+ LocalDateTime.now() );
		System.out.println("Edges = "+edges.size());
		System.out.println("Nodes = "+nodes.size());
	}
	
	public ArrayList<GraphNode> getNodes() {
		return nodes;
	}
	public void setNodes( ArrayList<GraphNode> nodes) {
		this.nodes = nodes;
	}
	public  ArrayList<DirectedEdge> getEdges() {
		return edges;
	}
	public void setEdges( ArrayList<DirectedEdge> edges) {
		this.edges = edges;
	}
	public RoadGraph getRoadGraph() {
		return g;
	}
	public void setG(RoadGraph g) {
		this.g = g;
	}
	
}
