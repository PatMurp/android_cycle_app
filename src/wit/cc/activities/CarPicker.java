package wit.cc.activities;

import wit.cc.R;
import android.view.View.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class CarPicker extends Base {
	
	public RadioGroup radioGroupId;
	public RadioButton carBandButton;
	public Button button;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_car_picker);
		
		addListenerOnButton();
	}
	
	public void addListenerOnButton() {
		
		radioGroupId = (RadioGroup) findViewById(R.id.radioCo2BandGroup);
		button = (Button) findViewById(R.id.btnChooseBand);
		
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				// Get selected radio button from radioGroup
				int selectedId = radioGroupId.getCheckedRadioButtonId();
				
				// find the radiobutton by returned id
				carBandButton = (RadioButton) findViewById(selectedId);
				
				Toast.makeText(CarPicker.this, carBandButton.getText() + " selected", Toast.LENGTH_SHORT).show();
			}
		});
		
	}
}
