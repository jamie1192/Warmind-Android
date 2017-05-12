package com.example.jamie.warmindjsonfunctions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class SecondActivity extends AppCompatActivity {

    public ListView lv;
    TextView displayMembershipID;
    TextView displayCharacterLightLevel;
    ImageView emblemIcon;
    ImageView emblemBackground;

    String firstCharacterEmblem;

    String bungie = "https://bungie.net";
    String appendEmblem;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        String playerUsername = extras.getString("user");


//        lv = (ListView) findViewById(R.id.list);

        Post n = new Post(SecondActivity.this, playerUsername);
        n.execute();

        displayMembershipID = (TextView)findViewById(R.id.displayMembershipID);
        displayCharacterLightLevel = (TextView)findViewById(R.id.displayCharacterLightLevel);
        emblemIcon = (ImageView)findViewById(R.id.emblemIcon);
        emblemBackground = (ImageView)findViewById(R.id.emblemBackground);
//        emblemIcon = (ImageView)findViewById(R.id.emblemIcon);
//        emblemBackground = (ImageView)findViewById(R.id.emblemBackground);

//        public void dumpJSON(String jsonStr) {
//            outputTextView.setText(jsonStr);
//        }

//            public void outputResults(String membershipId) {
//            displayMembershipID.setText(membershipId);
//        }

    }
    public void dumpJSON(String firstCharacterID, String firstClassType, String firstCharacterEmblem, String firstCharacterEmblemBackground) {
        displayMembershipID.setText(firstCharacterID);
        displayCharacterLightLevel.setText(firstClassType);
        System.out.println("Emblem link: "+firstCharacterEmblem);

        appendEmblem = bungie+firstCharacterEmblem;
//                "\nFirst Character class Type: "+firstClassType+
//                "\nFirst Character Emblem: "+firstCharacterEmblem+
//                "\nFirst Character Background: "+firstCharacterEmblemBackground);

        //<img src="https://bungie.net/common/destiny_content/icons/6ab7743cc8535a1d07a161fb1248ae23.jpg">

        new downloadImage(this, emblemIcon).execute(appendEmblem);


        System.out.println(appendEmblem);

//        try {
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(appendEmblem).getContent());
//            System.out.println("Emblem link: "+firstCharacterEmblem);
//            emblemIcon.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        try {
//
//            Bitmap bitmap = BitmapFactory.decodeStream((InputStream)new URL(firstCharacterEmblemBackground).getContent());
//            emblemBackground.setImageBitmap(bitmap);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    }




}
