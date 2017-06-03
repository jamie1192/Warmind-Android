package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import static android.view.View.INVISIBLE;

public class SearchResults extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    TextView displayMembershipID;
    TextView displayFirstCharacterType, firstClassType, secondClassType, thirdClassType;
    TextView firstLightLevel, secondLightLevel, thirdLightLevel;
    ImageView emblemIcon, secondEmblemIcon, thirdEmblemIcon;
    ImageView emblemBackground, secondEmblemBackground, thirdEmblemBackground;
//    ProgressBar loadingSpinner, loadingSpinner2, loadingSpinner3;


    String bungie = "https://bungie.net";
    String appendEmblem, appendSecondEmblem, appendThirdEmblem;
    String appendBackground, appendSecondBackground, appendThirdBackground;

    ViewPager pager;
    SlideAdapter adapter;
//    Boolean console;

    Integer consoleChoice;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new SlideAdapter(getSupportFragmentManager());



//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //my paste

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



//        Post n = new Post(SearchResults.this, playerUsername, consoleChoice);
        Post n = new Post(SearchResults.this, playerUsername, consoleChoice);
        n.execute();



//        loadingSpinner = (ProgressBar)findViewById(R.id.loadingSpinner);
//        loadingSpinner2 = (ProgressBar)findViewById(R.id.loadingSpinner2);
//        loadingSpinner3 = (ProgressBar)findViewById(R.id.loadingSpinner3);
//        displayMembershipID = (TextView)findViewById(R.id.displayMembershipID);
//        displayFirstCharacterType = (TextView)findViewById(R.id.displayFirstCharacterType);
//        emblemIcon = (ImageView)findViewById(R.id.emblemIcon);
//        emblemBackground = (ImageView)findViewById(R.id.emblemBackground);
//        secondEmblemIcon = (ImageView)findViewById(R.id.secondEmblemIcon);
//        secondEmblemBackground = (ImageView)findViewById(R.id.secondEmblemBackground);
//        thirdEmblemIcon = (ImageView)findViewById(R.id.thirdEmblemIcon);
//        thirdEmblemBackground = (ImageView)findViewById(R.id.thirdEmblemBackground);
//
//        firstClassType = (TextView)findViewById(R.id.firstClassType);
//        secondClassType = (TextView)findViewById(R.id.secondClassType);
//        thirdClassType = (TextView)findViewById(R.id.thirdClassType);
//
//        firstLightLevel = (TextView)findViewById(R.id.firstLightLevel);
//        secondLightLevel = (TextView)findViewById(R.id.secondLightLevel);
//        thirdLightLevel = (TextView)findViewById(R.id.thirdLightLevel);


        firstCharacterFragment firstCharacter = new firstCharacterFragment();


        adapter.pages.add(firstCharacter);
//        adapter.pages.add(imgFrag);
//        adapter.pages.add(btnFrag);

        pager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(pager, true);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_results, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.recentSearches) {
            Intent searchHistory = new Intent(SearchResults.this, SearchHistory.class);
            startActivity(searchHistory);
        } else if (id == R.id.favouriteUsers) {
            Intent favouriteSearches = new Intent(SearchResults.this, FavouriteSearches.class);
            startActivity(favouriteSearches);
        } else if (id == R.id.switchUser) {
            Intent switchUser = new Intent(SearchResults.this, MainActivity.class);
            startActivity(switchUser);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
//        System.out.println("Emblem link: "+firstCharacterEmblem);
//
//        appendEmblem = bungie+firstCharacterEmblem;
//        appendSecondEmblem = bungie+secondEmblem;
//        appendBackground = bungie+firstCharacterEmblemBackground;
//        appendSecondBackground = bungie+secondCharacterEmblemBackground;
//        appendThirdEmblem = bungie+thirdCharacterEmblemIcon;
//        appendThirdBackground = bungie+thirdCharacterEmblemBackground;

        //<img src="https://bungie.net/common/destiny_content/icons/6ab7743cc8535a1d07a161fb1248ae23.jpg">

        //paste


        Bundle firstCharBundle = new Bundle();
        firstCharBundle.putString("firstCharacterID", firstCharacterID);
        firstCharBundle.putString("firstCharacterEmblem", firstCharacterEmblem);
        firstCharBundle.putString("firstCharacterEmblemBackground", firstCharacterEmblemBackground);

        // set Fragmentclass Arguments
        firstCharacterFragment fragobj = new firstCharacterFragment();
        fragobj.setArguments(firstCharBundle);


//        new downloadImage(this, emblemIcon).execute(appendEmblem);
        new downloadImage(this, secondEmblemIcon).execute(appendSecondEmblem);
        new downloadImage(this, emblemBackground).execute(appendBackground);
//        loadingSpinner.setVisibility(INVISIBLE);
        new downloadImage(this, secondEmblemBackground).execute(appendSecondBackground);
//        loadingSpinner2.setVisibility(INVISIBLE);
        new downloadImage(this, thirdEmblemIcon).execute(appendThirdEmblem);
        new downloadImage(this, thirdEmblemBackground).execute(appendThirdBackground);
//        loadingSpinner3.setVisibility(INVISIBLE);
        System.out.println(appendEmblem);


    }

    public void firstCharacterData(String firstCharacterID, String firstCharacterEmblem, String firstCharacterEmblemBackground, String firstCharacterLightLevel,
                                   String firstClassType){

    }

    public void secondCharacterData(String secondCharacterID, String secondCharacterEmblem, String secondCharacterEmblemBackground, String secondCharacterLightLevel,
                                    String secondClassType){

    }

    public void thirdCharacterData(String thirdCharacterID, String thirdCharacterEmblem, String thirdCharacterEmblemBackground, String thirdLightLevel,
                                   String thirdClassType){

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
