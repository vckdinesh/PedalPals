package com.example.pedalpals;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Random;

public class Database extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "pedalpals.db";

    public static final String TABLE_USER = "User";
    public static final String USER_COL_1 = "username";
    public static final String USER_COL_2 = "first_name";
    public static final String USER_COL_3 = "last_name";
    public static final String USER_COL_4 = "email_id";
    public static final String USER_COL_5="room";
    public static final String USER_COL_6="hall";
    public static final String USER_COL_7="passw";
    public static final String USER_COL_8 = "rating";
    public static final String USER_COL_9 = "mobile_number";

    public static final String TABLE_ADMIN = "Admin";
    public static final String ADMIN_COL_1 = "username";
    public static final String ADMIN_COL_2 = "first_name";
    public static final String ADMIN_COL_3 = "last_name";
    public static final String ADMIN_COL_4 = "email_id";
    public static final String ADMIN_COL_5="passw";

    public static final String TABLE_CYCLE = "Cycle";
    public static final String CYCLE_COL_1 = "reg_no";
    public static final String CYCLE_COL_2 = "model";
    public static final String CYCLE_COL_3 = "color";
    public static final String CYCLE_COL_4 = "location";
    public static final String CYCLE_COL_5 = "price";
    public static final String CYCLE_COL_6 = "username";
    public static final String CYCLE_COL_7 = "rating";
    public static final String CYCLE_COL_8 = "cycle_condition";

    public static final String TABLE_LOCATION = "Location";
    public static final String LOCATION_COL_1 = "name";

    public static final String TABLE_TRANSACTION = "Transactions";
    public static final String TRANSACTION_COL_1 = "transaction_id";
    public static final String TRANSACTION_COL_2 = "username";
    public static final String TRANSACTION_COL_3 = "owner";
    public static final String TRANSACTION_COL_4 = "reg_no";
    public static final String TRANSACTION_COL_5 = "start_date";
    public static final String TRANSACTION_COL_6 = "end_date";
    public static final String TRANSACTION_COL_7 = "price_per_day";
    public static final String TRANSACTION_COL_8 = "user_rating";
    public static final String TRANSACTION_COL_9 = "cycle_rating";

    public static final String TABLE_CONTACT = "contact_us";
    public static final String CONTACT_COL_1 = "name";
    public static final String CONTACT_COL_2 = "email_id";
    public static final String CONTACT_COL_3 = "subject";
    public static final String CONTACT_COL_4 = "body";
    public static final String CONTACT_COL_5 = "query_date";

    public Database(Context context){
        super(context, DATABASE_NAME, null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_USER + "(username varchar(30) primary key, " +
                "first_name varchar(30) not null," +
                "last_name varchar(30)," +
                "email_id varchar(40) not null," +
                "room varchar(10) not null," +
                "hall varchar(50) not null," +
                "passw varchar(50) not null," +
                "rating numeric(3,2) default 0,"+
                "mobile_number char(10) not null unique);");

        db.execSQL("create table " + TABLE_ADMIN + "(username varchar(30) primary key, " +
                "first_name varchar(30) not null," +
                "last_name varchar(30)," +
                "email_id varchar(40) not null," +
                "passw varchar(50) not null);" );

        db.execSQL("create table " + TABLE_CYCLE + "(reg_no int primary key," +
                "model varchar(30) not null," +
                "color varchar(20) not null," +
                "location varchar(30) not null," +
                "price int not null," +
                "username varchar(30) not null," +
                "rating numeric(3,2) default 0," +
                "cycle_condition varchar(50) not null," +
                "foreign key(username) references User(username) on delete cascade," +
                "foreign key(location) references Location(name) on delete cascade);" );

        db.execSQL("create table " + TABLE_LOCATION + "(name varchar(30) primary key);");

        db.execSQL("create table " + TABLE_TRANSACTION + "(transaction_id int primary key," +
                "username varchar(30)," +
                "owner varchar(20)," +
                "reg_no varchar(30)," +
                "start_date date," +
                "end_date date," +
                "price_per_day int," +
                "user_rating numeric(3,2) default 0," +
                "cycle_rating numeric(3,2) default 0," +
                "foreign key(username) references User(username) on delete cascade," +
                "foreign key(owner) references User(username) on delete cascade," +
                "foreign key(reg_no) references Cycle(reg_no) on delete cascade);" );

        db.execSQL("create table " + TABLE_CONTACT + "(name varchar(30) not null, " +
                "email_id varchar(40) not null," +
                "subject varchar(50) not null," +
                "body varchar(150) not null," +
                "query_date date not null," +
                "primary key(email_id,subject,query_date));" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_CYCLE);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_LOCATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACT);

        onCreate(db);
    }


    public boolean insertData_User(String first_name,String last_name,String email,String hall,String room, String username, String password, String mobile_number) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(username.isEmpty() || first_name.isEmpty() || email.isEmpty() || password.isEmpty())
            return false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(USER_COL_1, username);
        contentValues.put(USER_COL_2, first_name);
        contentValues.put(USER_COL_3, last_name);
        contentValues.put(USER_COL_4, email);
        contentValues.put(USER_COL_5, room);
        contentValues.put(USER_COL_6, hall);
        contentValues.put(USER_COL_7, password);
        contentValues.put(USER_COL_8, 0);
        contentValues.put(USER_COL_9, mobile_number);
        long result = db.insert(TABLE_USER,null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }


    public boolean insertData_Admin(String first_name,String last_name,String email,String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();

        if(username.isEmpty() || first_name.isEmpty() || email.isEmpty() || password.isEmpty() )
            return false;

        ContentValues contentValues = new ContentValues();
        contentValues.put(ADMIN_COL_1, username);
        contentValues.put(ADMIN_COL_2, first_name);
        contentValues.put(ADMIN_COL_3, last_name);
        contentValues.put(ADMIN_COL_4, email);
        contentValues.put(ADMIN_COL_5, password);
        long result = db.insert(TABLE_ADMIN,null, contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean login_User(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE username=? AND passw=?", new String[]{username, password});
        if (result.getCount() == 1)
            return true;
        else
            return false;
    }

    public boolean login_Admin(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE username=? AND passw=?", new String[]{username, password});
        if (result.getCount() == 1)
            return true;
        else
            return false;
    }

    public Cursor getAllData_Location(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_LOCATION, null);
        return result;
    }

    public Cursor getData_Admin_username(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_ADMIN+" WHERE username=?", new String[]{username});
        return result;
    }

    public Cursor getData_User_username(String username){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE username=?", new String[]{username});
        return result;
    }

    public Cursor getData_User_email_id(String email){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE email_id=?", new String[]{email});
        return result;
    }

    public Cursor getData_User_mobile_number(String mobile_number){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_USER+" WHERE mobile_number=?", new String[]{mobile_number});
        return result;
    }

    public Cursor getAllData_Cycle(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_CYCLE, null);
        return result;
    }

    public Cursor getData_Cycle_reg(String reg_no){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_CYCLE+" WHERE reg_no=?", new String[]{reg_no});
        return result;
    }

    public Cursor getData_Cycle_GetRide(String username, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_CYCLE+" WHERE username!=? AND reg_no NOT IN (SELECT reg_no FROM "+TABLE_TRANSACTION+" WHERE end_date>=?)", new String[]{username, date});
        return result;
    }
}
