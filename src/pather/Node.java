package pather;

public class Node {
	double lat;
	double lon;
	double g;
	long id;
	transient Node parent;
	
	public Node() {
		id = 0;
		lat = 0;
		lon = 0;
		g = 0;
		parent = null;
	}
	
	public Node(double lati, double longi) {
		this.id = 0;
		this.lat = lati;
		this.lon = longi;
		g = 0;
		parent = null;
	}
	
	public Node(long id, double lati, double longi) {
		this.id = id;
		this.lat = lati;
		this.lon = longi;
		g = 0;
		parent = null;
	}
	
	public Node(Node node, double g) {
		this.lat = node.getLat();
		this.lon = node.getLon();
		this.g += g;
		parent = null;
	}
	
	public double getLat() {
		return lat;
	}
	
	public double getLon() {
		return lon;
	}
	
	public void setLati(double lat) {
		this.lat = lat;
	}
	
	public void setLongi(double lon) {
		this.lon = lon;
	}
	
	public Node getParent() {
		return parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setG(double g) {
		this.g = g;
	}
	
	public double getG() {
		return g;
	}
	
	public float getCost(Node to) {
		return (float) (g + getDistance(this, to));
	}
	
	public double getDistance(Node start, Node end) {
		// Haversine formula 
		  int R = 6371; // Radius of the earth in km
		  double dLat = deg2rad(end.getLat() - start.getLat());  // deg2rad below
		  double dLon = deg2rad(end.getLon() - start.getLon()); 
		  double a = 
		    Math.sin(dLat/2) * Math.sin(dLat/2) +
		    Math.cos(deg2rad(start.getLat())) * Math.cos(deg2rad(start.getLat())) * 
		    Math.sin(dLon/2) * Math.sin(dLon/2)
		    ; 
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a)); 
		  double d = R * c; // Distance in km
		  return d;
	}
	
	public double deg2rad(double deg) {
		  return deg * (3.14/180);
	}
	
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
}	
