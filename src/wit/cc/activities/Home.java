package wit.cc.activities;

import wit.cc.R;
import wit.cc.adapters.RouteAdapter;
import android.os.Bundle;

public class Home extends Base {
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		RouteAdapter adapter = new RouteAdapter(this, R.layout.item_route, routes);
		setListAdapter(adapter);
	}
}
