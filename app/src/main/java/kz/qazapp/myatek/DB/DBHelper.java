package kz.qazapp.myatek.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DB_VER = 1;
    public static final String DB_NAME = "Sheds";
    public static final String TABLE_NAME = "Shed";

    public static final String ID = "_id";
    public static final String DAY = "days";
    public static final String SUB1 = "sub1";
    public static final String SUB1TEACH = "sub1teach";
    public static final String SUB1LEC = "sub1lec";
    public static final String SUB2 = "sub2";
    public static final String SUB2TEACH = "sub2teach";
    public static final String SUB2LEC = "sub2lec";
    public static final String SUB3 = "sub3";
    public static final String SUB3TEACH = "sub3teach";
    public static final String SUB3LEC = "sub3lec";
    public static final String SUB4 = "sub4";
    public static final String SUB4TEACH = "sub4teach";
    public static final String SUB4LEC = "sub4lec";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+" ("+
                ID+" integer primary key,"+
                DAY+" text,"+
                SUB1+" text,"+
                SUB1TEACH+" text,"+
                SUB1LEC+" text,"+
                SUB2+" text,"+
                SUB2TEACH+" text,"+
                SUB2LEC+" text,"+
                SUB3+" text,"+
                SUB3TEACH+" text,"+
                SUB3LEC+" text,"+
                SUB4+" text,"+
                SUB4TEACH+" text,"+
                SUB4LEC+" text"+")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+TABLE_NAME);

        onCreate(sqLiteDatabase);
    }
}
