package com.example.vaio.buoi17;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.t3h.adapter.ListViewSongAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.InvalidPreferencesFormatException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    private ArrayList<ItemSong> arrItemSong;
    private ListView lvSong;
    private ListViewSongAdapter myAdapter;
    private MediaManager mediaManager;

    private ImageView btnPrevious;
    private ImageView btnPause;
    private ImageView btnNext;
    private TextView tvDuration;
    //    private boolean isRunning = true;
    private SeekBar seekBar;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ImageView previousView;

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }


        setContentView(R.layout.activity_main);
        MyContentProvider myContentProvider = new MyContentProvider(this);
        arrItemSong = myContentProvider.getData();
        mediaManager = new MediaManager(this, arrItemSong);
        initViews();
    }

    //    private AsyncTask<Void, Void, Void> asyncTask = new AsyncTask<Void, Void, Void>() {
//
//        @Override
//        protected Void doInBackground(Void... params) {
//            while (isRunning) {
//                publishProgress();
//            }
//            return null;
//        }
//
//        @Override
//        protected void onProgressUpdate(Void... values) {
//            super.onProgressUpdate(values);
//            seekBar.setMax(mediaManager.getDuration());
//            seekBar.setProgress(mediaManager.getCurrentDuration());
////            if(mediaManager.getCurrentDuration() == mediaManager.getDuration()){
////                isRunning = false;
////            }
//        }
//    };
    private Handler handler = new Handler();
    private Timer timer = new Timer();
    private TimerTask task = new TimerTask() {
        @Override
        public void run() {

            handler.post(new Runnable() {
                @Override
                public void run() {
                    int current = mediaManager.getCurrentDuration();
                    int max = mediaManager.getDuration();
                    seekBar.setMax(max);
                    seekBar.setProgress(current);

                    int current_time= current/1000;
                    int curent_minutes = current_time/60;
                    int curent_seconds = current_time%60;

                    int max_time = max/1000;
                    int max_minutes = max_time/60;
                    int max_second = max_time%60;
                    tvDuration.setText(curent_minutes+" : "+curent_seconds+" / "+max_minutes+" : "+max_second);
                }
            });

        }
    };

    private void initViews() {
        //
        drawerLayout = (DrawerLayout) findViewById(R.id.activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerToggle.syncState();
        drawerLayout.setDrawerListener(drawerToggle);
        //
        tvDuration = (TextView) findViewById(R.id.duration);
        btnNext = (ImageView) findViewById(R.id.next);
        btnNext.setOnClickListener(this);
        btnPause = (ImageView) findViewById(R.id.play_pause);
        btnPause.setOnClickListener(this);
        btnPrevious = (ImageView) findViewById(R.id.previous);
        btnPrevious.setOnClickListener(this);
        lvSong = (ListView) findViewById(R.id.lv_song);
        myAdapter = new ListViewSongAdapter(this, arrItemSong);
        lvSong.setAdapter(myAdapter);
        lvSong.setOnItemClickListener(this);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaManager.seekTo(progress);
                    seekBar.setProgress(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                Toast.makeText(MainActivity.this,"asfd",Toast.LENGTH_SHORT).show();
//                mediaManager.seekTo(seekBar.getProgress());
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        asyncTask.execute();
        timer.schedule(task, 0,1000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        drawerToggle.onOptionsItemSelected(item);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//        if (previousView != null) {
//            previousView.clearAnimation();
//        }
//        view.setBackgroundColor(Color.GRAY);
        btnPause.setImageResource(android.R.drawable.ic_media_pause);
//        RotateAnimation animation = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.rotate);
//        previousView = (ImageView) view.findViewById(R.id.icon);
//        TextView tv = (TextView) view.findViewById(R.id.title);
//        previousView.startAnimation(animation);
        mediaManager.createSound(position);
        mediaManager.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.previous:
                mediaManager.previous();
                break;
            case R.id.play_pause:
                if (mediaManager.isPlaying()) {
                    btnPause.setImageResource(android.R.drawable.ic_media_play);
                    mediaManager.pause();
                } else {
                    btnPause.setImageResource(android.R.drawable.ic_media_pause);
                    mediaManager.start();
                }
                break;
            case R.id.next:
                mediaManager.next();
                break;

        }
    }
}
