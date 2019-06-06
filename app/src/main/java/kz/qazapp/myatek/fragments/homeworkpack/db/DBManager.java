package kz.qazapp.myatek.fragments.homeworkpack.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {
    private DBHelper dbHelper;
    private DBHelperHw dbHelperHw;
    private Context context;
    private SQLiteDatabase database;
    private SQLiteDatabase databaseHw;

    public DBManager(Context c){
        context = c;
    }

    public DBManager open() throws SQLException{
        dbHelper = new DBHelper(context);
        database = dbHelper.getWritableDatabase();

        dbHelperHw = new DBHelperHw(context);
        databaseHw  = dbHelperHw.getWritableDatabase();
        return this;
    }

    public void close(){
        dbHelper.close();
    }

    public void insert(String flag, String text, String date){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelper.NOTE_TEXT, text);
        contentValue.put(DBHelper.NOTE_DATE, date);
        database.insert(DBHelper.TABLE_NAME_note, null, contentValue);
    }

    public void insertHw(String title, String text, String sub, String date, String hour){
        ContentValues contentValue = new ContentValues();
        contentValue.put(DBHelperHw.HW_TITLE, title);
        contentValue.put(DBHelperHw.HW_TEXT, text);
        contentValue.put(DBHelperHw.HW_SUB, sub);
        contentValue.put(DBHelperHw.HW_DATE, date);
        contentValue.put(DBHelperHw.HW_DATE2, hour);
        databaseHw.insert(DBHelperHw.TABLE_NAME_hw, null, contentValue);
    }

    public void closeHw(){
        dbHelperHw.close();
    }

    public Cursor fetch(String flag){
        Cursor cursor = null;

        if (flag == "note"){
            String[] columns = new String[]{DBHelper._ID, DBHelper.NOTE_TEXT, DBHelper.NOTE_DATE};
            cursor = database.query(DBHelper.TABLE_NAME_note, columns, null,null, null, null, null);
        }

        if (flag == "hw"){
            String[] columns = new String[]{DBHelperHw._ID_hw, DBHelperHw.HW_TITLE, DBHelperHw.HW_TEXT, DBHelperHw.HW_SUB, DBHelperHw.HW_DATE, DBHelperHw.HW_DATE2};
            cursor = databaseHw.query(DBHelperHw.TABLE_NAME_hw, columns, null,null, null, null, null);
        }
        if(cursor != null){
            cursor.moveToFirst();
        }

        return cursor;
    }

    public int update(long _id, String text, String date){
        int i = 0;
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.NOTE_TEXT, text);
        contentValues.put(DBHelper.NOTE_DATE, date);
        i = database.update(DBHelper.TABLE_NAME_note, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public int updateHw(long _id, String title, String text, String sub, String date, String hour){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelperHw.HW_TITLE, title);
        contentValues.put(DBHelperHw.HW_TEXT, text);
        contentValues.put(DBHelperHw.HW_SUB, sub);
        contentValues.put(DBHelperHw.HW_DATE, date);
        contentValues.put(DBHelperHw.HW_DATE2, hour);
        int i = database.update(DBHelperHw.TABLE_NAME_hw, contentValues, DBHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(String flag, long _id){
        if (flag == "note") database.delete(DBHelper.TABLE_NAME_note, DBHelper._ID + "=" + _id, null);
        if (flag == "hw") databaseHw.delete(DBHelperHw.TABLE_NAME_hw, DBHelperHw._ID_hw + "=" + _id, null);
    }
}
