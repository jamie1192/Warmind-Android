package com.example.jamie.warmindjsonfunctions;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

/**
 * Created by jamie on 12/5/17.
 */

class downloadBackground extends AsyncTask<String, Void, Bitmap> {

    private SecondActivity currentActivity;
    private ImageView emblemBackground;

    public downloadBackground(SecondActivity currentActivity, ImageView emblemBackground) {
        this.currentActivity = currentActivity;
        this.emblemBackground = emblemBackground;
    }

    protected void onPreExecute() {

        Toast.makeText(currentActivity, "Loading Images..", Toast.LENGTH_LONG).show();
    }

    protected Bitmap doInBackground(String... urls) {
        String appendEmblem = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(appendEmblem).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error", "image download error");
            Log.e("Error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        //set backgroundImage of emblem
        emblemBackground.setImageBitmap(result);
    }
}

