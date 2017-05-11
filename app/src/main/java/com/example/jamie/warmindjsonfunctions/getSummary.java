package com.example.jamie.warmindjsonfunctions;

import android.os.AsyncTask;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jamie on 11/5/17.
 */

public class getSummary extends AsyncTask<Void, Void, Void> {


    JsonObject json = null;
    String response;
//    {
//
////        Activity c;
////
////        public netTask(Activity c) {
////        this.c = c;
//    }

    private MainActivity currentActivity;

    TextView outputTextView;

    private boolean success = false;
    private String error = "";

    private String jsonResponse ="";

    public getSummary(MainActivity currentActivity) {
        this.currentActivity = currentActivity;
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String apiKey = "b7139c21a2114d17b538c7a53ceff70d";

            // Endpoint for Gjallarhorn
//            String url = "https://www.bungie.net/platform/Destiny/Manifest/InventoryItem/1274330687/";

            String url = "https://www.bungie.net/Platform/Destiny/SearchDestinyPlayer/2/wheels00769/";

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

            System.out.println();
//                System.out.println

            //Gjallahorn response
//            jsonResponse = (json.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonObject("inventoryItem").get("itemName")).toString();

            //Working account summary
//            jsonResponse = (json.getAsJsonObject("Response").getAsJsonObject("data").get("membershipType")).toString();

            //get membershipID
            jsonResponse = (json.getAsJsonArray("Response").get(0).getAsJsonObject().get("membershipId")).toString();

            JsonArray jArr = json.getAsJsonArray("Response");


            for(JsonElement jE : jArr){
                JsonObject jo = jE.getAsJsonObject();
                //jo.get("membershipId");
            }



            // Toast.makeText(c, json.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonObject("inventoryItem").get("itemName").toString(), Toast.LENGTH_LONG).show();
            //String s = json.getAsJsonObject("Response").getAsJsonObject("data").getAsJsonObject("inventoryItem").get("itemName").toString();
            //Log.d(test, "test message");

            if(jsonResponse != null){
                success = true;
            }



            //  Toast.makeText(currentActivity, s, Toast.LENGTH_SHORT).show();
//            Toast.makeText(currentActivity, test, Toast.LENGTH_SHORT).show();

//            output.setText(s.toString());
            //Gjallarhorn
        } catch (IOException e) {
            e = e;
        } catch (Exception e) {
            //whatever
            e = e;
        }
        return null;



    }

    @Override
    protected void onPostExecute(Void aVoid) {

        //NOTES FROM MATT:
        /*
        doInBackground runs on the background thread, so apparently we aren't allowed to do ui stuff then.
        However, onPreExecute(),onPostExecute(Result) run on the UI thread, so we can do makeText.
        Your toast wasn't working before because you had a local jsonResponse in doInBackground being set and not
        the classes attribute of the same name

        Also you weren't running this async task in main activity

        So I got something to show up at least.
         */

        if(success)
        {
            Toast.makeText(currentActivity, jsonResponse, Toast.LENGTH_LONG).show();

            currentActivity.dumpJSON(response);

            //to see the result, debug and look at response attribute for this class (its big, dont output it all!)

            // Intent nextScreen = new Intent(currentActivity, SecondActivity.class);

            //currentActivity.startActivity(nextScreen);
        }
        else
        {
            Toast.makeText(currentActivity, "Failed: "+error, Toast.LENGTH_SHORT).show();
        }
    }


//    Toast.makeText(currentActivity, "Login successful!", Toast.LENGTH_SHORT).show();
}
