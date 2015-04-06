package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
		
		EditText date = (EditText) findViewById(R.id.newDate);
		String rDate = date.getText().toString(); // convert to string
		
		EditText distance = (EditText) findViewById(R.id.newDistance);
		Double distanceD = Double.parseDouble(distance.getText().toString()); // convert to double
			
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
				Toast.makeText(Add.this, "Please select Co2 Band", Toast.LENGTH_SHORT).show();
				
			}
		});
		
		// save new route to array
		Route r = new Route(rDate, distanceD, rCo2Band);
		routeList.add(r);
		goToActivity(this, Home.class, null);
		
	}

	

}
