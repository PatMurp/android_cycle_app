package wit.cc.adapters;

import wit.cc.custom.Calc;
import wit.cc.models.Route;
import wit.cc.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class RouteItem {
	View view;
	
	public RouteItem(Context context, ViewGroup parent,
			OnClickListener deleteListener, Route route) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		view = inflater.inflate(R.layout.routerow, parent, false);
		view.setId(route.getRouteId());
		
		updateControls(route);
		
		ImageView imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
		imgDelete.setTag(route);
		imgDelete.setOnClickListener(deleteListener);
	}
	
	private void updateControls(Route route) {
		
		TextView tvDate = (TextView) view .findViewById(R.id.routeDate);
		tvDate.setText(route.getDate());
		
		TextView tvDistance = (TextView) view.findViewById(R.id.routeDistance);
		double rDistance = route.getDistance();// get double value
		String sDistance = String.valueOf(rDistance); // parse to string
		tvDistance.setText(sDistance + " km");
		
		TextView tvCo2 = (TextView) view.findViewById(R.id.routeCo2);
		double rEmission = Calc.emissionForBand(route.getCo2band()); // get co2 emission value 
		double rCo2 = Calc.calcCo2Emissions(rDistance, rEmission); // calculate co2 emissions
		String sCo2 = String.valueOf(rCo2); // parse to string
		tvCo2.setText(sCo2 + " kg CO2");
	}

}
