package com.example.windows7.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.windows7.myapplication.adapters.MenuListAdapter;

import java.util.ArrayList;

public class ActivityMenuFragment extends Fragment {

    ArrayList<String> menuActivityArray = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_activity_menu, container, false);

        menuActivityArray.add("Numbers");
        menuActivityArray.add("Addition");
        menuActivityArray.add("Subtraction");

        ListView menuActivityView = view.findViewById(R.id.activitylist);
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, menuActivityArray);
        menuActivityView.setAdapter(adapter);



        return view;


    }
}
