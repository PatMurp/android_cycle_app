package wit.cc.db;

import java.util.ArrayList;
import java.util.List;

import wit.cc.models.Route;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	
	private SQLiteDatabase database; // db reference
	private DBDesigner dbHelper;
	
	public DBManager(Context context) {
		dbHelper = new DBDesigner(context);
	}
	
	// return a reference to database created from SQL string
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}
	
	public void close() {
		database.close();
	}
	
	// add new route to db
	public void insert(Route r) {
		ContentValues values = new ContentValues();
		values.put(DBDesigner.COLUMN_DATE, r.getDate());
		values.put(DBDesigner.COLUMN_DISTANCE, r.getDistance());
		values.put(DBDesigner.COLUMN_CO2BAND, r.getCo2band());
		
		long insertId = database.insert(DBDesigner.TABLE_ROUTE, null, values);
	}
	
	// delete route from db
	public void delete(int id) {
		Log.v("DB", "Route deleted with id: " + id);
		
		database.delete(DBDesigner.TABLE_ROUTE, 
						DBDesigner.COLUMN_ID + " = " + id, 
						null);
	}
	
	// update route in db
	public void update(Route r) {
		
		ContentValues values = new ContentValues();
		values.put(DBDesigner.COLUMN_DATE, r.getDate());
		values.put(DBDesigner.COLUMN_DISTANCE, r.getDistance());
		values.put(DBDesigner.COLUMN_CO2BAND, r.getCo2band());
		
		long insertId = database.update(DBDesigner.TABLE_ROUTE, 
										values, DBDesigner.COLUMN_ID + " = " + r.getRouteId(), 
										null);
	}
	
	
	public List<Route> getAll() {
		List<Route> routes = new ArrayList<Route>();
		Cursor cursor = database.rawQuery("SELECT * FROM " + DBDesigner.TABLE_ROUTE, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Route pojo = toRoute(cursor);
			routes.add(pojo);
			cursor.moveToNext();
		}
		cursor.close();
		return routes;
	}
	
	public Route get(int id) {
		Route pojo = null;
		
		Cursor cursor = database.rawQuery("SELECT * FROM "
					+ DBDesigner.TABLE_ROUTE + " WHERE "
					+ DBDesigner.COLUMN_ID + " = "  + id, null);
		cursor.moveToFirst();
		while(!cursor.isAfterLast()) {
			Route temp = toRoute(cursor);
			pojo = temp;
			cursor.moveToNext();
		}
		cursor.close();
		return pojo;
		
	}
	
	// convert cursor object in to Route object
	private Route toRoute(Cursor cursor) {
		Route pojo = new Route();
		pojo.setRouteId(cursor.getInt(0));
		pojo.setDate(cursor.getString(1));
		pojo.setDistance(cursor.getDouble(2));
		pojo.setCo2band(cursor.getString(3));
		return pojo;
	}
	
	public void setupList() {
		
		Route r1 = new Route("03-April-15", 8.5, "A3");
		Route r2 = new Route("11-April-15", 6, "B2");
		Route r3 = new Route("21-April-15", 5.3, "A4");
		
		insert(r1);
		insert(r2);
		insert(r3);
	}
}
