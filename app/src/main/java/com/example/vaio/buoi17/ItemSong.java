package com.example.vaio.buoi17;

/**
 * Created by vaio on 11/2/2016.
 */

public class ItemSong {
    private long id;
    private  String data;
    private float size;
    private float duration;
    private String title;
    private String artist;

    public ItemSong(long id, String data, float size, float duration, String title, String artist) {
        this.id = id;
        this.data = data;
        this.size = size;
        this.duration = duration;
        this.title = title;
        this.artist = artist;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public float getSize() {
        return size;
    }

    public void setSize(float size) {
        this.size = size;
    }

    public float getDuration() {
        return duration;
    }

    public void setDuration(float duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
}
