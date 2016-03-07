package com.example.user.sqldatenbank;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by User on 07.03.2016.
 */
public class Database extends SQLiteOpenHelper
{
    public static final String Database_name = "Datenbank.db";
    public static final String Table_name = "ErsterTable";
    public static final String PrimaryKey = "ID";
    public static final String Variable1 ="Name";
    public static final String Variable2 = "Nachname";
    public static final String Variable3 = "Gruppe";

    public Database(Context context) {
        super(context,Database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("DROP TABLE IF EXISTS "+Table_name+";");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + Table_name + "(" + PrimaryKey + " INTEGER PRIMARY KEY AUTOINCREMENT," + Variable1 + " TEXT," + Variable2 + " TEXT," + Variable3 + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXITS "+Table_name+";");
        onCreate(db);

    }

    public boolean Insert (String Vorname, String Nachname,String Gruppe)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Variable1,Vorname);
        contentValues.put(Variable2, Nachname);
        contentValues.put(Variable3, Gruppe);

        long result = db.insert(Table_name,null,contentValues);

        if(result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }



    public Cursor Select()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+Table_name+";",null);
        return res;
    }

    public Integer Delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(Table_name,"ID = ?",new String[]{id});
    }

    public boolean Update(String id ,String name, String nachname, String gruppe)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(PrimaryKey,id);
        contentValues.put(Variable1,name);
        contentValues.put(Variable2,nachname);
        contentValues.put(Variable3,gruppe);
        db.update(Table_name,contentValues,"ID = ?",new String[]{id});
        return true;
    }
}
