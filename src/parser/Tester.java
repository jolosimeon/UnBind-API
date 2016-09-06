package parser;

import model.Model;

public class Tester {

	public static void main(String[] args) {
		GraphNode n1 = new GraphNode(14.16, 123.14, 1111111);
		GraphNode n2 = new GraphNode(15.156, 12978, 1111112);
		Model.addNode(n1);
		Model.addNode(n2);
		
		DirectedEdge e = new DirectedEdge(n1, n2, 120000.9, 70, true, "highway pre", "Highway to Hell", 1200, 1112);
		Model.addEdge(e);

	}

}
