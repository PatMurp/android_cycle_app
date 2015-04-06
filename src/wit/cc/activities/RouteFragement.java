package wit.cc.activities;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
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
			onRouteDelete((Route) v.getTag());
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
	
	public void onRouteDelete (final Route route) {
		
		String stringDate = route.getDate();
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setMessage("Are you sure you want to Delete the \'Route\' "
				+ stringDate + "?");
		builder.setCancelable(false);
		
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				Base.routeList.remove(route); // remove from our list
				listAdapter.routeList.remove(route); // update adapters data
				listAdapter.notifyDataSetChanged(); // refresh adapter
			}
		}).setNegativeButton("No", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}

}
