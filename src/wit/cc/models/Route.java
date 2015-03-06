package wit.cc.models;

public class Route {
	
	private String date;
	private double distance;
	
	public Route() {}

	public Route(String date, double distance) {
		super();
		this.date = date;
		this.distance = distance;
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

	@Override
	public String toString() {
		return "Route [date=" + date + ", distance=" + distance + "]";
	}
	
	

}
