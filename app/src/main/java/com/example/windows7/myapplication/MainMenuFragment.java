package com.example.windows7.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.windows7.myapplication.adapters.MenuListAdapter;

import java.util.ArrayList;

public class MainMenuFragment extends Fragment {

    ArrayList<String> menuArray = new ArrayList<String>();
    Communicator communicator;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_main_menu, container, false);

        menuArray.add("English");
        menuArray.add("Deutsch");


        RecyclerView mRecyclerView = view.findViewById(R.id.initial_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        MenuListAdapter mAdapter = new MenuListAdapter(getActivity(),menuArray);
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
