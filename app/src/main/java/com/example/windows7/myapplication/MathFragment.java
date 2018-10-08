package com.example.windows7.myapplication;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
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
import com.example.windows7.myapplication.sounds.Sounds;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.RenderersFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;


public class MathFragment extends android.support.v4.app.Fragment{

    TextView first;
    TextView second;
    TextView operation;
    EditText edit;
    TextView data;
    int firstQuestion;
    int secondQuestion;
    int answer;
    LiveData<Integer> returnedNumber;
    SharedPreferences sharedPreferences;
    Context context;
    String oper;
    String lang;

    private AppDatabase mDb;

    SimpleExoPlayer exoPlayer;
    Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_math, container, false);


        Bundle bundle = getArguments();
        oper = bundle.getString("Operation");
        lang = bundle.getString("Lang");


        mDb = AppDatabase.getInstance(getActivity());

        first = view.findViewById(R.id.textFirstNumber);
        operation = view.findViewById(R.id.textOperation);
        second = view.findViewById(R.id.textSecondNumber);
        edit = view.findViewById(R.id.answerText);
        data = view.findViewById(R.id.dataText);

        getProblem(oper);


        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("Language", lang);

                if(lang.equals("en")){
                    Log.v("Playing", "Playing English");
                    playEnglishNumber(firstQuestion);
                } else if (lang.equals("de")) {
                    Log.v("Playing", "Playing German");
                    playGermanNumber(firstQuestion);
                } else {
                    Log.v("Playing", "Something went wrong");
                }
            }
        });


        final Button checkbutton = view.findViewById(R.id.button1);
        checkbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String content = edit.getText().toString();
                answer = Integer.parseInt(content);

                if((firstQuestion+secondQuestion)== answer){
                    Toast.makeText(getContext(), "Congrats!!!", Toast.LENGTH_LONG).show();

                    Date date = new Date();
                    final Stats stats = new Stats(date,"addition",firstQuestion,secondQuestion,true);

                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.statsDao().insertStats(stats);
                        }
                    });

                    // Section for gathering info for Widget and sending broadcast
                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString("row",Integer.toString(firstQuestion));
                    edit.apply();
                    AppWidget.sendRefreshBroadcast(getActivity());

                    // Sets up and displays the next problem
                    getProblem(oper);


                } else{
                    Toast.makeText(getContext(), "Try Again" + answer, Toast.LENGTH_LONG).show();

                    Date date = new Date();
                    final Stats stats = new Stats(date,"addition",firstQuestion,secondQuestion,false);
                    AppExecutors.getInstance().diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mDb.statsDao().insertStats(stats);
                        }
                    });

                    getProblem(oper);

                }

            }
        });

        final Button nextbutton = view.findViewById(R.id.button2);
        nextbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                final StringBuilder result = new StringBuilder(50);
                //List<Stats> statsList = mDb.statsDao().loadAllStats();


                //for(int i=0; i<statsList.size(); i++){
                //    String studydate = statsList.get(i).getStudyDate().toString();
                //    String first = Integer.toString(statsList.get(i).getFirst());
                //    String second = Integer.toString(statsList.get(i).getSecond());
                //    result.append(studydate + "  " + first + "  " + second + "\n");
               // }

                //Toast.makeText(getContext(), Integer.toString(mDb.statsDao().getTotal()),Toast.LENGTH_LONG).show();
                //returnedNumber = mDb.statsDao().getTotal();

                MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);

                viewModel.getReturnedNumber().observe(getActivity(), new Observer<Integer>() {
                    @Override
                    public void onChanged(@Nullable Integer integer) {
                        data.setText("");
                        Integer totalNumber = integer;
                        String result = "For today, you have answered " + totalNumber + " questions!!";
                        data.setText(result);
                    }
                });
            }
        });

        return view;
    }


    private void getProblem(String oper){

        edit.setText("");

        MathProblem mathProblem = new MathProblem();

        firstQuestion = mathProblem.getFirstInt();
        secondQuestion = mathProblem.getSecondInt();

        first.setText(Integer.toString(firstQuestion));
        operation.setText(oper);
        second.setText(Integer.toString(secondQuestion));

    }


    public void playEnglishNumber(int numberToPlay) {


        switch (numberToPlay) {
            case 0:
                uri = Uri.parse("asset:///numbers_zero.mp3");
                break;
            case 1:
                uri = Uri.parse("asset:///numbers_one.mp3");
                break;
            case 2:
                uri = Uri.parse("asset:///numbers_two.mp3");
                break;
            case 3:
                uri = Uri.parse("asset:///numbers_three.mp3");
                break;
            case 4:
                uri = Uri.parse("asset:///numbers_four.mp3");
                break;
            case 5:
                uri = Uri.parse("asset:///numbers_five.mp3");
                break;
            case 6:
                uri = Uri.parse("asset:///numbers_six.mp3");
                break;
            case 7:
                uri = Uri.parse("asset:///numbers_seven.mp3");
                break;
            case 8:
                uri = Uri.parse("asset:///numbers_eight.mp3");
                break;
            case 9:
                uri = Uri.parse("asset:///numbers_nine.mp3");
                break;
        }
        initializePlayer(uri);

    }

    public void playGermanNumber(int numberToPlay) {

        switch (numberToPlay) {
            case 0:
                uri = Uri.parse("asset:///numbers_null.mp3");
                break;
            case 1:
                uri = Uri.parse("asset:///numbers_einz.mp3");
                break;
            case 2:
                uri = Uri.parse("asset:///numbers_zwei.mp3");
                break;
            case 3:
                uri = Uri.parse("asset:///numbers_drei.mp3");
                break;
            case 4:
                uri = Uri.parse("asset:///numbers_vier.mp3");
                break;
            case 5:
                uri = Uri.parse("asset:///numbers_funf.mp3");
                break;
            case 6:
                uri = Uri.parse("asset:///numbers_sechs.mp3");
                break;
            case 7:
                uri = Uri.parse("asset:///numbers_seben.mp3");
                break;
            case 8:
                uri = Uri.parse("asset:///numbers_acht.mp3");
                break;
            case 9:
                uri = Uri.parse("asset:///numbers_neun.mp3");
                break;
        }
        initializePlayer(uri);

    }


    private void initializePlayer(Uri uri){

        RenderersFactory renderersFactory = new DefaultRenderersFactory(getActivity(), null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
        TrackSelector trackSelector = new DefaultTrackSelector();
        ExtractorsFactory extractorFactory = new DefaultExtractorsFactory();
        DataSource.Factory dataSource = new DefaultDataSourceFactory(getActivity(), Util.getUserAgent(getActivity(), "ExoPlayerIntro"));
        MediaSource mediaSource = new ExtractorMediaSource(uri, dataSource, extractorFactory, null, null);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);


    }

}
