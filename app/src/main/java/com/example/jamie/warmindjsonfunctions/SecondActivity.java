package com.example.jamie.warmindjsonfunctions;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private ListView lv;
    TextView displayMembershipID;

//    Bundle extras = getIntent().getExtras();
//    String playerUsername = extras.getString("user");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        lv = (ListView) findViewById(R.id.list);

//        Post n = new Post(SecondActivity.this, playerUsername);
//        n.execute();

//        displayMembershipID = (TextView)findViewById(R.id.displayMembershipID);


//        public void dumpJSON(String jsonStr) {
//            outputTextView.setText(jsonStr);
//        }

//            public void outputResults(String membershipId) {
//            displayMembershipID.setText(membershipId);
//        }
    }
    public void dumpJSON(String jsonStr) {
        displayMembershipID.setText(jsonStr);
    }

}
