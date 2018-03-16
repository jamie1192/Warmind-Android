package com.example.jamie.warmindjsonfunctions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class FavouriteSearches extends AppCompatActivity {

    SQLiteDatabase UsernamesDB;
    ArrayList<userData> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite_searches);

        UsernamesDB = this.openOrCreateDatabase("UsernamesDB.db", MODE_PRIVATE, null);

        Cursor cursor = UsernamesDB.rawQuery("SELECT * FROM favourites", null);

        TextView favouriteMessage = (TextView)findViewById(R.id.noFavouritesText);

        int usernameCol = cursor.getColumnIndex("username");
        int consoleCol = cursor.getColumnIndex("console");

        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){
            do {
                favouriteMessage.setVisibility(View.INVISIBLE);

                String playerName = cursor.getString(usernameCol);
                String playerConsole = cursor.getString(consoleCol);
                dataList.add(new userData(playerName, playerConsole));

            }while(cursor.moveToNext()); //stops when no more results
        }
        else
        {
            System.out.println("No users to show");
        }

        ListAdapter adapter = new FavouritesListAdapter(this, dataList);

        ListView listView = (ListView)findViewById(R.id.favouritesListView);

        listView.setAdapter(adapter);

    }

    public void getSelectedUser(String username, String console){
        System.out.println("Selected username: "+username+ " and console is "+console);
        Intent searchResult = new Intent(FavouriteSearches.this, SearchResults.class);
        Bundle extras = new Bundle();
        boolean boolToPass = Boolean.parseBoolean(console);
        System.out.println("bool is = "+boolToPass);
        extras.putString("user", username);
        extras.putBoolean("console", boolToPass);
        searchResult.putExtras(extras);
        startActivity(searchResult);
    }

    public void removeFavourite(String username, String console){

        Cursor cursor = UsernamesDB.rawQuery("SELECT * FROM favourites WHERE username = '"+username+"' AND console = '"+console+"'", null);

        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){
            do {
                Toast.makeText(this, "Player removed from favourites.", Toast.LENGTH_SHORT).show();

                UsernamesDB.execSQL("DELETE FROM favourites WHERE username='"+username+"' AND console = '"+console+"'");

            }while(cursor.moveToNext());//returns false when it cant
        }
        else{
            Toast.makeText(this, "An error occurred while trying to remove favourite.", Toast.LENGTH_SHORT).show();
        }

    }
}
