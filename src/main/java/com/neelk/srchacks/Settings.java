package com.neelk.srchacks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;


public class Settings extends Fragment {

    private Button logOut;
    private ImageView githubLogo;
    private Button steps;


    public Settings() {
        // Required empty public constructor
    }


    public static Settings newInstance() {
        Settings fragment = new Settings();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // logOut = Objects.requireNonNull(getView()).findViewById(R.id.logOut);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        githubLogo = view.findViewById(R.id.githubLogo);
        logOut = (Button) view.findViewById(R.id.logOut);
        steps = view.findViewById(R.id.stepsButton);

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getActivity(), MainActivity.class);
                getActivity().startActivity(intent);
            }

        });
            //logout button onclick listener

        githubLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("https://github.com/neelkandlikar/SRCHacks"));
                startActivity(intent);

            }
        });
        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), PedometerActivity.class ));

            }
        });


        return view;

    }


}
