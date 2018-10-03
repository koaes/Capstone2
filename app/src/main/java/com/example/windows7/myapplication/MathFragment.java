package com.example.windows7.myapplication;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.windows7.myapplication.data.AppDatabase;
import com.example.windows7.myapplication.data.MathProblem;
import com.example.windows7.myapplication.data.Stats;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;


public class MathFragment extends android.support.v4.app.Fragment{

    TextView first;
    TextView second;
    TextView operation;
    EditText edit;
    int firstQuestion;
    int secondQuestion;
    int answer;
    SharedPreferences sharedPreferences;

    private AppDatabase mDb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);

        mDb = AppDatabase.getInstance(getActivity());

        first = view.findViewById(R.id.textFirstNumber);
        operation = view.findViewById(R.id.textOperation);
        second = view.findViewById(R.id.textSecondNumber);
        edit = view.findViewById(R.id.answerText);


        getProblem();

        final Button checkbutton = view.findViewById(R.id.button1);
        checkbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String content = edit.getText().toString();
                answer = Integer.parseInt(content);

                if((firstQuestion+secondQuestion)== answer){
                    Toast.makeText(getContext(), "Congrats!!!", Toast.LENGTH_LONG).show();

                    Date date = new Date();
                    Stats stats = new Stats(date,"addition",firstQuestion,secondQuestion,1);
                    mDb.statsDao().insertStats(stats);

                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("row",Integer.toString(firstQuestion));
                    edit.apply();

                    Log.v("SharedPref", "Testing");

                    AppWidget.sendRefreshBroadcast(getActivity());


                    getProblem();


                } else{
                    Toast.makeText(getContext(), "Try Again" + answer, Toast.LENGTH_LONG).show();


                }

            }
        });

        final Button nextbutton = view.findViewById(R.id.button2);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                StringBuilder result = new StringBuilder(50);
                List<Stats> statsList = mDb.statsDao().loadAllStats();


                for(int i=0; i<statsList.size(); i++){
                    String studydate = statsList.get(i).getStudyDate().toString();
                    String first = Integer.toString(statsList.get(i).getFirst());
                    String second = Integer.toString(statsList.get(i).getSecond());

                    result.append(studydate + "  " + first + "  " + second + "\n");
                }
                Toast.makeText(getContext(), result, Toast.LENGTH_LONG).show();
            }
        });


       // }


        return view;
    }


    private void getProblem(){

        edit.setText("");

        MathProblem mathProblem = new MathProblem();

        firstQuestion = mathProblem.getFirstInt();
        secondQuestion = mathProblem.getSecondInt();

        first.setText(Integer.toString(firstQuestion));
        operation.setText("+");
        second.setText(Integer.toString(secondQuestion));

    }


}
