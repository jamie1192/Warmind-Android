package com.example.jamie.warmindjsonfunctions;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import static android.view.View.INVISIBLE;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {link secondCharacterFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {link secondCharacterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class secondCharacterFragment extends Fragment {

    String characterID, characterEmblem, characterEmblemBackground, characterLightLevel,
        classType, getPlayerGrimoire, displayName;

    String bungie = "https://bungie.net";




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_character, container, false);
        ProgressBar loadingSpinner2 = (ProgressBar)view.findViewById(R.id.loadingSpinner2);
        TextView lightLevel = (TextView)view.findViewById(R.id.secondLightLevel);
        TextView playerUsername = (TextView)view.findViewById(R.id.playerUsername);
        TextView playerGrimoire = (TextView)view.findViewById(R.id.secondGrimoire);

        ImageView emblemIcon = (ImageView)view.findViewById(R.id.secondEmblemIcon);
        ImageView emblemBackground = (ImageView)view.findViewById(R.id.secondEmblemBackground);

        TextView secondClassType = (TextView)view.findViewById(R.id.secondClassType);

//        ((SearchResults)getActivity()).secondCharacterData(characterID, characterEmblem, characterEmblemBackground,
//                characterLightLevel, classType, playerGrimoire, displayName);


        String star = getResources().getString(R.string.lightIcon, characterLightLevel);
        String appendEmblem = bungie+characterEmblem;
        String appendBackground = bungie+characterEmblemBackground;
        lightLevel.setText(star);
        playerUsername.setText(displayName);
        secondClassType.setText(classType);
        playerGrimoire.setText(getPlayerGrimoire);

//        loadingSpinner2.setVisibility(View.VISIBLE);
//        new downloadImage(getActivity(), emblemIcon).execute(appendEmblem);
//        loadingSpinner2.setVisibility(INVISIBLE);
//        new downloadImage(getActivity(), emblemBackground).execute(appendBackground);
        Picasso.with(getActivity())
                .load(appendEmblem)
                .into(emblemIcon);

        Picasso.with(getActivity())
                .load(appendBackground)
                .into(emblemBackground);

        return view;
    }






}
