package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Set;

import static android.view.View.INVISIBLE;

public class SecondActivity extends AppCompatActivity {

    TextView displayMembershipID;
    TextView displayFirstCharacterType, firstClassType, secondClassType, thirdClassType;
    TextView firstLightLevel, secondLightLevel, thirdLightLevel;
    ImageView emblemIcon, secondEmblemIcon, thirdEmblemIcon;
    ImageView emblemBackground, secondEmblemBackground, thirdEmblemBackground;
    ProgressBar loadingSpinner, loadingSpinner2, loadingSpinner3;


    String bungie = "https://bungie.net";
    String appendEmblem, appendSecondEmblem, appendThirdEmblem;
    String appendBackground, appendSecondBackground, appendThirdBackground;
//    Boolean console;

    Integer consoleChoice;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Bundle extras = getIntent().getExtras();
        String playerUsername = extras.getString("user");
        Boolean console = extras.getBoolean("console");

        if (console){
            consoleChoice = 1;
        }
        else {
            consoleChoice = 2;
        }
        System.out.println("Console bool: "+console);

//        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString("name", "Elena");
//
//        editor.putInt("idName", 12);
//        editor.putString(R.string.savedUsername), playerUsername);
//        editor.commit();

        Post n = new Post(SecondActivity.this, playerUsername, consoleChoice);
        n.execute();



        loadingSpinner = (ProgressBar)findViewById(R.id.loadingSpinner);
        loadingSpinner2 = (ProgressBar)findViewById(R.id.loadingSpinner2);
        loadingSpinner3 = (ProgressBar)findViewById(R.id.loadingSpinner3);
        displayMembershipID = (TextView)findViewById(R.id.displayMembershipID);
        displayFirstCharacterType = (TextView)findViewById(R.id.displayFirstCharacterType);
        emblemIcon = (ImageView)findViewById(R.id.emblemIcon);
        emblemBackground = (ImageView)findViewById(R.id.emblemBackground);
        secondEmblemIcon = (ImageView)findViewById(R.id.secondEmblemIcon);
        secondEmblemBackground = (ImageView)findViewById(R.id.secondEmblemBackground);
        thirdEmblemIcon = (ImageView)findViewById(R.id.thirdEmblemIcon);
        thirdEmblemBackground = (ImageView)findViewById(R.id.thirdEmblemBackground);

        firstClassType = (TextView)findViewById(R.id.firstClassType);
        secondClassType = (TextView)findViewById(R.id.secondClassType);
        thirdClassType = (TextView)findViewById(R.id.thirdClassType);

        firstLightLevel = (TextView)findViewById(R.id.firstLightLevel);
        secondLightLevel = (TextView)findViewById(R.id.secondLightLevel);
        thirdLightLevel = (TextView)findViewById(R.id.thirdLightLevel);


    }
    public void showResults(String firstCharacterID, String firstCharacterEmblem, String firstCharacterEmblemBackground, String secondEmblem,
                            String secondCharacterEmblemBackground, String thirdCharacterEmblemIcon, String thirdCharacterEmblemBackground) {
        displayMembershipID.setText(firstCharacterID);
//        System.out.println(firstClassType);
//        if(Integer.parseInt(firstClassType) == 0){
//            displayFirstCharacterType.setText("Titan");
//        }
//        else if(Integer.parseInt(firstClassType) == 1){
//            displayFirstCharacterType.setText("Hunter");
//        }
//        else if(Integer.parseInt(firstClassType) == 2){
//            displayFirstCharacterType.setText("Warlock");
//        }


//        displayCharacterLightLevel.setText(firstClassType);
        System.out.println("Emblem link: "+firstCharacterEmblem);

        appendEmblem = bungie+firstCharacterEmblem;
        appendSecondEmblem = bungie+secondEmblem;
        appendBackground = bungie+firstCharacterEmblemBackground;
        appendSecondBackground = bungie+secondCharacterEmblemBackground;
        appendThirdEmblem = bungie+thirdCharacterEmblemIcon;
        appendThirdBackground = bungie+thirdCharacterEmblemBackground;

        //<img src="https://bungie.net/common/destiny_content/icons/6ab7743cc8535a1d07a161fb1248ae23.jpg">

        new downloadImage(this, emblemIcon).execute(appendEmblem);
        new downloadImage(this, secondEmblemIcon).execute(appendSecondEmblem);
        new downloadImage(this, emblemBackground).execute(appendBackground);
        loadingSpinner.setVisibility(INVISIBLE);
        new downloadImage(this, secondEmblemBackground).execute(appendSecondBackground);
        loadingSpinner2.setVisibility(INVISIBLE);
        new downloadImage(this, thirdEmblemIcon).execute(appendThirdEmblem);
        new downloadImage(this, thirdEmblemBackground).execute(appendThirdBackground);
        loadingSpinner3.setVisibility(INVISIBLE);
        System.out.println(appendEmblem);


    }

    public void showLightLevel(String firstCharacterLightLevel, String secondCharacterLightLevel, String thirdCharacterLightLevel){

        String star = getResources().getString(R.string.lightIcon, firstCharacterLightLevel);
        String star2 = getResources().getString(R.string.lightIcon, secondCharacterLightLevel);
        String star3 = getResources().getString(R.string.lightIcon, thirdCharacterLightLevel);


        firstLightLevel.setText(star);
        secondLightLevel.setText(star2);
        thirdLightLevel.setText(star3);
    }

    public void getClassTypes(String getFirstClassType, String getSecondClassType, String getThirdClassType){

        //First character type
        if(Integer.parseInt(getFirstClassType) == 0){
            firstClassType.setText("Titan");
        }
        else if(Integer.parseInt(getFirstClassType) == 1){
            firstClassType.setText("Hunter");
        }
        else if(Integer.parseInt(getFirstClassType) == 2){
            firstClassType.setText("Warlock");
        }

        //second
        if(Integer.parseInt(getSecondClassType) == 0){
            secondClassType.setText("Titan");
        }
        else if(Integer.parseInt(getSecondClassType) == 1){
            secondClassType.setText("Hunter");
        }
        else if(Integer.parseInt(getSecondClassType) == 2){
            secondClassType.setText("Warlock");
        }

        //third
        if(Integer.parseInt(getThirdClassType) == 0){
            thirdClassType.setText("Titan");
        }
        else if(Integer.parseInt(getThirdClassType) == 1){
            thirdClassType.setText("Hunter");
        }
        else if(Integer.parseInt(getThirdClassType) == 2){
            thirdClassType.setText("Warlock");
        }
    }



}
