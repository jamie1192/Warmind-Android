package com.example.jamie.warmindjsonfunctions;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchHistory extends AppCompatActivity {

    SQLiteDatabase UsernamesDB;
    ArrayList<userData> dataList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_history);

        UsernamesDB = this.openOrCreateDatabase("UsernamesDB.db", MODE_PRIVATE, null);

        Cursor cursor = UsernamesDB.rawQuery("SELECT * FROM history", null);

        //get our column id's
        int idCol = cursor.getColumnIndex("id");
        int usernameCol = cursor.getColumnIndex("username");
        int consoleCol = cursor.getColumnIndex("console");

        //cursor points to 1 row at a time
        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){
            do {
                //cycle through all the rows of sql query and add data to string
                //note how cursor.getString needs a column id
                String playerID = cursor.getString(idCol);
                String playerName = cursor.getString(usernameCol);
                String playerConsole = cursor.getString(consoleCol);
                dataList.add(new userData(playerName, playerConsole));
//                contacts = contacts + contact + "\n"; //add a contact then add a newline
            }while(cursor.moveToNext());//returns false when it cant
        }
        else
        {
            Toast.makeText(this, "No results to show", Toast.LENGTH_SHORT).show();
        }

        ListAdapter adapter = new HistoryListAdapter(this, dataList);

        ListView listView = (ListView)findViewById(R.id.historyListView);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id)
            {
                String selectedUser = ((TextView) view.findViewById(R.id.rowUsername)).getText().toString();
                Toast.makeText(SearchHistory.this, "User: " + selectedUser + " was pressed", Toast.LENGTH_LONG).show();
            }
        });
        //update our textview which will list all of our contacts
//        contactList.setText(contacts);

        //add player stuff to list adapter

    }

    public void getSelectedUser(String username, String console){
        System.out.println("Selected username: "+username+ " and console is "+console);
        Intent searchResult = new Intent(SearchHistory.this, SearchResults.class);
        Bundle extras = new Bundle();
        boolean boolToPass = Boolean.parseBoolean(console);
        System.out.println("bool is = "+boolToPass);
        extras.putString("user", username);
        extras.putBoolean("console", boolToPass);
        searchResult.putExtras(extras);
        startActivity(searchResult);
    }

    public void saveFavouriteUser(String username, String console){

        Cursor cursor = UsernamesDB.rawQuery("SELECT * FROM favourites WHERE username = '"+username+"'", null);

        int usernameCol = cursor.getColumnIndex("username");
        int consoleCol = cursor.getColumnIndex("console");

        cursor.moveToFirst();

        if(cursor != null && (cursor.getCount() > 0)){
            do {
                Toast.makeText(this, "User already saved as favourite.", Toast.LENGTH_SHORT).show();
//                String playerName = cursor.getString(usernameCol);
//                String playerConsole = cursor.getString(consoleCol);
//                dataList.add(new userData(playerName, playerConsole));
//                contacts = contacts + contact + "\n"; //add a contact then add a newline
            }while(cursor.moveToNext());//returns false when it cant
        }
        else
        {
            Toast.makeText(this, "No results to show", Toast.LENGTH_SHORT).show();
            try {
                UsernamesDB.execSQL("INSERT INTO favourites (username, console)" +
                        "VALUES('" + username + "','" + console + "')");
                Toast.makeText(this, "Saved to favourites!", Toast.LENGTH_SHORT).show();
            }
            catch(Exception e){
                Toast.makeText(this, "Database error: couldn't save favourite.", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
