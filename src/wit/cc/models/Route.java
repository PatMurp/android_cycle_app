package wit.cc.models;

public class Route {
	
	private static int autoid = 1;
	private int routeId;
	private String date;
	private double distance;
	private String co2band;
	
	public Route() {}

	public Route(String date, double distance, String co2band) {
		super();
		this.routeId = autoid++;
		this.date = date;
		this.distance = distance;
		this.co2band = co2band;
	}
	
	

	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public String getCo2band() {
		return co2band;
	}

	public void setCo2band(String co2band) {
		this.co2band = co2band;
	}

	@Override
	public String toString() {
		return "Route [date=" + date + ", distance=" + distance + ", co2band="
				+ co2band + "]";
	}

	
	
	
}
