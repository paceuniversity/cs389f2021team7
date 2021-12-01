package com.example.carbonfootprint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String NAME = "PAST_RESULTS_NAME";
    public static final String HOUSEHOLD = "PAST_RESULTS_HOUSEHOLD";
    public static final String HOME_ENERGY = "PAST_RESULTS_HOME_ENERGY";
    public static final String TABLE = "PAST_RESULTS_TABLE";
    public static final String TIME = "PAST_RESULTS_TIME";
    public static final String LOCATION = "PAST_RESULTS_LOCATION";
    public static final String TRANSPORTATION = "PAST_RESULTS_TRANSPORTATION";
    public static final String WASTE = "PAST_RESULTS_WASTE";
    public static final String TOTAL = "PAST_RESULTS_TOTAL";
    public static final String LOCATION_AVG = "PAST_RESULTS_LOCATION_AVG";
    PastResultsData newResultsData;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "PastResult.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTableStatement = "CREATE TABLE " + TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME + " TEXT, " + TIME + " TEXT, " + LOCATION + " TEXT, " + HOUSEHOLD + " TEXT, " + HOME_ENERGY + " TEXT, " + TRANSPORTATION + " TEXT, " + WASTE + " TEXT, " + TOTAL + " TEXT, " + LOCATION_AVG + " TEXT)";

        sqLiteDatabase.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public boolean add(PastResultsData pastResults) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(NAME, pastResults.getName());
        cv.put(TIME, pastResults.getTime());
        cv.put(LOCATION, pastResults.getLocation());
        cv.put(HOUSEHOLD, pastResults.getHousehold());
        cv.put(HOME_ENERGY, pastResults.getHomeEnergy());
        cv.put(TRANSPORTATION, pastResults.getTransportation());
        cv.put(WASTE, pastResults.getWaste());
        cv.put(TOTAL, pastResults.getTotal());
        cv.put(LOCATION_AVG, pastResults.getLocationAvg());

        long insert = db.insert(TABLE, null, cv);
        if (insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    public boolean delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE + " WHERE ID = " + id;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }

    }

    public boolean deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + TABLE;

        Cursor cursor = db.rawQuery(queryString, null);

        if (cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }

    }


    public List<PastResultsData> getEveryone() {
        List<PastResultsData> returnList = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                String time = cursor.getString(2);
                String location = cursor.getString(3);
                String household = cursor.getString(4);
                String homeEnergy = cursor.getString(5);
                String transportation = cursor.getString(6);
                String waste = cursor.getString(7);
                String total = cursor.getString(8);
                String location_avg = cursor.getString(9);

                PastResultsData newResultsData = new PastResultsData(ID, name, time, location, household, homeEnergy, transportation, waste, total, location_avg);
                returnList.add(newResultsData);

            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();

        return returnList;
    }

    public ArrayList<CardViewPastResults> getEverything() {

        ArrayList<CardViewPastResults> cardViewPastResults = new ArrayList<>();

        String queryString = "SELECT * FROM " + TABLE;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {
            do {
                int ID = cursor.getInt(0);
                String name = cursor.getString(1);
                String time = cursor.getString(2);
                String location = cursor.getString(3);
                String household = cursor.getString(4);
                String homeEnergy = cursor.getString(5);
                String transportation = cursor.getString(6);
                String waste = cursor.getString(7);
                String total = cursor.getString(8);
                String location_avg = cursor.getString(9);

                newResultsData = new PastResultsData(ID, name, time, location, household, homeEnergy, transportation, waste, total, location_avg);
                if (Double.valueOf(total) < Double.valueOf(location_avg)) {
                    cardViewPastResults.add(new CardViewPastResults(R.drawable.carbon_footprint_green_icon, name, time));
                }
                else {
                    cardViewPastResults.add(new CardViewPastResults(R.drawable.carbon_footprint_red_icon, name, time));
                }
            } while (cursor.moveToNext());
        }
        else {

        }
        cursor.close();
        db.close();

        return cardViewPastResults;
    }
}
