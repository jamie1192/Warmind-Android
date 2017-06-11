package com.example.jamie.warmindjsonfunctions;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SearchHistory extends AppCompatActivity {

    SQLiteDatabase UsernamesDB;
    ArrayList<historyData> dataList = new ArrayList<>();


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
                dataList.add(new historyData(playerName, playerConsole));
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
}
