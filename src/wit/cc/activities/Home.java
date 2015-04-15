package wit.cc.activities;

import wit.cc.R;
import wit.cc.models.Route;
import android.os.Bundle;
import android.view.View;

public class Home extends Base { 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		//setupRoutes();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		routeFragment = new RouteFragement(); // create new fragment
		getFragmentManager().beginTransaction()
			.add(R.id.fragment_layout, routeFragment)
			.commit();
	}

	public void add(View v) {
		goToActivity(this, Add.class, null);
	}
	
	public void map(View v) {
		goToActivity(this, Map.class, null);
	}
	
	public void totals(View v) {
		goToActivity(this, Totals.class, null);
	}
	
	public void setupRoutes() {
		routeList.add(new Route("03-04-15", 7.5, "A2"));
		routeList.add(new Route("06-04-15", 6.3, "B2"));
		routeList.add(new Route("10-04-15", 6.1, "A4"));
	}
}
