package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Add extends Base {

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
		
		EditText co2Band = (EditText) findViewById(R.id.newCo2Band);
		String rCo2Band = co2Band.getText().toString(); // convert to string
		
		Route r = new Route(rDate, distanceD, rCo2Band);
		routeList.add(r);
		goToActivity(this, Home.class, null);
		
	}

}
