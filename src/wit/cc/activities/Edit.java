package wit.cc.activities;

import wit.cc.R;
import wit.cc.custom.CustomCo2BandsSelectedListener;
import wit.cc.models.Route;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Loader.ForceLoadContentObserver;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Edit extends Base {
	private Context context;
	private Route aRoute;
	// allowed co2 bands
	String[] validBands = { "A1", "A2", "A3", "A4", "B1", "B2", "C", "D", "E", "F", "G" };
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		
		activityInfo = getIntent().getExtras();
		aRoute = dbManager.get((activityInfo.getInt("routeID")));
		//aRoute = getRouteObject(activityInfo.getInt("routeID"));
		
		setContentView(R.layout.activity_edit);
		
		// assign values to edit layout
		setEditString(R.id.editDate, aRoute.getDate());
		setEditDouble(R.id.editDistance, aRoute.getDistance());
		setEditString(R.id.editBand, aRoute.getCo2band());
	}
	
	
	// get route object
//	private Route getRouteObject(int id) {
//		
//		for (Route r : routeList) {
//			if (r.getRouteId() == id)
//				return r;
//		}
//		return null;
//	}
//	
//	private int getRouteIndex(Route obj) {
//		
//		for (Route r : routeList) {
//			if (r.getRouteId() == obj.getRouteId()) {
//				return routeList.indexOf(r);
//			}
//		}
//		return -1;
//	}
	
	public void update(View v) {
		
		String routeDate = getEditString(R.id.editDate);
		double routeDistance;
		String routeCo2Band = getEditString(R.id.editBand);
		
		try { // catch numberFormat exceptions
			routeDistance  = getEditDouble(R.id.editDistance);
		} catch (NumberFormatException e) {
			routeDistance = 0.0;
		}
		
		
		// check for values in edit form
		if ((routeDate.length() > 0) && (getEditText(R.id.editDistance).length() > 0) 
				&& (routeCo2Band.length() > 0)) {
			
			// validate co2 bands
			if(isCo2BandValid(routeCo2Band) == false) {
				Toast.makeText(this, "Invalid co2 band", Toast.LENGTH_SHORT).show();
			} else {
				// reset values if changed
				aRoute.setDate(routeDate);
				aRoute.setDistance(routeDistance);
				aRoute.setCo2band(routeCo2Band.toUpperCase()); // set to uppercase
				
				// Update route data and go to home activity
				dbManager.update(aRoute);
				goToActivity(this, Home.class, activityInfo);
			}
			
		}
		else {
			Toast.makeText(this, "You must enter a value for Date, Distance. and C02Band", Toast.LENGTH_SHORT).show();
		}
	}
	
	// check if co2 band is valid
	private boolean isCo2BandValid(String value) {
		
		for (String  band : validBands) {
			if(value.equalsIgnoreCase(band)) {
				return true;
			}
		}
		return false;
	}
	
	

	
}
