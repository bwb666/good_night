package com.example.renkai.login_test;


import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class TabActivity1 extends AppCompatActivity {

    private MediaPlayer mp1;
    private MediaPlayer player;
    private MediaPlayer player1;
    private MediaPlayer player2;
    private ImageView bofang1;
    private ImageView bofang2;
    private ImageView bofang3;
    private ImageView bofang4;
    int count=0;
    private ImageButton music;
    private ImageButton clock;
    private ImageButton tongji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity1);

        clock=(ImageButton)findViewById(R.id.btn_clock);
        music=(ImageButton)findViewById(R.id.btn_music) ;
        tongji=(ImageButton)findViewById(R.id.btn_tongji) ;

        bofang1=(ImageView)findViewById(R.id.bofang1) ;
        bofang2=(ImageView)findViewById(R.id.bofang2) ;
        bofang3=(ImageView)findViewById(R.id.bofang3) ;
        bofang4=(ImageView)findViewById(R.id.bofang4) ;

        mp1=MediaPlayer.create(TabActivity1.this, R.raw.sound1);
        player=MediaPlayer.create(TabActivity1.this, R.raw.sound2);//设置要播放的音频
        player1=MediaPlayer.create(TabActivity1.this, R.raw.sound3);
        player2=MediaPlayer.create(TabActivity1.this, R.raw.sound4);

        try{mp1.prepare();}catch(Exception e){e.printStackTrace();}

       music.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent();
               intent.setClass(TabActivity1.this, Music.class);
               startActivity(intent);
           }
       });

        clock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TabActivity1.this, MyClock.class);
                startActivity(intent);
            }
        });

        tongji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(TabActivity1.this, Mystati.class);
                startActivity(intent);
            }
        });


        bofang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;

                if(count%2==1){
                    bofang1.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                    mp1.start();
                    mp1.setLooping(true);
                }
                else {
                    if(mp1.isPlaying()){
                        mp1.pause();
                    }
                    bofang1.setImageDrawable(getResources().getDrawable(R.drawable.bofang));

                }

            }
        });

        bofang2.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(player==null){//如果没有歌
                    player = MediaPlayer.create(TabActivity1.this, R.raw.sound2);
                    player.start();
                    bofang2.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                     }
                else if(player.isPlaying()){//如果正在播放
                    //暂停
                    player.pause();
                    //开始图标
                    bofang2.setImageDrawable(getResources().getDrawable(R.drawable.bofang));
                }else {
                    //开始播放
                    player.start();
                    //暂停图标
                    bofang2.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                }

            }
        });

        bofang3.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(player1==null){//如果没有歌
                    player1 = MediaPlayer.create(TabActivity1.this, R.raw.sound3);
                    player1.start();
                    bofang3.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                }
                else if(player1.isPlaying()){//如果正在播放
                    //暂停
                    player1.pause();
                    //开始图标
                    bofang3.setImageDrawable(getResources().getDrawable(R.drawable.bofang));
                }else {
                    //开始播放
                    player1.start();
                    //暂停图标
                    bofang3.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                }

            }
        });

        bofang4.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View arg0){
                if(player2==null){//如果没有歌
                    player2 = MediaPlayer.create(TabActivity1.this, R.raw.sound4);
                    player2.start();
                    bofang4.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                }
                else if(player2.isPlaying()){//如果正在播放
                    //暂停
                    player2.pause();
                    //开始图标
                    bofang4.setImageDrawable(getResources().getDrawable(R.drawable.bofang));
                }else {
                    //开始播放
                    player2.start();
                    //暂停图标
                    bofang4.setImageDrawable(getResources().getDrawable(R.drawable.tingzhi));
                }

            }
        });

    }

}
