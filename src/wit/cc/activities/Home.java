package wit.cc.activities;

import wit.cc.R;
import android.os.Bundle;
import android.view.View;

public class Home extends Base { 
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
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
}
