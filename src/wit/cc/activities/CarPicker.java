package wit.cc.activities;

import wit.cc.R;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

public class CarPicker extends Base {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_picker);
	
	}

	public void onCheckBoxClicked(View view) {
		
		// Is the view checked?
		boolean checked = ((CheckBox) view).isChecked();
		
		// Check which checkbox was clicked
		switch (view.getId()) {
		case R.id.a1Band:
			if (checked)
				Toast.makeText(this, "A1 selected", Toast.LENGTH_SHORT).show();
			break;
		case R.id.a2Band:
			if (checked) 
				Toast.makeText(this, "A2 selected", Toast.LENGTH_SHORT).show();
		case R.id.a3Band:
			if (checked) 
				Toast.makeText(this, "A3 selected", Toast.LENGTH_SHORT).show();
		case R.id.a4Band:
			if (checked) 
				Toast.makeText(this, "A4 selected", Toast.LENGTH_SHORT).show();
		case R.id.b1Band:
			if (checked) 
				Toast.makeText(this, "B1 selected", Toast.LENGTH_SHORT).show();
		case R.id.b2Band:
			if (checked) 
				Toast.makeText(this, "B2 selected", Toast.LENGTH_SHORT).show();
		case R.id.cBand:
			if (checked) 
				Toast.makeText(this, "C selected", Toast.LENGTH_SHORT).show();
		case R.id.dBand:
			if (checked) 
				Toast.makeText(this, "D selected", Toast.LENGTH_SHORT).show();
		case R.id.eBand:
			if (checked) 
				Toast.makeText(this, "E selected", Toast.LENGTH_SHORT).show();
		case R.id.fBand:
			if (checked) 
				Toast.makeText(this, "F selected", Toast.LENGTH_SHORT).show();
		case R.id.gBand:
			if (checked) 
				Toast.makeText(this, "G selected", Toast.LENGTH_SHORT).show();
		default:
			Toast.makeText(this, "Please select a band", Toast.LENGTH_SHORT).show();
			break;
		}
		
	}
	

}
