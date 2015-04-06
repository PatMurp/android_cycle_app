package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Add extends Base {
	Spinner bandSelecter;
	ArrayAdapter<String> adapterBandSelected;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
	}
	
	public void addRoute(View v) {
			
		String rDate = getEditString(R.id.newDate);
		double rDistance;
		
		try { // catch numberFormat exceptions
			rDistance = getEditDouble(R.id.newDistance);
		} catch (NumberFormatException e) {
			rDistance = 0.0;
		}
		
		// initialize spinner
		bandSelecter = (Spinner) findViewById(R.id.chooseCo2Band);
		// get string value from spinner
		String rCo2Band = bandSelecter.getSelectedItem().toString();
		
		// initialize and set Adapter
		adapterBandSelected = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, R.array.co2Band_array);
		bandSelecter.setAdapter(adapterBandSelected);
		
		// band selected listener
		bandSelecter.setOnItemSelectedListener(new OnItemSelectedListener() {
			
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
			}
		});
		
		// check for empty fields
		if ((rDate.length() > 0) && (getEditString(R.id.newDistance).length() > 0)) {
			
			// save new route to array
			Route r = new Route(rDate, rDistance, rCo2Band);
			routeList.add(r);
			goToActivity(this, Home.class, null);
		} 
		else { 
			Toast.makeText(this, "Please enter value's for Date and Distance", Toast.LENGTH_SHORT).show();
			goToActivity(this, Add.class, null); // reload activity to display spinner options
		}
	}
}
