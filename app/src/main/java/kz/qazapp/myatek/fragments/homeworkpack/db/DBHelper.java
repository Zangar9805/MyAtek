package kz.qazapp.myatek.fragments.homeworkpack.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME_note = "NOTE_LIST";

    public static final String _ID = "_id";
    public static final String NOTE_TEXT = "textL";
    public static final String NOTE_DATE = "dateL";


    public static final String DB_NAME = "colledge";

    static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_note = "create table " + TABLE_NAME_note + " ( " + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE_TEXT + " TEXT, " + NOTE_DATE + " TEXT );";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_note);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_note);
        onCreate(db);
    }

}
