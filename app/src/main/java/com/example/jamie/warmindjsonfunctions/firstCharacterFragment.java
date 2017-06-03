package com.example.jamie.warmindjsonfunctions;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

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
            getFirstClassType;

    String bungie = "https://bungie.net";
    String appendEmblem = bungie+firstCharacterEmblem;
    String appendBackground = bungie+firstCharacterEmblemBackground;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_first_character, container, false);

        ProgressBar loadingSpinner = (ProgressBar)view.findViewById(R.id.loadingSpinner);
        TextView firstLightLevel = (TextView)view.findViewById(R.id.firstLightLevel);
        ImageView emblemIcon = (ImageView)view.findViewById(R.id.emblemIcon);
        ImageView emblemBackground = (ImageView)view.findViewById(R.id.emblemBackground);

        TextView firstClassType = (TextView)view.findViewById(R.id.firstClassType);



//    public void showResults(String firstCharacterID, String firstCharacterEmblem, String firstCharacterEmblemBackground, String secondEmblem,
//                            String secondCharacterEmblemBackground, String thirdCharacterEmblemIcon, String thirdCharacterEmblemBackground) {
//        displayMembershipID.setText(firstCharacterID);

        ((SearchResults)getActivity()).firstCharacterData(firstCharacterID, firstCharacterEmblem, firstCharacterEmblemBackground, firstCharacterLightLevel,
                getFirstClassType);


        appendEmblem = bungie+firstCharacterEmblem;

        new downloadImage(getActivity(), emblemIcon).execute(appendEmblem);
        loadingSpinner.setVisibility(INVISIBLE);
        new downloadImage(getActivity(), emblemBackground).execute(appendBackground);

//        return inflater.inflate(R.layout.fragment_first_character, container, false);

        return view;
    }
}
