package com.example.renkai.login_test;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TabActivity2 extends AppCompatActivity {

    private ImageButton start_sleep;
    private ImageButton get_up;
    private String sleep_time;
    private String getup_time;
    private DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tabactivity2);

        dbHelper = new DBHelper(this, "Data.db", null, 1);
        Log.i("debug","初始化");
        ////////////////////数据库测试
        SQLiteDatabase db = dbHelper.getWritableDatabase();
//
//
//        ContentValues values = new ContentValues();
//        values.put("riqi","2019/03/22 19:52:54");
//        values.put("sleeptime", "6.8");
//        db.insert("sleeptable", null, values);
//        Log.i("debug","插入");
//        values = new ContentValues();
//        values.put("riqi","2019/03/22 19:53:36");
//        values.put("sleeptime", "7.4");
//        db.insert("sleeptable", null, values);
//        values = new ContentValues();
//        values.put("riqi","2019/03/22 19:54:54");
//        values.put("sleeptime", "6");
//        db.insert("sleeptable", null, values);
//        values = new ContentValues();
//        values.put("riqi","2019/03/22 19:55:54");
//        values.put("sleeptime", "8.3");
//        db.insert("sleeptable", null, values);
//        values = new ContentValues();
//        values.put("riqi","2019/03/22 19:56:54");
//        values.put("sleeptime", "6.5");
//        db.insert("sleeptable", null, values);


//
//        Cursor cursor=db.query("sleeptable",null,"1=1",null,null,null,null);
//        Log.i("debug","查询");
//        if(cursor.moveToFirst()) {
//            do {
//                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
//                String date = cursor.getString(cursor.getColumnIndex("riqi"));
//                String sleeptime= cursor.getString(cursor.getColumnIndex("sleeptime"));
//                Log.i("debug", "id:" + id + "入睡时间:" + date + "睡眠时长:" + sleeptime );
//            } while (cursor.moveToNext());
//        }
//        cursor.close();
//
//        /////////////////////测试结束

        start_sleep=(ImageButton)findViewById(R.id.btn_sleep);


               /* SQLiteDatabase db=dbHelper.getWritableDatabase();

                //查询表的所有数据
                Cursor cursor=db.query("sleeptable",null,null, null,null,null,"id desc","0,7");
                Log.i("debug","查询");
                if(cursor.moveToFirst()) {
                    do {
                        Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                        String date = cursor.getString(cursor.getColumnIndex("riqi"));
                        String sleeptime= cursor.getString(cursor.getColumnIndex("sleeptime"));
                        Log.i("debug", "id:" + id + "入睡时间:" + date + "睡眠时长:" + sleeptime );
                    } while (cursor.moveToNext());
                }

                cursor.close();*/





        start_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");
                sleep_time=format.format(new Date());
                //把入睡时间输入数据库
//                start_sleep_time(sleep_time);
                getTimeMillis(sleep_time);
               // Toast.makeText(TabActivity2.this, sleep_time, Toast.LENGTH_SHORT).show();
                gotoLayout2();
            }
        });
    }

    private void gotoLayout2(){
        setContentView(R.layout.end_sleep);

        get_up = (ImageButton) findViewById(R.id.btn_getup);
        get_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");
                getup_time=format.format(new Date());
                getTimeMillis(getup_time);
//                Toast.makeText(TabActivity2.this,getup_time, Toast.LENGTH_SHORT).show();
                getTimeExpend(sleep_time, getup_time);
                Toast.makeText(TabActivity2.this,getTimeExpend(sleep_time, getup_time), Toast.LENGTH_SHORT).show();
                //把睡眠时长输入数据库
//                during_sleep_time(getTimeExpend(sleep_time, getup_time));
                sleep_Info(sleep_time, getTimeExpend(sleep_time, getup_time));

                gotoLayout1();
            }
        });
    }

    private void gotoLayout1(){
        setContentView(R.layout.tabactivity2);

        start_sleep = (ImageButton) findViewById(R.id.btn_sleep);
        start_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat format=new SimpleDateFormat("yyyy/MM/dd HH:mm");
                sleep_time=format.format(new Date());
                getTimeMillis(sleep_time);
                //Toast.makeText(TabActivity2.this, sleep_time, Toast.LENGTH_SHORT).show();
                gotoLayout2();
            }
        });
    }

    //把入睡时间输入数据库
    private void sleep_Info(String sstime,String shichang) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("riqi", sstime);
        values.put("sleeptime", shichang);

        float sleeptime= Float.parseFloat(shichang);
        if(sleeptime>0.5){
            db.insert("sleeptable", null, values);
            Log.i("debug", "睡觉信息已插入数据库");
        }
        else {Log.i("debug","睡眠时间不足0.5小时");
        Toast.makeText(TabActivity2.this, "Sleep time is less than 0.5 hours, not counting sleep time statistics", Toast.LENGTH_SHORT).show();}
        db.close();
    }

//    //把睡眠时长输入数据库
//    private void during_sleep_time(String shichang) {
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        ContentValues values = new ContentValues();
//        values.put("sleeptime", shichang);
//        db.insert("sleeptable", null, values);
//        Log.i("debug", "睡觉时长已插入数据库");
//        db.close();
//    }


    //    根据时间字符串获取毫秒数

    private long getTimeMillis(String strTime) {
        long returnMillis = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        Date d = null;
        try {
            d = sdf.parse(strTime);
            returnMillis = d.getTime();
        } catch (ParseException e) {
//            Toast.makeText(TabActivity2.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
        return returnMillis;
    }

//    根据毫秒数的差值来计算时间差
    private String getTimeExpend(String startTime, String endTime){
        //传入字串类型 2016/06/28 08:30
        long longStart = getTimeMillis(startTime); //获取开始时间毫秒数
        long longEnd = getTimeMillis(endTime);  //获取结束时间毫秒数
        long longExpend = longEnd - longStart;  //获取时间差

        long longHours = longExpend / (60 * 60 * 1000); //根据时间差来计算小时数
        long longMinutes = (longExpend - longHours * (60 * 60 * 1000)) / (60 * 1000);//根据时间差来计算分钟数
        long longSeconds=(longExpend-longHours*(60*60*1000)-longMinutes*(60*1000))/1000;//秒数
        float min_to_hour= (float) (longMinutes/60.0);
        float during_sstime= (float) (min_to_hour+longHours);

        return  during_sstime+"";
//        return longHours + ":" + longMinutes + ":" + longSeconds;
    }




}
