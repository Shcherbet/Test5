package shcher.test5;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class DBHelper  extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "myDB";
    public static final String TABLE_LIST= "list";

    public static final String KEY_ID = "_id";
    public static final String KEY_NAME= "name";
    public static final String KEY_DATE= "date";
    public static final String KEY_DATE2= "date2";
    //public static final long KEY_DATE= 100;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_LIST + "(" + KEY_ID + " integer primary key, "
        + KEY_NAME + " TEXT, " + KEY_DATE + " DATE, " + KEY_DATE2 + " TEXT " + ")");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_LIST);

        onCreate(db);

    }
}
