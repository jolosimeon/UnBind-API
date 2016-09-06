package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;
import parser.GraphNode;
import pather.AStarPather;
import pather.Node;

import com.google.gson.Gson;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;



/**
 * Servlet implementation class Route
 */
@WebServlet(
		description = "returns json of shortest path", 
		urlPatterns = { 
				"/Route", 
				"/route"
		})
public class Route extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Route() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		Gson g = new Gson();
		
		/*String place;
		OkHttpClient client = new OkHttpClient();
		HttpUrl.Builder urlBuilder = HttpUrl.parse("http://nominatim.openstreetmap.org/search.php").newBuilder();
		urlBuilder.addQueryParameter("q", place);
		urlBuilder.addQueryParameter("format", "json");
		String url = urlBuilder.build().toString();

		Request nomReq = new Request.Builder()
		                     .url(url)
		                     .build();
		
		Response nomResp = client.newCall(nomReq).execute();
		nomResp.body().string();
		
		urlBuilder = HttpUrl.parse("http://nominatim.openstreetmap.org/search.php").newBuilder();
		urlBuilder.addQueryParameter("q", place2);
		urlBuilder.addQueryParameter("format", "json");
		url = urlBuilder.build().toString();

		nomReq = new Request.Builder()
		                     .url(url)
		                     .build();
		
		nomResp = client.newCall(nomReq).execute();
		nomResp.body().string();
		*/
		Node start = new Node();
		start.setLati(Double.valueOf(request.getParameter("lat1")));
		start.setLongi(Double.valueOf(request.getParameter("lon1")));
		start = Model.getNode(start.getLat(), start.getLon());
		
		Node end = new Node();
		end.setLati(Double.valueOf(request.getParameter("lat2")));
		end.setLongi(Double.valueOf(request.getParameter("lon2")));
		end = Model.getNode(end.getLat(), end.getLon());
		
		AStarPather pather = new AStarPather();
		Node n = pather.calcPath(start, end);
		ArrayList<Node> results = new ArrayList<>();
		results.add(n);
		while (n.getParent() != null) {
            System.out.println("G " + n.getG());
            n = n.getParent();
            results.add(n);
        }
		Collections.reverse(results);
		
		String resultJson = g.toJson(results);

		// try printing this out to see the JSON string of studentList
				
		// Send the JSON string to the client who requested it
		response.getWriter().print(resultJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
