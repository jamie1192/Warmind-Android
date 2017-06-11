package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by jastl on 11/06/2017.
 */

public class HistoryListAdapter extends ArrayAdapter<historyData> {

    SQLiteDatabase UsernamesDB;

    public HistoryListAdapter(Context context, List<historyData> objects)
    {
        super(context, R.layout.history_list_item, R.id.rowUsername, objects);
    }

    static class ViewHolder
    {
        TextView rowUsername;
        TextView hiddenConsole;
        ImageView consoleIcon;
        ImageButton favouriteUser;

//        public String hiddenConsole(View view) {
//            return ((TextView) view.findViewById(R.id.hiddenConsole)).getText().toString();
//        }
    }


    /*
        getView is called every time a row needs to be generated. Only rows on screen are built, as they scroll onto screen the newly visible rows are generated with getView
        Basically: GetView will be used here to generate 1 row at a time

        position: which item in the list
        convertView: reference to the previous view that is available for reuse
        parent: a container that holds a bunch of views and define their layout properties
    *
     */
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder = new ViewHolder();


        //creating LayoutInflator for inflating the row layout.
//        LayoutInflater inflator = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LayoutInflater inflater = LayoutInflater.from(getContext());

        //inflating the row layout we defined earlier.
        convertView = inflater.inflate(R.layout.history_list_item, null);


        historyData item = getItem(position);//getPosition gets item at position from values passed in via the constructor. e.g position = 3, tvShows[3]


        holder.rowUsername = (TextView)convertView.findViewById(R.id.rowUsername); //get the textView reference from the generated view
        holder.consoleIcon = (ImageView)convertView.findViewById(R.id.consoleIcon);
        holder.hiddenConsole = (TextView)convertView.findViewById(R.id.hiddenConsole);
        holder.favouriteUser = (ImageButton)convertView.findViewById(R.id.favorite_button);
        holder.favouriteUser.setTag(position);
        holder.rowUsername.setTag(position);
        holder.rowUsername.setText(item.getUsername());
        holder.hiddenConsole.setText(item.getConsole());

        if(Objects.equals(item.getConsole(), "true")){
            holder.consoleIcon.setImageResource(R.drawable.xbox);
        }
        else{
            holder.consoleIcon.setImageResource(R.drawable.psn);
        }
        //setting the views into the ViewHolder.
//        holder.rowUsername = (TextView) convertView.findViewById(R.id.tvItemTitle);
//        holder.changeRowStatus = (ImageView) convertView.findViewById(R.id.iStatus);
//        holder.changeRowStatus.setTag(position);

        //define an onClickListener for the ImageView.
        holder.rowUsername.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                ViewGroup row = (ViewGroup) v.getParent();
                String console = ((TextView) row.findViewById(R.id.hiddenConsole)).getText().toString();
                String selectedUser = ((TextView) v.findViewById(R.id.rowUsername)).getText().toString();
//                try {
//                    UsernamesDB.execSQL("INSERT INTO favourites (username, console)" +
//                            "VALUES('" + selectedUser + "','" + console + "')");
//                }
//                catch(Exception e){
//                    Toast.makeText(getContext(), "Error: couldn't save favourite user.", Toast.LENGTH_SHORT).show();
//                }
//                String console = holder.hiddenConsole(v);
                Toast.makeText(getContext(), selectedUser + " from row " + position + " was pressed. Console is " + console, Toast.LENGTH_SHORT).show();
            }
        });

//        holder.checked = (CheckBox) convertView.findViewById(R.id.cbCheckListItem);
//        holder.checked.setTag(position);
//
//        //define an onClickListener for the CheckBox.
//        holder.checked.setOnClickListener(new OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                //assign check-box state to the corresponding object in list.
//                CheckBox checkbox = (CheckBox) v;
//                rowDataList.get(position).setChecked(checkbox.isChecked());
//                Toast.makeText(activity, "CheckBox from row " + position + " was checked", Toast.LENGTH_LONG).show();
//            }
//        });

        //setting data into the the ViewHolder.
//        holder.title.setText(RowData.getName());
//        holder.checked.setChecked(RowData.isChecked());

        //return the row view.
        return convertView;
    }













        //working mine below
        //used to take an xml file and make a code view object out of it
//        LayoutInflater inflater = LayoutInflater.from(getContext());
//
//
//        View view = inflater.inflate(R.layout.history_list_item, parent, false);
//
//
//
//        historyData item = getItem(position);//getPosition gets item at position from values passed in via the constructor. e.g position = 3, tvShows[3]
//        holder.rowUsername = (TextView)
//
//        TextView usernameText = (TextView)view.findViewById(R.id.rowUsername); //get the textView reference from the generated view
//        ImageView consoleIcon = (ImageView)view.findViewById(R.id.consoleIcon);
//        usernameText.setText(item.getUsername());
//
//        if(Objects.equals(item.getConsole(), "true")){
//            consoleIcon.setImageResource(R.drawable.xbox);
//        }
//        else{
//            consoleIcon.setImageResource(R.drawable.psn);
//        }
//
//        return view;
//
//    }

}
