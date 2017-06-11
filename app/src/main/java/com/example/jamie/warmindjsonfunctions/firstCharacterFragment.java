package com.example.jamie.warmindjsonfunctions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static android.view.View.INVISIBLE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {link firstCharacterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {link firstCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class firstCharacterFragment extends Fragment {




    String firstCharacterID, firstCharacterEmblem, firstCharacterEmblemBackground, firstCharacterLightLevel,
            getFirstClassType, getPlayerGrimoire, displayName;

    String bungie = "https://bungie.net";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first_character, container, false);

        ProgressBar loadingSpinner = (ProgressBar)view.findViewById(R.id.loadingSpinner);
        TextView firstLightLevel = (TextView)view.findViewById(R.id.firstLightLevel);
        TextView playerUsername = (TextView)view.findViewById(R.id.playerUsername);
        TextView playerGrimoire = (TextView)view.findViewById(R.id.playerGrimoire);

        ImageView emblemIcon = (ImageView)view.findViewById(R.id.firstEmblemIcon);
        ImageView emblemBackground = (ImageView)view.findViewById(R.id.firstEmblemBackground);

        TextView firstClassType = (TextView)view.findViewById(R.id.firstClassType);

        System.out.println("Created view of frag");


//    public void showResults(String firstCharacterID, String firstCharacterEmblem, String firstCharacterEmblemBackground, String secondEmblem,
//                            String secondCharacterEmblemBackground, String thirdCharacterEmblemIcon, String thirdCharacterEmblemBackground) {
//        displayMembershipID.setText(firstCharacterID);

       //
        // MATT: This section not helping
        // ((SearchResults)getActivity()).firstCharacterData(firstCharacterID, firstCharacterEmblem, firstCharacterEmblemBackground, firstCharacterLightLevel,
          //      getFirstClassType, playerGrimoire, displayName);


        String appendEmblem = bungie+firstCharacterEmblem;
        String appendBackground = bungie+firstCharacterEmblemBackground;
        System.out.println("emblem link: "+ firstCharacterEmblem);
        System.out.println("emblem bground: "+ firstCharacterEmblemBackground);
        String star = getResources().getString(R.string.lightIcon, firstCharacterLightLevel);
        playerUsername.setText(displayName);
        firstClassType.setText(getFirstClassType);
        playerGrimoire.setText(getPlayerGrimoire);
        firstLightLevel.setText(star);
//        appendEmblem = bungie+firstCharacterEmblem;

        System.out.println("First frag icon: "+firstCharacterEmblem);

//        new downloadImage(getActivity(), emblemIcon).execute(appendEmblem);
        Picasso.with(getActivity())
                .load(appendEmblem)
                .into(emblemIcon);

        Picasso.with(getActivity())
                .load(appendBackground)
                .placeholder( R.drawable.progress_animation )
                .into(emblemBackground);
//        new downloadImage(getActivity(), emblemBackground).execute(appendBackground);

//        return inflater.inflate(R.layout.fragment_first_character, container, false);

        loadingSpinner.setVisibility(INVISIBLE);
        return view;
    }
}
