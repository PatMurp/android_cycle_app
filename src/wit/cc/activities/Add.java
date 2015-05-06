package wit.cc.activities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import wit.cc.R;
import wit.cc.custom.CustomCo2BandsSelectedListener;
import wit.cc.models.Route;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint("SimpleDateFormat")
public class Add extends Base {
	static final int DATE_DIALOG_ID = 0;
	private int mYear, mMonth, mDay;
	EditText routeDate;

	Spinner bandSelecter;
	private Button addRoute;
	String rDate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add);

		// get current date by calander
		Calendar c = Calendar.getInstance();
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
		
		// format and show current date in date field
		routeDate = (EditText) findViewById(R.id.newDate);
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		routeDate.setText(sdf.format(c.getTime()));

		// call date dialog if clicked
		routeDate.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		addListenerButton();
		addListenerOnSpinneritemSelection();
	}

	// spinner listener
	public void addListenerOnSpinneritemSelection() {
		bandSelecter = (Spinner) findViewById(R.id.chooseCo2Band);
		bandSelecter
				.setOnItemSelectedListener(new CustomCo2BandsSelectedListener());
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
				if ((rDate.length() > 0)
						&& (getEditString(R.id.newDistance).length() > 0)) {

					// save new route to array
					Route r = new Route(rDate, rDistance, rCo2Band);
					// routeList.add(r);
					dbManager.insert(r);
					goToActivity(Add.this, Home.class, null);
				} else {
					Toast.makeText(Add.this,
							"Please enter value's for Date and Distance",
							Toast.LENGTH_SHORT).show();
				}

			}
		});
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// open datepicker dialog
			// set date picker for current date
			// set date picker style
			// add mDateSetListener to date picker
			return new DatePickerDialog(this,
					android.R.style.Theme_DeviceDefault, mDateSetListener,
					mYear, mMonth, mDay);
		default:
			break;
		}
		return null;
	}
	
	// display
	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		// resets date field value when dialog box is closed
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			routeDate.setText(formatDate(mYear, mMonth, mDay));
		}
	};

	// date formatter for date picker dialog
	private static String formatDate(int year, int month, int day) {

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(0);
		cal.set(year, month, day);
		Date date = cal.getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");

		return sdf.format(date);
	}

}
