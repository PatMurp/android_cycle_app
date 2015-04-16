package wit.cc.custom;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

import android.content.Context;
import android.content.SharedPreferences;

public class MapStateManager {
	private static final String LONGITUDE = "longitude";
	private static final String LATITUDE = "latitude";
	private static final String ZOOM = "zoom";
	private static final String BEARING = "bearing";
	private static final String TILT = "tilt";
	private static final String MAPTYPE = "MAPTYPE";
	
	public static final String PREFS_NAME = "mapCameraState"; 
	
	private SharedPreferences mapStatePrefs;
	
	public MapStateManager(Context context) {
		mapStatePrefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
	}
	
	// save and restore map state
	public void saveMapState(GoogleMap map) {
		
		SharedPreferences.Editor editor = mapStatePrefs.edit(); //get map values
		CameraPosition position = map.getCameraPosition(); // get map position
		
		// deconstruct camera position
		editor.putFloat(LATITUDE, (float) position.target.latitude); // save latitude
		editor.putFloat(LONGITUDE, (float) position.target.longitude);// save longitude
		editor.putFloat(ZOOM, position.zoom); // save zoom
		editor.putFloat(TILT, position.tilt); // save tilt
		editor.putFloat(BEARING, position.bearing); // save bearing
		editor.putInt(MAPTYPE, map.getMapType()); // save map type
		
		editor.commit(); // save all changes
	}
	
	// return saved camera position
	public CameraPosition getSavedCameraPosition() {
		
		double latitude = mapStatePrefs.getFloat(LATITUDE, 0);
		
		// check return value
		if(latitude == 0) { // no saved camera position
			return null;
		} else {
			double longitude = mapStatePrefs.getFloat(LONGITUDE, 0);
			LatLng target = new LatLng(latitude, longitude); // retrieve latitude and longitude
			
			float zoom = mapStatePrefs.getFloat(ZOOM, 0); // retrieve zoom value
			float bearing = mapStatePrefs.getFloat(BEARING, 0); // retrieve bearing value
			float tilt = mapStatePrefs.getFloat(TILT, 0); // retrieve tilt value
			
			// create new instance of CameraPosition class
			CameraPosition position = new CameraPosition(target, zoom, tilt, bearing);
			return position;
		}
	}
	
	// return saved map type
	public int getSavedMapType() {
		return mapStatePrefs.getInt(MAPTYPE, GoogleMap.MAP_TYPE_NORMAL);
	}

}
