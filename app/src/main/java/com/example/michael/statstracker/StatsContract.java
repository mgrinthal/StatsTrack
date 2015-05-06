package com.example.michael.statstracker;

import android.provider.BaseColumns;

/**
 * Created by Michael on 5/5/2015.
 */
public final class StatsContract {

    public static final  int    DATABASE_VERSION   = 1;
    public static final  String DATABASE_NAME      = "database.db";
    private static final String TEXT_TYPE          = " TEXT";
    private static final String COMMA_SEP          = ",";

    public StatsContract() {}

    public static abstract class StatsEntry implements BaseColumns {
        public static final String TABLE_NAME = "Statistics";
        public static final String COLUMN_TYPE = "Type";
        public static final String COLUMN_TIME = "Time";

        public static final String CREATE_TABLE = "CREATE TABLE " +
                TABLE_NAME + " (" +
                _ID + " INTEGER PRIMARY KEY," +
                COLUMN_TYPE + TEXT_TYPE + COMMA_SEP +
                COLUMN_TIME + TEXT_TYPE + ")";
        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
