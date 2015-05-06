package com.example.michael.statstracker;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    protected final String TAG = "MainActivity";
    private String filename = "stat_storage";
    SQLiteDatabase db;
    StringBuilder builder = new StringBuilder("");
    //protected Button start_recording;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start_recording = (Button)findViewById(R.id.begin_button);
        Button end_recording = (Button)findViewById(R.id.end_button);
        Button view_stats = (Button)findViewById(R.id.stats_button);
        StatsDbHelper dbHelper = new StatsDbHelper(getApplicationContext());
        db = dbHelper.getReadableDatabase();
        Log.i(TAG, start_recording.toString());
        start_recording.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startService(new Intent(v.getContext(), CreeperService.class));
            }
        });
        end_recording.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                stopService(new Intent(v.getContext(), CreeperService.class));
            }
        });
        view_stats.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String query = "SELECT * FROM " + StatsContract.StatsEntry.TABLE_NAME;
                //String[] columns = new String[] {StatsContract.StatsEntry._ID, StatsContract.StatsEntry.COLUMN_TYPE, StatsContract.StatsEntry.COLUMN_TIME};
                Cursor cursor = db.rawQuery(query, null);//db.query(StatsContract.StatsEntry.TABLE_NAME, columns, StatsContract.StatsEntry._ID + "=?",);
                if(cursor.moveToFirst()) {
                    do {
                        String event_info = builder.append(cursor.getString(1)).append(" ").append(cursor.getString(2)).toString();
                        builder.setLength(0);
                        Log.i(TAG, event_info);
                    } while(cursor.moveToNext());
                }
                //Intent statsIntent = new Intent(MainActivity.this, StatisticsActivity.class);
                //MainActivity.this.startActivity(statsIntent);
            }
        });

    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this, "OHHH YOU KILLED ME", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
//    public static class PlaceholderFragment extends Fragment {
//
//        public PlaceholderFragment() {
//        }
//
//        @Override
//        public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                                 Bundle savedInstanceState) {
//            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//            return rootView;
//        }
//    }
}
