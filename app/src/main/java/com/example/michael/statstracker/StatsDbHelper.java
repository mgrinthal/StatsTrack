package com.example.michael.statstracker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Michael on 5/5/2015.
 */
public class StatsDbHelper extends SQLiteOpenHelper {

    public StatsDbHelper(Context context) {
        super(context, StatsContract.DATABASE_NAME, null, StatsContract.DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(StatsContract.StatsEntry.CREATE_TABLE);
    }

    // Method is called during an upgrade of the database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(StatsContract.StatsEntry.DELETE_TABLE);
        onCreate(db);
    }
}
