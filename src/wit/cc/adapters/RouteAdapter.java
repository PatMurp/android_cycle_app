package wit.cc.adapters;

import java.util.List;

import wit.cc.R;
import wit.cc.models.Route;
import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RouteAdapter extends ArrayAdapter<Route> {

	private Context context;
	public List<Route> routeList;
	private OnClickListener deleteListener; // reference to delete route

	// Constructor
	public RouteAdapter(Context context, OnClickListener deleteListener, List<Route> routeList) {
		super(context, R.layout.routerow, routeList);
		
		this.context = context;
		this.deleteListener = deleteListener;
		this.routeList = routeList;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		RouteItem item = new RouteItem(context, parent, deleteListener, routeList.get(position));
		return item.view;
	}
	
	@Override
	public int getCount() {
		return routeList.size();
	}
	
}
