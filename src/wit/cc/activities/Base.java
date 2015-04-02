package wit.cc.activities;

import java.util.ArrayList;
import java.util.List;

import wit.cc.R;
import wit.cc.models.Route;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Base extends Activity{
	
	//List<Route> routes = new RouteData().getRoutes(); // get hard coded data from RouteData.java
	
	public static ArrayList<Route> routeList = new ArrayList<Route>();
	public Fragment  routeFragment; // share list of routes between activities
	
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
	
	
	

}
