package com.example.renkai.login_test;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Gedan3 extends AppCompatActivity {

    private ImageView bofangOrzanting;
    private ImageButton next;
    private ImageButton last;

    private SeekBar seekBar1;
    private MediaPlayer player;
    int cur = 0;
    private int[] resList = new int[]{R.raw.class3_1,R.raw.class3_2,R.raw.class3_3,R.raw.class3_4,R.raw.class3_5,R.raw.class3_6};

    private List<Gequ> gequList = new ArrayList<Gequ>();


    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.yinyue);

        init();
        findView();
        setListener();

        initGedan(); // 初始化歌单
        GedanAdapter adapter = new GedanAdapter(Gedan3.this, R.layout.item_layout, gequList);
        ListView listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        cur=0;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;

                    case 1:
                        cur=1;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;

                    case 2:
                        cur=2;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;

                    case 3:
                        cur=3;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;

                    case 4:
                        cur=4;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;
                    case 5:
                        cur=5;
                        if(player != null){
                            player.release();
                        }
                        player = MediaPlayer.create(Gedan3.this,resList[cur]);
                        seekBar1.setMax(player.getDuration());
                        player.start();
                        bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                        handler.post(updateThread);
                        break;
                }
            }
        });

    }

    private void init() {
//        player = MediaPlayer.create(this, R.raw.sound1);
        player = MediaPlayer.create(Gedan3.this,resList[cur]);
//        player.setLooping(true);
    }

    private void findView() {
        next=(ImageButton)findViewById(R.id.next);
        last=(ImageButton)findViewById(R.id.last);
        bofangOrzanting=(ImageView)findViewById(R.id.button);
        seekBar1 = (SeekBar)findViewById(R.id.seekBar1);
        //获得歌曲的长度并设置成播放进度条的最大值
        seekBar1.setMax(player.getDuration());
    }

    Handler handler = new Handler();
    Runnable updateThread = new Runnable(){
        public void run() {
            //获得歌曲现在播放位置并设置成播放进度条的值
            seekBar1.setProgress(player.getCurrentPosition());
            //每次延迟100毫秒再启动线程
            handler.postDelayed(updateThread, 100);
        }
    };

    private void setListener() {
        bofangOrzanting.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){

                if(player==null){//如果没有歌
                    player = MediaPlayer.create(Gedan3.this, resList[cur]);
                    player.start();
                    bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                    //启动
                    handler.post(updateThread);}
                else if(player.isPlaying()){//如果正在播放
                    //暂停
                    player.pause();
                    //开始图标
                    bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.play));
                    handler.removeCallbacks(updateThread);
                }else {
                    //开始播放
                    player.start();
                    //暂停图标
                    bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                    handler.post(updateThread);
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur = (cur + 1)%resList.length;
                if(player != null){
                    player.release();
                }
                player = MediaPlayer.create(Gedan3.this,resList[cur]);
                player.start();
                bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                handler.post(updateThread);
            }
        });
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cur = (cur + 5)%resList.length;
                if(player != null){
                    player.release();
                }
                player = MediaPlayer.create(Gedan3.this,resList[cur]);
                player.start();
                bofangOrzanting.setImageDrawable(getResources().getDrawable(R.drawable.stop));
                handler.post(updateThread);
            }
        });

        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // fromUser判断是用户改变的滑块的值
                if(fromUser==true){
                    player.seekTo(progress);
                }
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });
    }

    private void initGedan() {
        Gequ song1 = new Gequ("Starry sky",1);
        gequList.add(song1);
        Gequ song2 = new Gequ("Meniscus",2);
        gequList.add(song2);
        Gequ song3 = new Gequ("Empty space",3);
        gequList.add(song3);
        Gequ song4 = new Gequ("Falling flower",4);
        gequList.add(song4);
        Gequ song5 = new Gequ("Calm and calm",5);
        gequList.add(song5);
        Gequ song6 = new Gequ("Cure insomnia",6);
        gequList.add(song6);


    }

    public void finish() {
        // TODO Auto-generated method stub
        if(player.isPlaying()){
            player.stop();
        }
        player.release();//释放资源
//        Intent intent = new Intent();
//        intent.setClass(Gedan3.this, Music.class);
//        startActivity(intent);
    }




}
