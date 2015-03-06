package wit.cc.models;

import java.util.ArrayList;
import java.util.List;

public class RouteData {
	
	// array of routes
	private List<Route> routes = new ArrayList<Route>();
	public List<Route> getRoutes() {
		return routes;
	}
	
	public RouteData() {
		routes.add(new Route("03-01-15", 8.5));
		routes.add(new Route("05-01-15", 6));
		routes.add(new Route("07-01-15", 5.3));
	}
	
//	private void addItem(Route item) {
//		routes.add(item);
//	}

}
