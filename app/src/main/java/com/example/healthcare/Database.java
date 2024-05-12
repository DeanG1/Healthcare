package com.example.healthcare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

//public class Database extends SQLiteOpenHelper {
//
//    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
//        super(context, name, factory, version);
//    }
//
//    @Override
//    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        String qry1 = "create table users(username text,email text,password text)";
//        sqLiteDatabase.execSQL(qry1);
//    }
//
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//
//    }
//    public void register(String username,String email,String password){
//        ContentValues cv = new ContentValues();
//        cv.put("username",username);
//        cv.put("email",email);
//        cv.put("password",password);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert("users",null,cv);
//        db.close();
//    }
//    public int login(String username, String password){
//        int result = 0;
//        String str[] = new String[2];
//        str[0] = username;
//        str[1] = password;
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
//        if(c.moveToFirst()){
//            result=1;
//        }
//        return result;
//    }
//
//}

//public class Database extends SQLiteOpenHelper {
//    private static final String DATABASE_NAME = "HealthCareDB";
//    private static final int DATABASE_VERSION = 1;
//    private static final String TABLE_USERS = "users";
//
//    // Columns for users table
//    private static final String KEY_USERNAME = "username";
//    private static final String KEY_EMAIL = "email";
//    private static final String KEY_PASSWORD = "password";

    // Columns for doctors table

    // Add other columns as needed for doctors

//    public Database(Context context, String healthcare, Object o, int i) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION);
//    }
//
//
//
//
//    @Override
//    public void onCreate(SQLiteDatabase db) {
//
//       // String qry1 = "create table users(username text,email text,password text)";
////        sqLiteDatabase.execSQL(qry1);
//        // Create users table
//        String createUserTable = "CREATE TABLE " + TABLE_USERS +
//                "(" +
//                KEY_USERNAME + " TEXT PRIMARY KEY," +
//                KEY_EMAIL + " TEXT," +
//                KEY_PASSWORD + " TEXT" +
//                ")";
//        db.execSQL(createUserTable);
//
//
//    }
//
//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
//        onCreate(db);
//    }
//
//    // Methods for user authentication
//    public void register(String username,String email,String password){
//        ContentValues cv = new ContentValues();
//        cv.put("username",username);
//        cv.put("email",email);
//        cv.put("password",password);
//        SQLiteDatabase db = getWritableDatabase();
//        db.insert("users",null,cv);
//        db.close();
//    }
//
//        public int login(String username, String password){
//        int result = 0;
//        String str[] = new String[2];
//        str[0] = username;
//        str[1] = password;
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
//        if(c.moveToFirst()){
//            result=1;
//        }
//        return result;
//    }
//
//}



import androidx.annotation.Nullable;

    public class Database extends SQLiteOpenHelper {

        public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            String qry1 = "create table users(username text,email text,password text)";
            sqLiteDatabase.execSQL(qry1);

            String qry2 = "create table cart(username text,product text,price float,otype text)";
            sqLiteDatabase.execSQL(qry2);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
        public void register(String username,String email,String password){
            ContentValues cv = new ContentValues();
            cv.put("username",username);
            cv.put("email",email);
            cv.put("password",password);
            SQLiteDatabase db = getWritableDatabase();
            db.insert("users",null,cv);
            db.close();
        }
        public int login(String username, String password){
            int result = 0;
            String str[] = new String[2];
            str[0] = username;
            str[1] = password;
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from users where username=? and password=?",str);
            if(c.moveToFirst()){
                result=1;
            }
            return result;
        }

        //Add function for add to cart we pass parameters
        public void addCart(String username, String product, float price, String otype){
            ContentValues cv = new ContentValues();
            cv.put("username", username);
            cv.put("product", product);
            cv.put("price", price);
            cv.put("otype", otype);
            SQLiteDatabase db = getWritableDatabase();
            db.insert("cart",null,cv);
            db.close();
        }

        //Function to check if the item already exists in our database we check by username and product
        public int checkCart(String username, String product){
            int result = 0;
            String str[] = new String[2];
            str[0] = username;
            str[1] = product;
            SQLiteDatabase db = getReadableDatabase();
            Cursor c = db.rawQuery("select * from cart where username = ? and product = ? ", str);
            if(c.moveToFirst()){
                result=1;
            }
            db.close();
            return result;
        }

        public void removeCart(String username, String otype){
            String str[] = new String[2];
            str[0] = username;
            str[1] = otype;
            SQLiteDatabase db = getWritableDatabase();
            db.delete("cart", "username=? and otype=?",str);
            db.close();
        }

        public ArrayList getCartData(String username, String otype){
            ArrayList<String> arr = new ArrayList<>();
            SQLiteDatabase db = getReadableDatabase();
            String str[] = new String[2];
            str[0] = username;
            str[1] = otype;
            Cursor c = db.rawQuery("select * from cart where username = ? and otype = ?",str);
            if(c.moveToFirst()){
                do{
                    String product = c.getString(1);
                    String price = c.getString(2);
                    arr.add(product+"$"+price);
                }
                while(c.moveToNext());
            }
            db.close();
            return arr;
        }
    }
