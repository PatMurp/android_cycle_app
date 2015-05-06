package wit.cc.activities;

import wit.cc.R;
import wit.cc.custom.CustomCo2BandsSelectedListener;
import wit.cc.models.Route;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Add extends Base {
	Spinner bandSelecter;
	private Button addRoute;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);
		
		addListenerButton();
		addListenerOnSpinneritemSelection();
	}
	
	// spinner listener
	public void addListenerOnSpinneritemSelection() {
		bandSelecter = (Spinner) findViewById(R.id.chooseCo2Band);
		bandSelecter.setOnItemSelectedListener(new CustomCo2BandsSelectedListener());
		bandSelecter.setSelection(6); // default value C
	}
	
	public void addListenerButton() {
		
		bandSelecter = (Spinner) findViewById(R.id.chooseCo2Band);
		addRoute = (Button) findViewById(R.id.addNewRouteBtn);
		
		addRoute.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String rDate = getEditString(R.id.newDate);
				double rDistance;
				
				try { // catch numberFormat exceptions
					rDistance = getEditDouble(R.id.newDistance);
				} catch (NumberFormatException e) {
					rDistance = 0.0;
				}
				
				String rCo2Band = String.valueOf(bandSelecter.getSelectedItem());
				
				// check for empty fields
				if ((rDate.length() > 0) && (getEditString(R.id.newDistance).length() > 0)) {
					
					// save new route to array
					Route r = new Route(rDate, rDistance, rCo2Band);
					//routeList.add(r);
					dbManager.insert(r);
					goToActivity(Add.this, Home.class, null);
				} else {
					Toast.makeText(Add.this, "Please enter value's for Date and Distance", Toast.LENGTH_SHORT).show();
				}
				
			}
		});
	}
}
