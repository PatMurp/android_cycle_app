package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import wit.cc.models.Calc;
import android.os.Bundle;
import android.widget.TextView;

public class Totals extends Base {
	private double tDistance = 0.0;
	private double tCo2Saving = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_totals);
		
		// Iterate through route array
		for (Route route: routeList) {
			tDistance += route.getDistance(); // total distance
			tCo2Saving += (route.getDistance() * Calc.emissionForBand(route.getCo2band())/ 1000); // total CO2 savings
		}
		
		TextView totalDistance = (TextView) findViewById(R.id.totalDistance);
		totalDistance.setText(String.format("%.02f km", tDistance)); // set total distance
		
		TextView totalCo2 = (TextView) findViewById(R.id.totalCo2);
		totalCo2.setText(String.format("%.03f kg", tCo2Saving));
		
	}
	
	
	


}


