package com.t3h.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vaio.buoi17.ItemSong;
import com.example.vaio.buoi17.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaio on 11/2/2016.
 */

public class ListViewSongAdapter extends ArrayAdapter {
    private LayoutInflater inflater;
    private ArrayList<ItemSong> arrItemSong;
    public ListViewSongAdapter(Context context, ArrayList<ItemSong> arrItemSong) {
        super(context,android.R.layout.simple_list_item_1,arrItemSong);
        inflater = LayoutInflater.from(context);
        this.arrItemSong = arrItemSong;
    }

    @NonNull
    @Override
    public View getView(int position, View v, ViewGroup parent) {

        ViewHolder viewHolder;
        if(v==null){

            v  = inflater.inflate(R.layout.lv_item_song,parent,false);
            viewHolder = new ViewHolder();

            viewHolder.ivIcon = (ImageView) v.findViewById(R.id.icon);
            viewHolder.tvTitle  = (TextView) v.findViewById(R.id.title);
            viewHolder.tvDuration  = (TextView) v.findViewById(R.id.duration);
            viewHolder.tvSize  = (TextView) v.findViewById(R.id.size);
            viewHolder.tvArtist  = (TextView) v.findViewById(R.id.artist);
            v.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) v.getTag();
        }

        viewHolder.tvTitle.setText(arrItemSong.get(position).getTitle());
        int s = (int) (arrItemSong.get(position).getDuration()/1000);
        String time = s/60+" : "+s%60;
        viewHolder.tvDuration.setText(time);
        viewHolder.tvSize.setText((int)(arrItemSong.get(position).getSize()/(1024*1024)*100)/100.0+" M ");
        viewHolder.tvArtist.setText(arrItemSong.get(position).getArtist());

        return v;
    }
    class ViewHolder{
        ImageView ivIcon;
        TextView tvTitle;
        TextView tvDuration;
        TextView tvSize;
        TextView tvArtist;
    }
}
