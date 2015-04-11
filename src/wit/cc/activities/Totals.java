package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import wit.cc.models.Calc;
import android.os.Bundle;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.SeekBar;
import android.widget.TextView;

public class Totals extends Base {
	private double tDistance = 0.0; // total distance
	private double tCo2Saving = 0.0;// total co2 saving kg's
	private double tValue = 0.0;// total carbon credit value of co2 saved
	private double ccValue = 7; //  value of carbon credits set with slider; default value €7/tonne
	private TextView totalValue; // display total value of carbon credit savings
	private TextView co2CreditValue; // display carbon credit value
	
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
		totalCo2.setText(String.format("%.03f kg", tCo2Saving)); // set total co2 savings
		
		totalValue = (TextView) findViewById(R.id.totalValue);
		tValue = Calc.calcCo2Value(tCo2Saving, ccValue); // calculate total value
		totalValue.setText(String.format("€%.02f", tValue)); // set total value
		
		co2CreditValue = (TextView) findViewById(R.id.ccValue);
		co2CreditValue.setText(String.format("€%.02f/tonne", ccValue));
		
		SeekBar seekbar = (SeekBar) findViewById(R.id.seekBarValue);
		seekbar.setOnSeekBarChangeListener(slider); // get slider value
		
	}
	
	// update values when slider moved
	private void updateCustom() {
		
		// set cc value text to match position on slider
		co2CreditValue.setText(String.format("€%.02f", ccValue));
		
		// calculate updated total value
		tValue = Calc.calcCo2Value(tCo2Saving, ccValue); 
		 
		// display carbon credit value and total value
		co2CreditValue.setText(String.format("€%.02f/tonne", ccValue));
		totalValue.setText(String.format("€%.02f", tValue));
		
	}
	
	// call when user moves slider
	private OnSeekBarChangeListener slider = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			ccValue = seekBar.getProgress();
			updateCustom(); // update cc value and total
		}
	};
}


