package wit.cc.adapters;

import java.util.List;

import wit.cc.R;
import wit.cc.models.Route;
import wit.cc.models.Calc;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

@SuppressLint("InflateParams")
public class RouteAdapter extends ArrayAdapter<Route> {

	private Context context;
	private List<Route> objects;
	
	// Constructor
	public RouteAdapter(Context context, int resource, List<Route> objects) {
		super(context, resource, objects);
		this.context = context;
		this.objects = objects;
	}
	
	@SuppressLint("ViewHolder")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Route route = objects.get(position);
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.item_route, null);
		TextView tvDate = (TextView) view .findViewById(R.id.routeDate);
		tvDate.setText( route.getDate());
		
		TextView tvDistance = (TextView) view.findViewById(R.id.routeDistance);
		double rDistance = route.getDistance();// get double value
		String sDistance = String.valueOf(rDistance); // parse to string
		tvDistance.setText(sDistance + " km");
		
		TextView tvCo2 = (TextView) view.findViewById(R.id.routeCo2);
		double rEmission = Calc.emissionForBand(route.getCo2band()); // get co2 emission value 
		double rCo2 = Calc.calcCo2Emissions(rDistance, rEmission); // calculate co2 emissions
		String sCo2 = String.valueOf(rCo2); // parse to string
		tvCo2.setText(sCo2 + " kg");
		
		return view;
	}
	
	

}
