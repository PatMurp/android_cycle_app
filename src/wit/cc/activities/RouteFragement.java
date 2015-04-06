package wit.cc.activities;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Intent;
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
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		
		// create new bundle with row id
		Bundle activityInfo = new Bundle();
		activityInfo.putInt("routeID", v.getId());
		
		// launch edit screen and add bundle
		Intent goEdit = new Intent(getActivity(), Edit.class);
		goEdit.putExtras(activityInfo);
		getActivity().startActivity(goEdit);
	}

}
