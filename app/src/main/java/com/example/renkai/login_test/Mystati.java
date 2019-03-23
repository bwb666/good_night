package com.example.renkai.login_test;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;

import java.util.ArrayList;

public class Mystati extends AppCompatActivity {


    private LineChart lineChart1, lineChart2;
    private LineData lineData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linerchart);


        initChart1();

    }

    private void initChart1() {

        lineChart1 = (LineChart) findViewById(R.id.spread_line_chart);
        //设置图表的描述
        lineChart1.setDescription("hhhhhh");
        //设置x轴的数据
        int numX = 7;
        String[] riqi={"","","","","","",""};
        //设置y轴的数据
        float[] datas1 = {0.0f,0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,0.0f};//数据

        DBHelper dbHelper = new DBHelper(this, "Data.db", null, 1);
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        Cursor cursor=db.query("sleeptable",null,null, null,null,null,"id desc","0,7");
        Log.i("debug","查询");
        int i=0;
        if(cursor.moveToFirst()) {
            do {
                Integer id = cursor.getInt(cursor.getColumnIndex("id"));
                String date = cursor.getString(cursor.getColumnIndex("riqi"));
                String sleeptime= cursor.getString(cursor.getColumnIndex("sleeptime"));
                Log.i("debug", "id:" + id + "入睡时间:" + date + "睡眠时长:" + sleeptime );
                riqi[i]=date.substring(6,11);
                datas1[i++]= Float.parseFloat(sleeptime);
            } while (cursor.moveToNext());
        }

        cursor.close();




        //设置折线的名称
        LineChartManager2.setLineName("睡眠时长");
        //创建两条折线的图表
        lineData = LineChartManager2.initSingleLineChart(this, lineChart1, numX, datas1,riqi);
        LineChartManager2.initDataStyle(lineChart1, lineData, this);
    }

}
