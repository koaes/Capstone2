package com.example.windows7.myapplication;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.windows7.myapplication.data.MathProblem;
import com.google.android.exoplayer2.DefaultRenderersFactory;
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
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class CountingFragment extends Fragment {


    private String lang;
    private int firstQuestion;
    private TextView number;
    private Button next;
    private Button english;
    private Button german;
    SimpleExoPlayer exoPlayer;
    Uri uri;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_counting, container, false);


        Bundle bundle = getArguments();
        lang = bundle.getString("Lang");

        number = view.findViewById(R.id.textFirstNumber);
        english = view.findViewById(R.id.button2);
        german = view.findViewById(R.id.button1);
        next = view.findViewById(R.id.button3);

        getProblem();


        english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playEnglishNumber(firstQuestion);
            }
        });


        german.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                playGermanNumber(firstQuestion);

            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getProblem();

            }
        });



        return view;
    }




    private void getProblem(){

        MathProblem mathProblem = new MathProblem();
        firstQuestion = mathProblem.getFirstInt();
        number.setText(Integer.toString(firstQuestion));
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
