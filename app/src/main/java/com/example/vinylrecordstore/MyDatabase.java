package com.example.vinylrecordstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.vinylrecordstore.models.Products;

import java.util.ArrayList;
import java.util.List;

public class MyDatabase extends SQLiteOpenHelper {
    private static final int DB_VERSION = 10;
    private static final String DB_NAME = "Users";
    private static final String TABLE_Users = "user_details";
    private static final String KEY_ID = "id";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_NAME = "name";
    private static final String KEY_CITY = "city";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_PHONE = "phone";

    private static final String TABLE_Products = "products";
    private static final String PRODUCTS_ID = "id";
    private static final String KEY_ALBUM = "album";
    private static final String KEY_ARTIST = "artist";
    private static final String KEY_PRICE = "price";
    private static final String KEY_IMAGE = "image";

    public MyDatabase(Context context){
        super(context,DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE1 = "CREATE TABLE " + TABLE_Users + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAME + " TEXT,"
                + KEY_ADDRESS + " TEXT,"
                + KEY_CITY + " TEXT,"
                + KEY_PHONE + " TEXT,"
                + KEY_EMAIL + " TEXT,"
                + KEY_PASSWORD + " TEXT"+")";
        db.execSQL(CREATE_TABLE1);
        String CREATE_TABLE2 = "CREATE TABLE " + TABLE_Products + "("
                + PRODUCTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_ALBUM + " TEXT,"
                + KEY_ARTIST + " TEXT,"
                + KEY_PRICE+ " TEXT,"
                + KEY_IMAGE + " INTEGER"+")";
        db.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Users);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_Products);
        onCreate(db);
    }
    void insertUserDetails(String name, String address, String city, String phone, String email, String password){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys

        ContentValues cValues = new ContentValues();
        cValues.put(KEY_NAME, name);
        cValues.put(KEY_ADDRESS, address);
        cValues.put(KEY_CITY, city);
        cValues.put(KEY_PHONE, phone);
        cValues.put(KEY_EMAIL, email);
        cValues.put(KEY_PASSWORD, password);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Users, null, cValues);
        db.close();
    }

    public boolean checkUser(String username, String password){
        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();
        // selection criteria
        String selection = KEY_EMAIL + " = ?" + " AND " + KEY_PASSWORD + " = ?";
        // selection arguments
        String[] selectionArgs = {username, password};
        // query user table with conditions
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com' AND user_password = 'qwerty';
         */
        Cursor cursor = db.query(TABLE_Users, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                       //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if (cursorCount > 0) {
            return true;
        }
        return false;
    }
    public List<Products> getProducts() {

        SQLiteDatabase db = this.getReadableDatabase();
        try {
            onCreate(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<Products> returnList = new ArrayList<>();

        String query = "SELECT * FROM "+ TABLE_Products;

            Cursor cursor = db.rawQuery(query, new String[]{});

            while (cursor.moveToNext()) {
                int id = cursor.getInt(0);
                String album = cursor.getString(1);
                String artist = cursor.getString(2);
                String price = cursor.getString(3);
                int image = cursor.getInt(4);

                Products product =
                        new Products(id, album, artist, price, image);
                returnList.add(product);
            }
            cursor.close();
            db.close();

        return returnList;
    }
    void insertProduct(String album, String artist, String price, Integer image){
        //Get the Data Repository in write mode
        SQLiteDatabase db = this.getWritableDatabase();
        //Create a new map of values, where column names are the keys
        ContentValues cValues = new ContentValues();
        cValues.put(KEY_ALBUM, album);
        cValues.put(KEY_ARTIST, artist);
        cValues.put(KEY_PRICE, price);
        cValues.put(KEY_IMAGE, image);
        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(TABLE_Products, null, cValues);
        db.close();
    }
    public void deleteOne(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_Products, KEY_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
    public void deleteAll()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(" DELETE FROM " + TABLE_Products);
        db.close();
    }

}
