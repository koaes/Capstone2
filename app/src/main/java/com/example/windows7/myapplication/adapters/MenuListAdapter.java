package com.example.windows7.myapplication.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.windows7.myapplication.ActivityMenu;
import com.example.windows7.myapplication.R;
import java.util.ArrayList;



public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.ViewHolder> {

    private ArrayList<String> mDataset;
    Intent intent;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mName;

        public ViewHolder (View v){
            super(v);
            mName = v.findViewById(R.id.txtName);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos = getAdapterPosition();

                    intent = new Intent(context, ActivityMenu.class);
                    //Recipe currentRecipe = mDataset.get(pos);

                    //intent.putExtra("Image", recipeImage);


                    context.startActivity(intent);
                }
            });
        }
    }


    public MenuListAdapter(Context context, ArrayList<String> myDataset) {
        this.mDataset = myDataset;
        this.context = context;
    }

    @NonNull
    @Override
    public MenuListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_cardview_item, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuListAdapter.ViewHolder holder, int position) {

        holder.mName.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

}
