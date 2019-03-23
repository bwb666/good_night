package com.example.renkai.login_test;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class after_login extends ActivityGroup{

    private TabHost tabHost;//声明一个TabHost对象
    private String name;

    //资源文件
    private Class activitys[]={TabActivity1.class,TabActivity2.class,UsersInfo_activity.class};//跳转的Activity
    private String title[]={"Home","Sleep","My Info"};//设置菜单的标题
    private int image[]={R.drawable.tab1_selector,R.drawable.tab2_selector,R.drawable.tab3_selector};//设置菜单

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab_main);
//        ///////测试 跳过点击
//        Intent intent = new Intent(after_login.this, TabActivity2.class);
//        intent.putExtra("Username","test");
//        startActivity(intent);
//        ///////////////////////
        init();
        initTabView();//初始化tab标签

    }
    protected void init() {
        Intent intent = getIntent();
        name = intent.getStringExtra("Username");
//        text_name = (TextView) findViewById(R.id.text_name);
//        text_name.setText(name);
//        text_condition = (TextView) findViewById(R.id.text_condition);
//        text_condition.setText("在线");
    }

    private void initTabView() {
        //实例化tabhost
        this.tabHost=(TabHost) findViewById(R.id.mytabhost);
        //由于继承了ActivityGroup，所以需要在setup方法里加入此参数，若继承TabActivity则可省略
        tabHost.setup(this.getLocalActivityManager());

        //创建标签
        for(int i=0;i<activitys.length;i++){
            //实例化一个view作为tab标签的布局
            View view=View.inflate(this, R.layout.tab_layout, null);

            //设置imageview
            ImageView imageView=(ImageView) view.findViewById(R.id.image);
            imageView.setImageDrawable(getResources().getDrawable(image[i]));
            //设置textview
            TextView textView=(TextView) view.findViewById(R.id.title);
            textView.setText(title[i]);
            //设置跳转activity
            Intent intent=new Intent(this, activitys[i]);
            intent.putExtra("username",name);

            //载入view对象并设置跳转的activity
            TabSpec spec=tabHost.newTabSpec(title[i]).setIndicator(view).setContent(intent);

            //添加到选项卡
            tabHost.addTab(spec);
        }

    }


}