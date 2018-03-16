package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

/**
 * Created by jastl on 12/06/2017.
 */

public class FavouritesListAdapter extends ArrayAdapter<userData> {

    public FavouritesListAdapter(Context context, List<userData> objects) {
        super(context, R.layout.favourites_list_item, R.id.favouriteUsername, objects);
    }

    static class favouritesViewHolder {
        TextView favouriteUsername;
        TextView hiddenConsole;
        ImageView favConsoleIcon;
        ImageButton removeFavourite;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final favouritesViewHolder holder = new favouritesViewHolder();

        LayoutInflater inflater = LayoutInflater.from(getContext());

        //inflating the row layout defined in custom list view
        convertView = inflater.inflate(R.layout.favourites_list_item, null);


        userData item = getItem(position); //getPosition gets item at position from values passed in via the constructor. e.g position = 3, tvShows[3]



        holder.favouriteUsername = (TextView) convertView.findViewById(R.id.favouriteUsername);
        holder.favConsoleIcon = (ImageView) convertView.findViewById(R.id.favConsoleIcon);
        holder.hiddenConsole = (TextView) convertView.findViewById(R.id.hiddenConsoleFavourite);
        holder.removeFavourite = (ImageButton) convertView.findViewById(R.id.remove_favorite_button);
        holder.removeFavourite.setTag(position);
        holder.removeFavourite.setTag(position);
        assert item != null;
        holder.favouriteUsername.setText(item.getUsername());
        holder.hiddenConsole.setText(item.getConsole());

        //work out which console
        if (Objects.equals(item.getConsole(), "true")) {
            holder.favConsoleIcon.setImageResource(R.drawable.xbox);
        } else {
            holder.favConsoleIcon.setImageResource(R.drawable.psn);
        }

        //define an onClickListener for the username to view their stats.
        holder.favouriteUsername.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewGroup row = (ViewGroup) v.getParent();
                String console = ((TextView) row.findViewById(R.id.hiddenConsoleFavourite)).getText().toString();
                String selectedUser = ((TextView) v.findViewById(R.id.favouriteUsername)).getText().toString();

                ((FavouriteSearches) getContext()).getSelectedUser(selectedUser, console);
//
                Toast.makeText(getContext(), selectedUser + " from row " + position + " was pressed. Console is " + console, Toast.LENGTH_SHORT).show();
            }
        });

        //un-favourite user
        holder.removeFavourite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ViewGroup row = (ViewGroup) v.getParent();
                String console = ((TextView) row.findViewById(R.id.hiddenConsoleFavourite)).getText().toString();
                String selectedUser = ((TextView) row.findViewById(R.id.favouriteUsername)).getText().toString();
                ImageView favBtn = (ImageView) v.findViewById(R.id.remove_favorite_button);
                favBtn.setImageResource(R.drawable.btn_star_off_disabled_holo_dark);
                ((FavouriteSearches) getContext()).removeFavourite(selectedUser, console);
//
//                Toast.makeText(getContext(), selectedUser + " from row " + position + " was pressed. Console is " + console, Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;
    }
}
