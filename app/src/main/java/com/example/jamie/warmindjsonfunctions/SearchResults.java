package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;



public class SearchResults extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


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



        pager = (ViewPager)findViewById(R.id.pager);
        adapter = new SlideAdapter(getSupportFragmentManager());



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

    public void firstCharacterData(String firstCharacterID, String firstCharacterEmblem, String firstCharacterEmblemBackground, String firstCharacterLightLevel,
                                   String firstClassType, String playerGrimoire, String displayName){

        //MATT: just build and setup fragments as you get data for em
        firstCharacterFragment firstCharacter = new firstCharacterFragment();
        firstCharacter.firstCharacterID = firstCharacterID;
        firstCharacter.firstCharacterEmblem = firstCharacterEmblem;
        firstCharacter.firstCharacterEmblemBackground = firstCharacterEmblemBackground;
        firstCharacter.firstCharacterLightLevel = firstCharacterLightLevel;
        if(Integer.parseInt(firstClassType) == 0){
            firstCharacter.getFirstClassType = "Titan";
        }
        else if(Integer.parseInt(firstClassType) == 1){
            firstCharacter.getFirstClassType = "Hunter";
        }
        else if(Integer.parseInt(firstClassType) == 2){
            firstCharacter.getFirstClassType = "Warlock";
        }

        firstCharacter.getPlayerGrimoire = playerGrimoire;
        firstCharacter.displayName = displayName;



//        Post s = new Post(SearchResults.this, playerUsername, consoleChoice);
//        s.execute();

//TODO - the rest of the values add them to the fragement





        adapter.pages.add(firstCharacter);
        adapter.notifyDataSetChanged();


    }

    public void secondCharacterData(String secondCharacterID, String secondCharacterEmblem, String secondCharacterEmblemBackground, String secondCharacterLightLevel,
                                    String secondClassType, String playerGrimoire, String displayName){

        secondCharacterFragment secondCharacter = new secondCharacterFragment();
        secondCharacter.characterID = secondCharacterID;
        secondCharacter.characterEmblem = secondCharacterEmblem;
        secondCharacter.characterEmblemBackground = secondCharacterEmblemBackground;
        secondCharacter.characterLightLevel = secondCharacterLightLevel;
        if(Integer.parseInt(secondClassType) == 0){
            secondCharacter.classType = "Titan";
        }
        else if(Integer.parseInt(secondClassType) == 1){
            secondCharacter.classType = "Hunter";
        }
        else if(Integer.parseInt(secondClassType) == 2){
            secondCharacter.classType = "Warlock";
        }
        secondCharacter.getPlayerGrimoire = playerGrimoire;
        secondCharacter.displayName = displayName;


//        secondCharacterFragment secondCharacter = new secondCharacterFragment();
        adapter.pages.add(secondCharacter);
        adapter.notifyDataSetChanged();
    }

    public void thirdCharacterData(String thirdCharacterID, String thirdCharacterEmblem, String thirdCharacterEmblemBackground, String thirdLightLevel,
                                   String thirdClassType, String playerGrimoire, String displayName){

        thirdCharacterFragment thirdCharacter = new thirdCharacterFragment();

        thirdCharacter.characterID = thirdCharacterID;

        thirdCharacter.characterEmblem = thirdCharacterEmblem;
        thirdCharacter.characterEmblemBackground = thirdCharacterEmblemBackground;

        thirdCharacter.characterLightLevel = thirdLightLevel;

        if(Integer.parseInt(thirdClassType) == 0){
            thirdCharacter.classType = "Titan";
        }
        else if(Integer.parseInt(thirdClassType) == 1){
            thirdCharacter.classType = "Hunter";
        }
        else if(Integer.parseInt(thirdClassType) == 2){
            thirdCharacter.classType = "Warlock";
        }
        thirdCharacter.getPlayerGrimoire = playerGrimoire;
        thirdCharacter.getDisplayName = displayName;

       // thirdCharacterFragment thirdCharacter = new thirdCharacterFragment();
        adapter.pages.add(thirdCharacter);
        adapter.notifyDataSetChanged();
    }
}
