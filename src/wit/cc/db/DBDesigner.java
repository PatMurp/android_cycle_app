package wit.cc.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBDesigner extends SQLiteOpenHelper{
	
	public static final String 	TABLE_ROUTE = "table_route";
	public static final String COLUMN_ID = "routeid";
	public static final String COLUMN_DATE = "routedate";
	public static final String COLUMN_DISTANCE =  "routedistance";
	public static final String COLUMN_CO2BAND = "routeco2band";
	public static final String DATABASE_NAME = "cyclecredit.db";
	public static final int DATABASE_VERSION = 1;
	
	// database creation sql statement
	private static final String DATABASE_CREATE_TABLE_ROUTE = "create table "
			+ TABLE_ROUTE + "(" +  COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_DATE + " text not null, "
			+ COLUMN_DISTANCE + " double not null, "
			+ COLUMN_CO2BAND + " text not null);";

	public DBDesigner(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE_TABLE_ROUTE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(DBDesigner.class.getName(),
				"Upgrading database from version " + oldVersion + " to "
					+ newVersion + ", which will destory all old data");
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ROUTE);
		onCreate(db);
	}

}
