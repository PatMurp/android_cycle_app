package wit.cc.models;

public class Route {
	
	private String date;
	private double distance;
	private String co2band;
	
	public Route() {}

	public Route(String date, double distance, String co2band) {
		super();
		this.date = date;
		this.distance = distance;
		this.setCo2band(co2band);
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
