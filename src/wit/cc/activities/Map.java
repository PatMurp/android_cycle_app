package wit.cc.activities;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import wit.cc.R;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

public class Map extends Base {
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (servicesOk()) { // display home
			setContentView(R.layout.activity_map);
			if (initMap()) {
				Toast.makeText(this, "Ready to map", Toast.LENGTH_LONG).show();
			}
			else {
				Toast.makeText(this, "Map not available", Toast.LENGTH_LONG).show();
			}
		} else { // display home screen
			setContentView(R.layout.activity_home);
		}
	}
	
	// check to see if google play services are available
	public boolean servicesOk() {
		int isAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		
		if (isAvailable == ConnectionResult.SUCCESS) {
			return true;
		} // check for recoverable error and display appropriate message
		else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {
			Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable, this, GPS_ERRORDIALOG_REQUEST);
			dialog.show();
		} 
		else {// non recoverable error
			Toast.makeText(this, "Can't connect to Google Play Services", Toast.LENGTH_LONG).show();
		}
		return false;
	}
	
	// check to see if mMap reference is set
	private boolean initMap() {
		if (mMap == null) {
			MapFragment mapFrag =
					(MapFragment) getFragmentManager().findFragmentById(R.id.map);
			mMap = mapFrag.getMap(); // get reference to map
		}
		return (mMap != null);
	}
	

	
}
