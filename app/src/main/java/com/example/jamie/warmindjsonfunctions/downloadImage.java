package com.example.jamie.warmindjsonfunctions;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;


/**
 * Created by jamie on 12/5/17.
 */

public class downloadImage extends AsyncTask<String, Void, Bitmap> {

    private Activity currentActivity;

    private ImageView emblemIcon;
    public ProgressBar loadingSpinner;

    public downloadImage(Activity currentActivity, ImageView emblemIcon) {
        this.currentActivity = currentActivity;
        this.emblemIcon = emblemIcon;
    }

    protected void onPreExecute() {
//        Toast.makeText(currentActivity, "Loading Images..", Toast.LENGTH_LONG).show();
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
        //set image imageview
        emblemIcon.setImageBitmap(result);
    }
}

