package com.example.vaio.buoi17;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by vaio on 11/2/2016.
 */

public class MyContentProvider {
    private static final String TAG = "1";
    private ContentResolver contentResolver;
    private ArrayList<ItemSong> arrItemSong = new ArrayList<>();

    public MyContentProvider(Context context) {
        contentResolver = context.getContentResolver();
        getData();
    }

    public ArrayList<ItemSong> getData() {
        Cursor cursor = contentResolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null, null);
        String s[] = cursor.getColumnNames();
        int id = cursor.getColumnIndex(MediaStore.MediaColumns._ID);
        int data = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
        int titile = cursor.getColumnIndex(MediaStore.MediaColumns.TITLE);
        int size = cursor.getColumnIndex(MediaStore.MediaColumns.SIZE);
        int duration = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.DURATION);
        int artist = cursor.getColumnIndex(MediaStore.Audio.Playlists.Members.ARTIST);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            long id_ = cursor.getLong(id);
            String data_ = cursor.getString(data);
            String title_ = cursor.getString(titile);
            float size_ = cursor.getFloat(size);
            float duration_ = cursor.getFloat(duration);
            String artist_ = cursor.getString(artist);


            ItemSong item = new ItemSong(id_, data_, size_, duration_, title_, artist_);
            arrItemSong.add(item);
            cursor.moveToNext();
        }
        cursor.close();
        return arrItemSong;
    }

}
