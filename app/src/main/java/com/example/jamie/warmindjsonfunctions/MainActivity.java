package com.example.jamie.warmindjsonfunctions;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase UsernamesDB = null;

    EditText getPlayerUsername;
    Button searchUserBtn;
    Switch consoleSwitch;

    String playerUsername;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getPlayerUsername = (EditText) findViewById(R.id.playerUsername);
        searchUserBtn = (Button) findViewById(R.id.searchUserBtn);
        consoleSwitch = (Switch) findViewById(R.id.consoleSwitch);

        try{
            //create or open a private database called UsernamesDB.db
            UsernamesDB = this.openOrCreateDatabase("UsernamesDB.db", MODE_PRIVATE, null);
            //if creating a new DB, it won't actually create te db until we do some sql with it
            UsernamesDB.execSQL("CREATE TABLE IF NOT EXISTS history"+
                    "(id integer primary key, username varchar, console varchar, UNIQUE(username));");
            UsernamesDB.execSQL("CREATE TABLE IF NOT EXISTS favourites"+
                    "(id integer primary key, username varchar, console varchar, UNIQUE(username));");
            //check if database was created on phone/tablet file system
            File db = getApplicationContext().getDatabasePath("UsernamesDB.db");

            if(db.exists()){
                Toast.makeText(this, "Database Created!", Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(this, "Database MISSING!", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            Log.e("Creating DB Error", e.getMessage());
        }

    }

    public void searchByUsername(View view) {

        playerUsername = getPlayerUsername.getText().toString();

        if(TextUtils.isEmpty(playerUsername.trim())) {
            Toast.makeText(this, "Please enter a username.", Toast.LENGTH_SHORT).show();
        }
        else {
            //Save search for history list
            try {
                UsernamesDB.execSQL("INSERT INTO history (username, console)" +
                        "VALUES('" + playerUsername + "','" + consoleSwitch.isChecked() + "')");
            }
            catch(Exception e){

            }
            Intent searchResult = new Intent(MainActivity.this, SearchResults.class);
            Bundle extras = new Bundle();
            extras.putString("user", playerUsername);
            extras.putBoolean("console", consoleSwitch.isChecked());
            searchResult.putExtras(extras);
            startActivity(searchResult);
        }
    }
}
