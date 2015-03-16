package wit.cc.activities;

import java.util.List;

import wit.cc.R;
import wit.cc.models.Route;
import wit.cc.models.RouteData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class Base extends Activity{
	
	List<Route> routes = new RouteData().getRoutes(); // get hard coded data from RouteData.java
	
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
