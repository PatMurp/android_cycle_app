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
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import wit.cc.R;
import wit.cc.custom.MapStateManager;
import android.app.Dialog;
import android.app.LocalActivityManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Map extends Base implements
	GoogleApiClient.ConnectionCallbacks,
	GoogleApiClient.OnConnectionFailedListener, 
	LocationListener {
	
	private static final int GPS_ERRORDIALOG_REQUEST = 9001;
	GoogleMap mMap; // map object
	private static final float DEFAULTZOOM = 15; // set zoom level
	GoogleApiClient mGoogleApiClient; // GoogleApiClient object
	FusedLocationProviderApi fusedLocationProviderApi = LocationServices.FusedLocationApi;
	Marker marker;
	
	Boolean tracking = false;  // is user tracking ?
	
	private static float final_distance = 0.0f;
	private float routeDistance = 0.0f; // total route distance
	private double old_lat = 0.0;
	private double old_long = 0.0;
	private double new_lat = 0.0;
	private double new_long = 0.0;
	float[] result = new float[1]; // array of route points
	
	
	@SuppressWarnings("unused")
	private static final double 
	WATERFORD_LNG = -7.138939,
	WATERFORD_LAT = 52.246322;
	
	
	
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
				mMap.getUiSettings().setZoomControlsEnabled(true); // turn on zoom controls
				setTrackingButtonState();
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
	
	// press current location icon go to current location apply marker
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
			
			// allow only one marker
			if (marker != null) {
				marker.remove();
			}
			// add marker
			MarkerOptions options = new MarkerOptions()
			.title("Current position")
			.position(ll)
			.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
			marker = mMap.addMarker(options);
		}
		
	}
	// connection failed
	@Override
	public void onConnectionFailed(ConnectionResult arg0) {
		
	}


	// connect successfully
	@Override
	public void onConnected(Bundle arg0) {
		// check for location updates
		LocationRequest request = LocationRequest.create(); // create instance of LocationRequest class
		request.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
		request.setInterval(5000); //optimum time is 1 minute or 60000
		request.setFastestInterval(1000);
		LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, request, this);
		
	}
	
	// tracking button
	public void track(View v) {
		mGoogleApiClient.disconnect(); // disconnect tracker
		Toast.makeText(this, "Tracker disconnected", Toast.LENGTH_SHORT).show();
		
		setTrackingButtonState();
	}


	// if disconnected
	@Override
	public void onConnectionSuspended(int arg0) {
		
	}

	
	// called when location changes
	@Override
	public void onLocationChanged(Location location) {

		if (old_lat == 0.0 && old_long == 0.0) {
			old_lat = location.getLatitude();
			old_long = location.getLongitude();
		}
		new_lat = location.getLatitude();
		new_long = location.getLongitude();
		
		// calculate distance
		Location.distanceBetween(old_lat, old_long, new_lat, new_long, result);
		
		routeDistance = final_distance + result[0];
		
		// reset values
		final_distance = routeDistance;
		old_lat = new_lat;
		old_long = new_long;
		
		TextView distanceDisplay = (TextView) findViewById(R.id.liveDistance);
		distanceDisplay.setText(String.format("%.02f km", routeDistance / 1000)); // display distance
	}
	
	// change button background and text if tracking 
	public void setTrackingButtonState() {
		Button trackingButton = (Button) findViewById(R.id.trackRoute);
		if (tracking) {
			trackingButton.setBackgroundColor(Color.RED);
			trackingButton.setTextColor(Color.WHITE);
			trackingButton.setText(R.string.trackRouteBtnOn);
		} else {
			trackingButton.setBackgroundColor(Color.GREEN);
			trackingButton.setTextColor(Color.GRAY);
			trackingButton.setText(R.string.trackRouteBtnOff);
		}
	}
	
}
