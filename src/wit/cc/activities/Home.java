package wit.cc.activities;

import java.util.List;

import wit.cc.R;
import wit.cc.adapters.RouteAdapter;
import wit.cc.models.Route;
import wit.cc.models.RouteData;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Home extends ListActivity {
	
	List<Route> routes = new RouteData().getRoutes(); // get hard coded data from RouteData.java

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		RouteAdapter adapter = new RouteAdapter(this, R.layout.item_route, routes);
		setListAdapter(adapter);
		
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
			
			break;

		default:
			break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	
}
