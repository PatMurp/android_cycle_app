package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class Totals extends Base {
	private double tDistance = 0.0;
	private double tCo2Saving = 0.0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_totals);
		
		// itterate through route array
		for (Route route: routeList) {
			tDistance += route.getDistance(); // total distance
		}
		
		TextView totalDistance = (TextView) findViewById(R.id.totalDistance);
		totalDistance.setText(String.format("%.02f km", tDistance));
		
	}
	
	
	


}


