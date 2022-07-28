package com.example.msqldata;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.annotation.StyleableRes;

import com.google.android.material.tabs.TabLayout;

import java.util.PrimitiveIterator;


public class BDHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "UserDetail.db";
    private static final String TABLE_NAME = "UserDetails";
    private static final String NAME = "name";
    private static final String NUMBER = "number";
    private static final String BIRTHDAY = "Date of Birth";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE = "Create table "+TABLE_NAME+" ("+NAME+" VARCHAR(30), "+NUMBER+" INTERGER ,"+BIRTHDAY+" INTEGER);";
    private static final String DROP_TABLE = " DROP TABLE IF EXISTS "+TABLE_NAME;

    private static final String SELECT_ALL = " SELECT * FROM "+TABLE_NAME;

    private Context context;
    public BDHelper(@Nullable Context context) {
        super ( context,DATABASE_NAME,null, VERSION );

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL ( CREATE_TABLE );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL ( DROP_TABLE );

    }
    public Boolean insertData(String name, String contact, String dob){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();

        ContentValues contentValues = new ContentValues ();

        contentValues.put ( "Name", name );
        contentValues.put ( "Contact", contact );
        contentValues.put ( "Dob", dob );

        long result = sqLiteDatabase.insert (TABLE_NAME, null, contentValues);

        if (result == 1){
            return false;

        }else {
            return  true;
        }
    }

    public Boolean UpdateDate(String name, String contact, String dob){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        ContentValues contentValues = new ContentValues ();

        contentValues.put ( "Contact", contact );
        contentValues.put ( "Dob", dob );

        Cursor cursor = sqLiteDatabase.rawQuery ( SELECT_ALL, null );
        if (cursor.getCount () > 0){

        long result = sqLiteDatabase.update ( TABLE_NAME, contentValues, "name=?", new String[] {name} );

        if (result == -1){
            return false;
        }else {
            return true;
        }
        }else{
            return false;
        }


    }

    public Boolean deleteData(String  name){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();

        Cursor cursor = sqLiteDatabase.rawQuery ( SELECT_ALL, null );

        if (cursor.getCount () == -1) {

            long result = sqLiteDatabase.delete ( TABLE_NAME, "Name=?", new String[]{name} );
            if (result == -1){
                return false;

        }else {
            return true;
        }
        }else {
            return false;
        }
    }
    public Cursor ShowData(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase ();
        Cursor cursor = sqLiteDatabase.rawQuery ( SELECT_ALL, null );
        return cursor;
    }


}
