package com.example.renkai.login_test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by renkai on 17/7/6.
 */

public class UsersInfo_activity extends Activity {


    private TextView text_name;
    private String user_name;
    private LinearLayout gotoclock;
    private LinearLayout gotostatic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userinfo_activity);
        init();

        gotoclock=(LinearLayout)findViewById(R.id.goto_myclock);
        gotostatic=(LinearLayout)findViewById(R.id.goto_mytatic);

       gotoclock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UsersInfo_activity.this, MyClock.class);
                startActivity(intent);
            }
        });

        gotostatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(UsersInfo_activity.this, Mystati.class);
                startActivity(intent);
            }
        });

    }

    protected void init() {
        Intent intent = getIntent();
        user_name = intent.getStringExtra("username");
        text_name = (TextView) findViewById(R.id.text_name);
        text_name.setText(user_name);
    }

}
