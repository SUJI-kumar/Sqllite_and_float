package com.example.ca2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class database extends SQLiteOpenHelper {

    //Database version
    private static final int DATABASE_VERSION = 1;
    //Database Name
    private static final String DATABASE_NAME = "sqliteDatabase.db";
    //Database Table name
    private static final String TABLE_NAME = "myTable";
    //Table columns
    public static final String UID = "_id";
    public static final String NAME = "name";
    public static final String Reg_no = "regno";
    private Context context;
    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + UID +
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " VARCHAR(255)," + Reg_no + " VARCHAR(225));";
    public database(@Nullable  Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            db.execSQL(CREATE_TABLE);
        }catch (Exception e)
        {
            Toast.makeText(context,"Error"+e,Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try{
            db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }catch (Exception e)
        {
            Toast.makeText(context,"Error"+e,Toast.LENGTH_SHORT).show();
        }
    }

    //Inserting data
    public Boolean insert_data(String name,String reg_no)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(NAME,name);
        c.put(Reg_no,reg_no);
        long r=db.insert(TABLE_NAME,null,c);

        if(r == -1)
        {
            return false;
        }else
        {
            return true;
        }
    }
    public Cursor getinfo()
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from "+TABLE_NAME,null);
        return cursor;
    }

    public boolean deletedata(String reg_no)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from  myTable where regno=?",new String[]{reg_no});
        if(cursor.getCount()>0)
        {
            long r=db.delete(TABLE_NAME, "regno=?",new String[]{reg_no});
            if(r == -1)
            {
                return false;
            }else
            {
                return true;
            }

        }else
        {
            return false;
        }
    }

    public Boolean update_data(String name,String reg_no)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues c=new ContentValues();
        c.put(NAME,name);
        Cursor cursor=db.rawQuery("select * from  myTable where regno=?",new String[]{reg_no});
        if(cursor.getCount()>0)
        {
            long r=db.update(TABLE_NAME,c,"regno=?",new String[]{reg_no});
            if(r == -1)
            {
                return false;
            }else
            {
                return true;
            }

        }else
        {
            return false;
        }
    }



}
