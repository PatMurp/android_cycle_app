package wit.cc.activities;

import wit.cc.R;
import wit.cc.adapters.RouteAdapter;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

public class Home extends Base { 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		RouteAdapter adapter = new RouteAdapter(this, R.layout.item_route, routes);
		ListView lv = (ListView) findViewById(R.id.list);
 		lv.setAdapter(adapter);
	}
	
	

	public void add(View v) {
		goToActivity(this, CarPicker.class, null);
	}
}
