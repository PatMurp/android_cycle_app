package wit.cc.activities;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import wit.cc.R;
import wit.cc.models.Route;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class Base extends Activity{
	
	//List<Route> routes = new RouteData().getRoutes(); // get hard coded data from RouteData.java
	
	public static ArrayList<Route> routeList = new ArrayList<Route>();
	public Fragment  routeFragment; // share list of routes between activities
	protected Bundle activityInfo; // used for persistence
	
	protected void goToActivity(Activity current,
			Class<? extends Activity> activityClass,
			Bundle bundle) {
		Intent newActivity = new Intent(current, activityClass);
		
		if (bundle != null) newActivity.putExtras(bundle);
		
		current.startActivity(newActivity);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.optionsmenu, menu); // inflate menu
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.help:
			goToActivity(this, Help.class, null);
			break;
		case R.id.home:
			goToActivity(this, Home.class, null);
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public EditText getEditText(int id) {
		return ((EditText) findViewById(id));
	}
	protected String getEditString(int id) {
		return (getEditText(id)).getText().toString();
	}
	
	protected void setEditString(int id, String str) {
		(getEditText(id)).setText(str);
	}
	
	protected void setEditDouble(int id, Double d) {
		((EditText) findViewById(id)).setText(d.toString());
	}
	
	protected double getEditDouble(int id) {
		return Double.parseDouble(getEditString(id));
	}
	
	// get current date and parse to string
	protected static String getCurrentDateToString() {
		SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yy");
		String date  = df.format(Calendar.getInstance().getTime());
		return date;
	}
}
