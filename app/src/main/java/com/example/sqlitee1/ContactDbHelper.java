package com.example.sqlitee1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;
import android.util.Log;

public class ContactDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="contact_db";
    public static final int DATABASE_VERSION=2;

    public static final String CREAT_TABLE = "create table "+ContactCobtract.ContactEntry.TABLE_NAME+
                "("+ContactCobtract.ContactEntry.CONTACT_ID+" number, "+ ContactCobtract.ContactEntry.NAME+"text,"+ContactCobtract.ContactEntry.EMAIL+
            " text);";
    public static final String DROP_TABLE="drop table if exists "+ContactCobtract.ContactEntry.TABLE_NAME;

    public ContactDbHelper(Context context)
    {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
        //  Log.d("database Operations","Datebase created...");
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(CREAT_TABLE);
        Log.d("database Operations","Table created...");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(DROP_TABLE);
        onCreate(sqLiteDatabase);

    }

    public void addContact(int id, String name,String email,SQLiteDatabase database)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ContactCobtract.ContactEntry.CONTACT_ID,id);
        contentValues.put(ContactCobtract.ContactEntry.NAME,name);
        contentValues.put(ContactCobtract.ContactEntry.EMAIL,email);

        database.insert(ContactCobtract.ContactEntry.TABLE_NAME,null,contentValues);
        Log.d("database Operations","one Raw inserted...");

    }

    public Cursor readContacts(SQLiteDatabase database)
    {
        String[] projections ={ContactCobtract.ContactEntry.CONTACT_ID,ContactCobtract.ContactEntry.NAME,ContactCobtract.ContactEntry.EMAIL};

        Cursor cursor=database.query(ContactCobtract.ContactEntry.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;

    }


    public void updateContact(int id,String name, String email, SQLiteDatabase database)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(ContactCobtract.ContactEntry.NAME,name);
        contentValues.put(ContactCobtract.ContactEntry.EMAIL,email);

        String selection = ContactCobtract.ContactEntry.CONTACT_ID +" = "+id;

        database.update(ContactCobtract.ContactEntry.TABLE_NAME,contentValues,selection,null);


    }





}
