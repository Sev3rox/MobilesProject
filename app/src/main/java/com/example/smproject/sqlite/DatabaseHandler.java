package com.example.smproject.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;


public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "setingsManager";
    private static final String TABLE_SETINGS = "setings";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MODE = "mode";
    private static final String KEY_MODE2 = "mode2";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        //3rd argument to be passed is CursorFactory instance
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SETINGS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_MODE + " INTERGER,"+ KEY_MODE2 + " INTERGER" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SETINGS);

        // Create tables again
        onCreate(db);
    }

    // code to add the new contact
    public void addSetings(setings setings) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, setings.getName()); // Contact Name
        values.put(KEY_MODE, setings.getMode()); // Contact Phone
        values.put(KEY_MODE2, setings.getMode2());
        // Inserting Row
        db.insert(TABLE_SETINGS, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }

    // code to get the single contact
    setings getSetings(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_SETINGS, new String[] { KEY_ID,
                        KEY_NAME, KEY_MODE, KEY_MODE2 }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        setings setings = new setings(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getInt(2), cursor.getInt(3));
        // return contact
        return setings;
    }

    // code to get all contacts in a list view
    public List<setings> getAllSetings() {
        List<setings> contactList = new ArrayList<setings>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SETINGS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                setings contact = new setings();
                contact.setID(Integer.parseInt(cursor.getString(0)));
                contact.setName(cursor.getString(1));
                contact.setMode(cursor.getInt(2));
                contact.setMode2(cursor.getInt(3));
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // code to update the single contact
    public int updateSetings(setings setings) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, setings.getName());
        values.put(KEY_MODE, setings.getMode());
        values.put(KEY_MODE2, setings.getMode2());

        // updating row
        return db.update(TABLE_SETINGS, values, KEY_ID + " = ?",
                new String[] { String.valueOf(setings.getID()) });
    }

    // Deleting single contact
    public void deleteSetings(setings setings) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SETINGS, KEY_ID + " = ?",
                new String[] { String.valueOf(setings.getID()) });
        db.close();
    }

    // Getting contacts Count
    public int getSetingsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_SETINGS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}