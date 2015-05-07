package wit.cc.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.google.android.gms.internal.mm;

import wit.cc.R;
import wit.cc.custom.CustomCo2BandsSelectedListener;
import wit.cc.models.Route;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Loader.ForceLoadContentObserver;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

@SuppressLint({ "DefaultLocale", "SimpleDateFormat" })
public class Edit extends Base {
	private Context context;
	private Route aRoute;
	static final int DATE_DIALOG_ID = 0;
	private int mYear, mMonth, mDay;
	EditText editDateCal;
	// allowed co2 bands
	String[] validBands = { "A1", "A2", "A3", "A4", "B1", "B2", "C", "D", "E",
			"F", "G" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;

		activityInfo = getIntent().getExtras();
		aRoute = dbManager.get((activityInfo.getInt("routeID")));
		// aRoute = getRouteObject(activityInfo.getInt("routeID"));

		setContentView(R.layout.activity_edit);

		// assign values to edit layout
		setEditString(R.id.editDate, aRoute.getDate());
		setEditDouble(R.id.editDistance, aRoute.getDistance());
		setEditString(R.id.editBand, aRoute.getCo2band());

		editDateCal = (EditText) findViewById(R.id.editDate);

		// call date dialog if clicked
		editDateCal.setOnClickListener(new OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DATE_DIALOG_ID);
			}
		});

		// get edit date by calendar
		Calendar c = convertStringToCalendar(aRoute.getDate());
		mYear = c.get(Calendar.YEAR);
		mMonth = c.get(Calendar.MONTH);
		mDay = c.get(Calendar.DAY_OF_MONTH);
	}

	public void update(View v) {

		String routeDate = getEditString(R.id.editDate);
		double routeDistance;
		String routeCo2Band = getEditString(R.id.editBand);

		try { // catch numberFormat exceptions
			routeDistance = getEditDouble(R.id.editDistance);
		} catch (NumberFormatException e) {
			routeDistance = 0.0;
		}

		// check for values in edit form
		if ((routeDate.length() > 0)
				&& (getEditText(R.id.editDistance).length() > 0)
				&& (routeCo2Band.length() > 0)) {

			// validate co2 bands
			if (isCo2BandValid(routeCo2Band) == false) {
				Toast.makeText(this, "Invalid co2 band", Toast.LENGTH_SHORT)
						.show();
			} else {
				// reset values if changed
				aRoute.setDate(routeDate);
				aRoute.setDistance(routeDistance);
				aRoute.setCo2band(routeCo2Band.toUpperCase()); // set to
																// uppercase

				// Update route data and go to home activity
				dbManager.update(aRoute);
				goToActivity(this, Home.class, activityInfo);
			}

		} else {
			Toast.makeText(this,
					"You must enter a value for Date, Distance. and C02Band",
					Toast.LENGTH_SHORT).show();
		}
	}

	// check if co2 band is valid
	private boolean isCo2BandValid(String value) {

		for (String band : validBands) {
			if (value.equalsIgnoreCase(band)) {
				return true;
			}
		}
		return false;
	}

	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			// open datepicker dialog
			// set date picker for edit date
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

	// convert string to calendar object
	public static Calendar convertStringToCalendar(String date) {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		try {
			cal.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cal;
	}

	private DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		// resets date field value when dialog box is closed
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			mYear = year;
			mMonth = monthOfYear;
			mDay = dayOfMonth;
			editDateCal.setText(formatDate(mYear, mMonth, mDay));
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
