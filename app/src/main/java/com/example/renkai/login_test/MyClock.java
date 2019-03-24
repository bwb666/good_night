package com.example.renkai.login_test;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MyClock extends AppCompatActivity {
    TextView show1;
    TextView show2;
    TextView show3;
    Button setTime1;
    Button setTime2;
    Button setTime3;
    Button delete1;
    Button delete2;
    Button delete3;
    String show1String = null;
    String show2String = null;
    String show3String = null;
    String defalutString = "Currently no settings";
    AlertDialog builder=null;
    Calendar c=Calendar.getInstance();
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myclock);
        mediaPlayer = MediaPlayer.create(this,R.raw.clockmusic2);
        SharedPreferences settings = getPreferences(Activity.MODE_PRIVATE);
        show1String = settings.getString("TIME1", defalutString);
        show2String = settings.getString("TIME2", defalutString);
        show3String = settings.getString("TIME3", defalutString);

        InitSetTime1();
        InitSetTime2();
        InitSetTime3();
        InitDelete1();
        InitDelete2();
        InitDelete3();

        show1.setText(show1String);
        show2.setText(show2String);
        show3.setText(show3String);
    }
    private void InitSetTime1(){
        show1 =(TextView)findViewById(R.id.show1);
        setTime1 = (Button)findViewById(R.id.settime1);
        setTime1.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour=c.get(Calendar.HOUR_OF_DAY);
                int mMinute=c.get(Calendar.MINUTE);
                new TimePickerDialog(MyClock.this,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute)
                            {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.set(Calendar.SECOND,0);
                                c.set(Calendar.MILLISECOND,0);

                                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                                PendingIntent sender=PendingIntent.getBroadcast(
                                        MyClock.this,0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager)getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS=format(hourOfDay)+"："+format(minute);
                                show1.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MyClock.this,"Set the alarm time to"+tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },mHour,mMinute,true).show();
            }
        });
    }
    private void InitDelete1(){
        delete1 = (Button)findViewById(R.id.delete1);
        delete1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                PendingIntent sender=PendingIntent.getBroadcast(
                        MyClock.this,0, intent, 0);
                AlarmManager am;
                am =(AlarmManager)getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MyClock.this,"Delete the clock",
                        Toast.LENGTH_SHORT).show();
                show1.setText("Currently no settings");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "Currently no settings");
                editor.commit();
            }
        });
    }

    private void InitSetTime2(){
        show2 = (TextView)findViewById(R.id.show2);
        setTime2 = (Button)findViewById(R.id.settime2);
        setTime2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour=c.get(Calendar.HOUR_OF_DAY);
                int mMinute=c.get(Calendar.MINUTE);


                new TimePickerDialog(MyClock.this,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute)
                            {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.set(Calendar.SECOND,0);
                                c.set(Calendar.MILLISECOND,0);

                                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                                PendingIntent sender=PendingIntent.getBroadcast(
                                        MyClock.this,0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager)getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS=format(hourOfDay)+"："+format(minute);
                                show2.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MyClock.this,"Set the alarm time to"+tmpS,
                                        Toast.LENGTH_SHORT)
                                        .show();
                            }
                        },mHour,mMinute,true).show();
            }
        });
    }
    private void InitDelete2(){
        delete2 = (Button)findViewById(R.id.delete2);
        delete2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                PendingIntent sender=PendingIntent.getBroadcast(
                        MyClock.this,0, intent, 0);
                AlarmManager am;
                am =(AlarmManager)getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MyClock.this,"Set the alarm time to", Toast.LENGTH_SHORT).show();
                show2.setText("Currently no settings");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "Currently no settings");
                editor.commit();
            }
        });
    }
    private void InitSetTime3(){
        show3 = (TextView)findViewById(R.id.show3);
        setTime3 = (Button)findViewById(R.id.settime3);
        setTime3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.setTimeInMillis(System.currentTimeMillis());
                int mHour=c.get(Calendar.HOUR_OF_DAY);
                int mMinute=c.get(Calendar.MINUTE);


                new TimePickerDialog(MyClock.this,
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute)
                            {
                                c.setTimeInMillis(System.currentTimeMillis());
                                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                                c.set(Calendar.MINUTE,minute);
                                c.set(Calendar.SECOND,0);
                                c.set(Calendar.MILLISECOND,0);

                                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                                PendingIntent sender=PendingIntent.getBroadcast(
                                        MyClock.this,0, intent, 0);
                                AlarmManager am;
                                am = (AlarmManager)getSystemService(ALARM_SERVICE);
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                                    am.setExact(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), sender);
                                }

                                String tmpS=format(hourOfDay)+"："+format(minute);
                                show3.setText(tmpS);

                                //SharedPreferences保存数据，并提交
                                SharedPreferences time1Share = getPreferences(0);
                                SharedPreferences.Editor editor = time1Share.edit();
                                editor.putString("TIME1", tmpS);
                                editor.commit();

                                Toast.makeText(MyClock.this,"Set the alarm time to"+tmpS, Toast.LENGTH_SHORT).show();
                            }
                        },mHour,mMinute,true).show();
            }
        });
    }
    private void InitDelete3(){
        delete3 = (Button)findViewById(R.id.delete3);
        delete3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyClock.this, CallAlarm.class);
                PendingIntent sender=PendingIntent.getBroadcast(
                        MyClock.this,0, intent, 0);
                AlarmManager am;
                am =(AlarmManager)getSystemService(ALARM_SERVICE);
                am.cancel(sender);
                Toast.makeText(MyClock.this,"Set the alarm time to",
                        Toast.LENGTH_SHORT).show();
                show3.setText("Currently no settings");

                SharedPreferences time1Share = getPreferences(0);
                SharedPreferences.Editor editor = time1Share.edit();
                editor.putString("TIME1", "Currently no settings");
                editor.commit();
            }
        });
    }
//    @Override
//    public boolean onKeyUp(int keyCode, KeyEvent event) {
//        mediaPlayer.stop();
//        if(keyCode == KeyEvent.KEYCODE_BACK){
//            mediaPlayer.stop();
//            builder = new AlertDialog.Builder(MyClock.this)
////
////                    .setTitle("温馨提示：")
////                    .setMessage("您是否要退出程序？")
//                    .setPositiveButton("确定",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog,
//                                                    int whichButton) {
//                                    mediaPlayer.stop();
//                                    MyClock.this.finish();
//                                }
//                            })
//                    .setNegativeButton("取消",
//                            new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog,
//                                                    int whichButton) {
//                                    mediaPlayer.stop();
//                                    builder.dismiss();
//                                }
//                            }).show();
//        }
//        return true;
//    }

    private String format(int x)
    {
        String s=""+x;
        if(s.length()==1) s="0"+s;
        return s;
    }

}
