package com.example.windows7.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.windows7.myapplication.adapters.MenuListAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

public class MainMenuFragment extends Fragment {

    ArrayList<String> menuArray = new ArrayList<String>();
    Communicator communicator;
    private AdView mAdView;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        ((MainActivity) getActivity()).setActionBarTitle("Learning to Zahlen");


        menuArray.add("English");
        menuArray.add("Deutsch");

        MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544/6300978111");
        mAdView = view.findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        RecyclerView mRecyclerView = view.findViewById(R.id.initial_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        MenuListAdapter mAdapter = new MenuListAdapter(getActivity(),menuArray, MainMenuFragment.this);
        mRecyclerView.setAdapter(mAdapter);

         return view;
    }

    public interface Communicator{
        void respond (int stepPosition);
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);

        if(context instanceof Communicator){
            communicator = (Communicator) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement Communicator.OnItemSelectedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        communicator = null;
    }

    public void callRespond(int position){
        communicator.respond(position);
    }


}
