package wit.cc.activities;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.internal.ml;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import wit.cc.R;
import wit.cc.custom.MapStateManager;
import android.app.Dialog;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class Map extends Base implements
	GoogleApiClient.ConnectionCallbacks,
	GoogleApiClient.OnConnectionFailedListener, LocationListener {
	
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap; // map object
	private static final float DEFAULTZOOM = 14; // set zoom level
	
	
	@SuppressWarnings("unused")
	private static final double 
	WATERFORD_LNG = -7.138939,
	WATERFORD_LAT = 52.246322;
	
	GoogleApiClient mGoogleApiClient; // GoogleApiClient object
	FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (servicesOk()) { // display home
			setContentView(R.layout.activity_map);
			if (initMap()) {
				//goToLocation(WATERFORD_LAT, WATERFORD_LNG, DEFAULTZOOM); // hardcoded location
				
				// create connection client and connect to service
				mGoogleApiClient = new GoogleApiClient.Builder(this)
					.addApi(LocationServices.API)
					.addConnectionCallbacks(this)
					.addOnConnectionFailedListener(this)
					.build();
				mGoogleApiClient.connect();
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
	
	// map displays at specific location and zoom level
	private void goToLocation(double lat, double lng,
			float zoom) {
		LatLng ll = new LatLng(lat, lng);
		CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, zoom);
		mMap.moveCamera(update);
	}
	
	// menu for map screen only
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.mapoptions, menu); // inflate map menu
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()) {
		case R.id.help:
			goToActivity(this, Help.class, null);
			break;
		case R.id.home:
			goToActivity(this, Home.class, null);
			break;
		case R.id.mapNormal:
			mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
			break;
		case R.id.mapSatellite:
			mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.mapTerrain:
			mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
			break;
		case R.id.mapHybrid:
			mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
			break;
		case R.id.goToCurrentLocation:
			goToCurrentLocation();
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// when activity shuts down save map values
	@Override
	protected void onStop() {
		super.onStop();
		MapStateManager mgr = new MapStateManager(this);
		mgr.saveMapState(mMap); // pass reference to map object
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		MapStateManager mgr = new MapStateManager(this);
		CameraPosition position = mgr.getSavedCameraPosition(); // get values
		if (position != null) {
			// restore position
			CameraUpdate update = CameraUpdateFactory.newCameraPosition(position);
			mMap.moveCamera(update); // move camera to position
			mMap.setMapType(mgr.getSavedMapType()); // get map type
		}
	}

	protected void goToCurrentLocation() {
		
		Location currentLocation = fusedLocationProviderApi.getLastLocation(mGoogleApiClient);
		// check for null location
		if (currentLocation == null) {
			Toast.makeText(this, "Current location isn,t available", Toast.LENGTH_SHORT).show();
		} else {
			// use location information to set currentLocation
			LatLng ll = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
			CameraUpdate update = CameraUpdateFactory.newLatLngZoom(ll, DEFAULTZOOM);
			mMap.animateCamera(update); // call camera animate method
		}
		
	}
	// connection failed
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		
	}


	// connect successfully
	@Override
	public void onConnected(Bundle arg0) {
		
		LocationRequest request = LocationRequest.create(); // create instance of LocationRequest class
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setInterval(5000); //optimum time is 1 minute or 60000
		request.setFastestInterval(1000);
		LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, request, this);
		
	}


	// if disconnected
	@Override
	public void onConnectionSuspended(int arg0) {
		
	}



	@Override
	public void onLocationChanged(Location location) {
		
		String msg = "Location: " + location.getLatitude() + ", " + location.getLongitude();
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}
	
}
