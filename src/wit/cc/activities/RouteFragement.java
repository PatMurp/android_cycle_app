package wit.cc.activities;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import wit.cc.adapters.RouteAdapter;
import wit.cc.models.Route;

public class RouteFragement extends ListFragment implements OnClickListener {
	protected Base activity;
	protected static RouteAdapter listAdapter;
	protected ListView listView;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		this.activity = (Base) activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		listAdapter = new RouteAdapter(activity, this, Base.routeList);
		setListAdapter(listAdapter);
	}

	@Override
	public void onClick(View v) {
		if (v.getTag() instanceof Route) {
			
		}
		
	}

}
