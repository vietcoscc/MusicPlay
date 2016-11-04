package com.example.vaio.buoi17;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import java.util.ArrayList;

/**
 * Created by vaio on 11/2/2016.
 */

public class MediaManager implements MediaPlayer.OnCompletionListener {
    private MediaPlayer mediaPlayer;
    private Context context;
    private ArrayList<ItemSong> arrItemSong;
    private int index = 0;

    public MediaManager(Context context, ArrayList<ItemSong> arrItemSong) {
        this.context = context;
        this.arrItemSong = arrItemSong;
    }

    public void createSound(int index) {
        this.index = index;
        String data = arrItemSong.get(index).getData();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(context, Uri.parse(data));
        mediaPlayer.setOnCompletionListener(this);
    }

    public void start() {
        if (mediaPlayer != null) {
            mediaPlayer.start();
        }
    }

    public void pause() {
        if (mediaPlayer != null) {
            mediaPlayer.pause();
        }
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public boolean isPlaying() {
        if (mediaPlayer != null) {
            return mediaPlayer.isPlaying();
        }
        return false;
    }

    public void seekTo(int posision) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(posision);
        }
    }

    public void setLooping(boolean looping) {
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(looping);
        }
    }

    public int getCurrentDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public void next() {
        if (index < arrItemSong.size() - 1)
            index++;
        createSound(index);
        mediaPlayer.start();
    }

    public void previous() {
        if (index > 0) {
            index--;
        } else {
            index = arrItemSong.size() - 1;
        }
        createSound(index);
        mediaPlayer.start();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        index++;
        if (index >= arrItemSong.size()) {
            index = 0;
        }
        createSound(index);
    }

    public int getDuration() {
        if (mediaPlayer != null) {
            return mediaPlayer.getDuration();
        }
        return 0;
    }
}
