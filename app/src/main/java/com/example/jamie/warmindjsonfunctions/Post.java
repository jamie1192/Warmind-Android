package com.example.jamie.warmindjsonfunctions;

import android.os.AsyncTask;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jamie on 4/5/17.
 */

public class Post extends AsyncTask<Void, Void, Void> {


    JsonObject json = null;
    JsonObject summaryJson = null;
    String response;

    String membershipID ="";


    //Character[0]
    String firstCharacterID = "";
    String firstClassType = "";
    String firstCharacterEmblem = "";
    String firstCharacterEmblemBackground = "";
    String firstCharacterLightLevel = "";

    //Character[1]
    String secondCharacterID = "";
    String secondClassType = "";
    String secondCharacterEmblem = "";
    String secondCharacterEmblemBackground = "";
    String secondCharacterLightLevel ="";

    //Character[2]
    String thirdCharacterID = "";
    String thirdClassType = "";
    String thirdCharacterEmblem = "";
    String thirdCharacterEmblemBackground = "";
    String thirdCharacterLightLevel ="";

    //Account
    String playerGrimoire = "";
    String displayName = "";
    String playerUsername;
    Integer consoleChoice;

    private SearchResults currentActivity;

    private boolean success = false;
    private String error = "";

    private String jsonResponse ="";


    public Post(SearchResults currentActivity, String playerUsername, Integer consoleChoice) {

        this.currentActivity = currentActivity;
        this.playerUsername = playerUsername;
        this.consoleChoice = consoleChoice;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String apiKey = "b7139c21a2114d17b538c7a53ceff70d";

            // Endpoint for Gjallarhorn
//            String url = "https://www.bungie.net/platform/Destiny/Manifest/InventoryItem/1274330687/";

            String url = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/"+consoleChoice+"/"+playerUsername+"/";

            //Account summary
//            String url = "https://www.bungie.net/Platform/Destiny/2/Account/4611686018439307322/Summary/";


            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("GET");

            // Set header
            con.setRequestProperty("X-API-KEY", apiKey);

            int responseCode = con.getResponseCode();
            System.out.println("\nSending 'GET' request to Bungie.Net : " + url);
            System.out.println("Response Code : " + responseCode);

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            response = "";

            while ((inputLine = in.readLine()) != null) {
                response += inputLine;
            }

            in.close();

            // Uses Gson - https://github.com/google/gson
            JsonParser parser = new JsonParser();
            json = (JsonObject) parser.parse(response);

            //get membershipID and displayName
            membershipID = (json.getAsJsonArray("Response").get(0).getAsJsonObject().get("membershipId")).getAsString();
            displayName = (json.getAsJsonArray("Response").get(0).getAsJsonObject().get("displayName")).getAsString();

            //get Summary

            String searchDestinyPlayer = "https://www.bungie.net/Platform/Destiny/"+consoleChoice+"/Account/"+membershipID+"/Summary/";

            URL getMembershipIdURL = new URL(searchDestinyPlayer);
            HttpURLConnection con2 = (HttpURLConnection) getMembershipIdURL.openConnection();

            con2.setRequestMethod("GET");

            // Set header
            con2.setRequestProperty("X-API-KEY", apiKey);

            int membershipIdResponseCode = con2.getResponseCode();
            System.out.println("\nSending 'GET' request to Bungie.Net : " + searchDestinyPlayer);
            System.out.println("Response Code : " + membershipIdResponseCode);

            BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
            String inputLine2;
            String membershipResponse = "";

            while ((inputLine2 = in2.readLine()) != null) {
                membershipResponse += inputLine2;
            }

            in.close();

            // Uses Gson - https://github.com/google/gson
            JsonParser membershipIDparser = new JsonParser();
            summaryJson = (JsonObject) membershipIDparser.parse(membershipResponse);

            //get Character[0] ID

//            System.out.println("before loop: "+firstCharacterID);

//            $json->Response->data->characters[0]->characterBase->grimoireScore;

                //Character Slot [0]
                System.out.println("inside");
                playerGrimoire = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().getAsJsonObject("characterBase").get("grimoireScore")).getAsString();
                firstCharacterID = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().getAsJsonObject("characterBase").get("characterId")).getAsString();
                firstClassType = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().getAsJsonObject("characterBase").get("classType")).getAsString();
                System.out.println("First character type: "+playerGrimoire);
                firstCharacterLightLevel = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().getAsJsonObject("characterBase").get("powerLevel")).toString();
                System.out.println("light level: "+ firstCharacterLightLevel);
                firstCharacterEmblem = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().get("emblemPath")).getAsString();
                firstCharacterEmblemBackground = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(0).getAsJsonObject().get("backgroundPath")).getAsString();
                System.out.println("emblem icon link: "+firstCharacterEmblem);

//              Character slot [1]
                secondCharacterID = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(1).getAsJsonObject().getAsJsonObject("characterBase").get("characterId")).getAsString();
                secondCharacterEmblem = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(1).getAsJsonObject().get("emblemPath")).getAsString();
                secondCharacterEmblemBackground = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(1).getAsJsonObject().get("backgroundPath")).getAsString();
                secondCharacterLightLevel = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(1).getAsJsonObject().getAsJsonObject("characterBase").get("powerLevel")).toString();
                secondClassType = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(1).getAsJsonObject().getAsJsonObject("characterBase").get("classType")).getAsString();

                System.out.println("second class type: "+ secondClassType);
//              Character slot [2]
                thirdCharacterID = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(2).getAsJsonObject().getAsJsonObject("characterBase").get("characterId")).getAsString();
                thirdCharacterEmblem = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(2).getAsJsonObject().get("emblemPath")).getAsString();
                System.out.println("getting 3rd emblem icon: "+thirdCharacterEmblem);
                thirdCharacterEmblemBackground = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(2).getAsJsonObject().get("backgroundPath")).getAsString();
                System.out.println("getting 3rd emblem bground: "+thirdCharacterEmblemBackground);
                thirdCharacterLightLevel = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(2).getAsJsonObject().getAsJsonObject("characterBase").get("powerLevel")).toString();
                thirdClassType = (summaryJson.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonArray("characters").get(2).getAsJsonObject().getAsJsonObject("characterBase").get("classType")).getAsString();


            //paste
//            String searchDestinyPlayer = "https://www.bungie.net/Platform/Destiny/"+consoleChoice+"/Account/"+membershipID+"/Summary/";
//
//            URL getMembershipIdURL = new URL(searchDestinyPlayer);
//            HttpURLConnection con2 = (HttpURLConnection) getMembershipIdURL.openConnection();
//
//            con2.setRequestMethod("GET");


            //end



            //Response->trialsOfOsiris->allTime->killsDeathsRatio->basic->displayValue;
            if(summaryJson != null){
                success = true;
            }

            //end summary


        } catch (IOException e) {
            e = e;
        } catch (Exception e) {
            //whatever
            e = e;
        }
        return null;



    }


    //get summary
    @Override
    protected void onPostExecute(Void result) {

        System.out.println("Result?: "+result);

        if(success)
        {

//            currentActivity.showResults(firstCharacterID, firstCharacterEmblem, firstCharacterEmblemBackground,
//                    secondCharacterEmblem, secondCharacterEmblemBackground, thirdCharacterEmblemIcon, thirdCharacterEmblemBackground);
            Toast.makeText(currentActivity, "Retrieving player information..", Toast.LENGTH_SHORT).show();
            System.out.println("inside success");
            currentActivity.firstCharacterData(firstCharacterID, firstCharacterEmblem, firstCharacterEmblemBackground, firstCharacterLightLevel,
                     firstClassType, playerGrimoire, displayName);

            currentActivity.secondCharacterData(secondCharacterID, secondCharacterEmblem, secondCharacterEmblemBackground, secondCharacterLightLevel,
                 secondClassType, playerGrimoire, displayName);

            currentActivity.thirdCharacterData(thirdCharacterID, thirdCharacterEmblem, thirdCharacterEmblemBackground, thirdCharacterLightLevel,
                    thirdClassType, playerGrimoire, displayName);

//            currentActivity.showLightLevel(firstCharacterLightLevel, secondCharacterLightLevel, thirdCharacterLightLevel);
//
//            currentActivity.getClassTypes(firstClassType, secondClassType, thirdClassType);

        }
        else
        {
            Toast.makeText(currentActivity, "Failed: Couldn't find username."+error, Toast.LENGTH_SHORT).show();
        }
    }

}




