package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
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
 * {link thirdCharacterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {link thirdCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class thirdCharacterFragment extends Fragment {

    String characterID, characterEmblem, characterEmblemBackground, characterLightLevel, classType,
            getPlayerGrimoire, getDisplayName;

    String bungie = "https://bungie.net";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_third_character, container, false);

        ProgressBar loadingSpinner = (ProgressBar)view.findViewById(R.id.loadingSpinner3);
        TextView lightLevel = (TextView)view.findViewById(R.id.thirdLightLevel);
        TextView playerUsername = (TextView)view.findViewById(R.id.playerUsername);

        ImageView emblemIcon = (ImageView)view.findViewById(R.id.thirdEmblemIcon);
        ImageView emblemBackground = (ImageView)view.findViewById(R.id.thirdEmblemBackground);

        TextView thirdClassType = (TextView)view.findViewById(R.id.thirdClassType);
        TextView playerGrimoire = (TextView)view.findViewById(R.id.thirdGrimoire);

//        ((SearchResults)getActivity()).thirdCharacterData(getCharacterID, getEmblem, getEmblemBackground,
//                getLightLevel, getClassType, getGrimoire, getDisplayName);

        loadingSpinner.setVisibility(View.VISIBLE);
        String appendEmblem = bungie+characterEmblem;
        String appendBackground = bungie+characterEmblemBackground;
        String star = getResources().getString(R.string.lightIcon, characterLightLevel);

        playerUsername.setText(getDisplayName);
        lightLevel.setText(star);
        thirdClassType.setText(classType);
        playerGrimoire.setText(getPlayerGrimoire);

        System.out.println("test 3rd emblem: "+appendEmblem);
        System.out.println("test 3rd emblem: "+appendBackground);
        Picasso.with(getActivity())
                .load(appendEmblem)
                .into(emblemIcon);

        Picasso.with(getActivity())
                .load(appendBackground)
                .into(emblemBackground);
//        new downloadImage(getActivity(), emblemIcon).execute(appendEmblem);
        loadingSpinner.setVisibility(View.INVISIBLE);
//        new downloadImage(getActivity(), emblemBackground).execute(appendBackground);


        return view;
    }

}
