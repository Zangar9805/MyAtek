package kz.qazapp.myatek.fragments.homeworkpack.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelperHw extends SQLiteOpenHelper {

    public static final String TABLE_NAME_hw = "HOME_WORK";

    public static final String _ID_hw = "_id";
    public static final String HW_TITLE = "title";
    public static final String HW_TEXT = "text";
    public static final String HW_SUB = "subject";
    public static final String HW_DATE = "date";
    public static final String HW_DATE2 = "hour";

    public static final String DB_NAME = "coll";

    private static final int DB_VERSION = 1;

    private static final String CREATE_TABLE_homework = "create table " + TABLE_NAME_hw + " ( " + _ID_hw + " INTEGER PRIMARY KEY AUTOINCREMENT, " + HW_TITLE + " TEXT, " + HW_TEXT+ " TEXT, " + HW_SUB + " TEXT, "+HW_DATE+ " TEXT, " + HW_DATE2+" TEXT );";


    public DBHelperHw(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_homework);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_hw);
    }

}
