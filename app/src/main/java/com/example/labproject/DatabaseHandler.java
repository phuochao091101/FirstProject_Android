package com.example.labproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "details_main_01";

    public static final String ID_COLUMN = "person_id";
    public static final String NAME_COLUMN = "name";
    public static final String ROLE_COLUMN = "role";
    public static final String PHONE_COLUMN = "phone";
    public static final String EMAIL_COLUMN = "email";
    public static final String PROVINCE_COLUMN = "province";

    public SQLiteDatabase database;

    private static final String DATABASE_CREATE = String.format(
            "CREATE TABLE %s (" +
                    "   %s INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT, " +
                    "   %s TEXT)",
            DATABASE_NAME, ID_COLUMN, NAME_COLUMN, ROLE_COLUMN, EMAIL_COLUMN,PROVINCE_COLUMN,PHONE_COLUMN);

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, 1);
        database = getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);

        Log.v(this.getClass().getName(), DATABASE_NAME + " database upgrade to version " +
                newVersion + " - old data lost");
        onCreate(db);
    }
    public long insertDetails(String name, String email, String role, String province, String phone) {
        ContentValues rowValues = new ContentValues();

        rowValues.put(NAME_COLUMN, name);
        rowValues.put(ROLE_COLUMN, role);
        rowValues.put(PROVINCE_COLUMN, province);
        rowValues.put(EMAIL_COLUMN, email);
        rowValues.put(PHONE_COLUMN, phone);

        return database.insertOrThrow(DATABASE_NAME, null, rowValues);
    }
    public String getDetails() {
        Cursor results = database.query("details_main_01", new String[] {"person_id", "name", "phone", "email","province","role"},
                null, null, null, null, "name");

        String resultText = "";

        results.moveToFirst();
        while (!results.isAfterLast()) {
            int id = results.getInt(0);
            String name = results.getString(1);
            String phone = results.getString(2);
            String email = results.getString(3);
            String province = results.getString(4);

            resultText += id + " " + name + " " + phone + " " + email + " " + province + "\n";

            results.moveToNext();
        }

        return resultText;

    }

    public User searchById(int id){
        Cursor cursor = database.query("details_main_01", new String[] {"person_id", "name", "phone", "email","role","province" }, "person_id"+ "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        User user = new User(
                Integer.parseInt(cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5));
        cursor.close();
        database.close();
        return user;
    }
    // Deleting single user
    public void removeUserById(long id) {
        database = this.getWritableDatabase();
        database.delete("details_main_01", "person_id" + " = ?",
                new String[] { String.valueOf(id) });
        database.close();
    }

    public ArrayList<User> getListUser(){
        ArrayList<User> users=new ArrayList<>();
        Cursor results = database.query("details_main_01", new String[] {"person_id", "name", "phone", "email","role","province"},
                null, null, null, null, "name");

        results.moveToFirst();
        while (!results.isAfterLast()) {
            String resultText = "";
            int id = results.getInt(0);
            String name = results.getString(1);
            String phone = results.getString(2);
            String email = results.getString(3);
            String role = results.getString(4);
            String province = results.getString(5);
            users.add(new User(id,name,phone,email,role,province));
            results.moveToNext();
        }
        return users;
    }

    // Updating single contact
    public int updateUser(User user) {
        database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", user.getName());
        values.put("phone", user.getPhone());
        values.put("email", user.getEmail());
        values.put("role", user.getProvince());
        values.put("province", user.getProvince());

        // updating row
        return database.update("details_main_01",
                values,
                "person_id" + " = ?",
                new String[] { String.valueOf(user.getId()) });
    }

}
