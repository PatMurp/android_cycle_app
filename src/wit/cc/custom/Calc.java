package wit.cc.custom;

public class Calc {
	
	// get route distance and car CO2 band and calculate saved Co2 emissions
	public static double calcCo2Emissions(double routeDistance, double carCo2Emissions) {
		double calculatedCo2Emissions = 0;
		
		calculatedCo2Emissions = routeDistance * carCo2Emissions; // grams CO2
		double kgCo2Emissions = calculatedCo2Emissions / 1000;  // kg's CO2
		double rounded = roundTo3decimalPlaces(kgCo2Emissions); // round to 3 decimal places
		return rounded;
	}
	
	// assign emission value for selected car band
	public static double emissionForBand(String band) {
		double emissions = 0.0;
		if (band.equals("A1")) {
			emissions = 40;
		}
		else if (band.equals("A2")) {
			emissions = 90;
		}
		else if (band.equals("A3")) {
			emissions = 100;
		}
		else if (band.equals("A4")) {
			emissions = 115;
		}
		else if (band.equals("B1")) {
			emissions = 125;
		}
		else if (band.equals("B2")) {
			emissions = 135;
		}
		else if (band.equals("C")) {
			emissions = 148;
		}
		else if (band.equals("D")) {
			emissions = 163;
		}
		else if (band.equals("E")) {
			emissions = 180;
		}
		else if (band.equals("F")) {
			emissions = 223;
		}
		else if (band.equals("G")) {
			emissions = 230;
		}
		else
			emissions = 0.0;
		return emissions;
	}
	
	// round to 3 decimal places
	public static double roundTo3decimalPlaces(double value) {
		double rounded = Math.round(value * 1000.0) / 1000.0;
		return rounded;
	}
	
	// round to 2 decimal places
	public static double roundto2DecimalPlaces(double value) {
		double rounded = Math.round(value * 100.0) / 100.0;
		return rounded;
	}
	
	// calculate value of saved co2
	public static double calcCo2Value(Double co2Total, Double value) {
		Double result = 0.0;
		Double tonnesCo2 = co2Total / 1000;
		result = tonnesCo2 * value;
		return result;
	}
}
