package com.example.windows7.myapplication.sounds;

import android.content.Context;
import android.net.Uri;

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

public class Sounds {

    SimpleExoPlayer exoPlayer;
    Context context;
    Uri uri;

    public Sounds(Context context) {
        this.context = context;
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

        RenderersFactory renderersFactory = new DefaultRenderersFactory(context, null, DefaultRenderersFactory.EXTENSION_RENDERER_MODE_OFF);
        TrackSelector trackSelector = new DefaultTrackSelector();
        ExtractorsFactory extractorFactory = new DefaultExtractorsFactory();
        DataSource.Factory dataSource = new DefaultDataSourceFactory(context, Util.getUserAgent(context, "ExoPlayerIntro"));
        MediaSource mediaSource = new ExtractorMediaSource(uri, dataSource, extractorFactory, null, null);
        exoPlayer = ExoPlayerFactory.newSimpleInstance(renderersFactory, trackSelector);
        exoPlayer.prepare(mediaSource);
        exoPlayer.setPlayWhenReady(true);
        exoPlayer.release();

    }


}
