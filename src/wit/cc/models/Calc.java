package wit.cc.models;

public class Calc {
	
	// get route distance and car CO2 band and calculate saved C)2 emissions
	public static double calcCo2Emissions(double routeDistance, double carCo2Emissions) {
		double calculatedCo2Emissions = 0;
		carCo2Emissions = 90; // hard coded  value for testing only!!!
		
		calculatedCo2Emissions = routeDistance * carCo2Emissions; // grams CO2
		double kgCo2Emissions = calculatedCo2Emissions / 1000;  // kg's CO2
		double rounded = roundTo3decimalPlaces(kgCo2Emissions); // round to 3 decimal places
		return rounded;
	}
	
	// round to 3 decimal places
	private static double roundTo3decimalPlaces(double value) {
		double rounded = Math.round(value * 1000.0) / 1000.0;
		return rounded;
	}

}
