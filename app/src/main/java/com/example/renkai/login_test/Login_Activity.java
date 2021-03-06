package com.example.renkai.login_test;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Login_Activity extends AppCompatActivity implements View.OnClickListener {

    private EditText edit_account, edit_password;
    private TextView text_msg;
    private Button btn_login, btn_register;
    private ImageButton openpwd;
    private boolean flag = false;
    private String account, password;
    private String username;
    private DBHelper dbHelper;
    private SharedPreferences pref;

    private CheckBox Remenberpass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ////////测试 跳过登录
//        Intent intent = new Intent(Login_Activity.this, after_login.class);
//        intent.putExtra("Username","test");
//        startActivity(intent);
//        ///////////////////////////////
        edit_account=(EditText) findViewById(R.id.edit_account);
        edit_password=(EditText) findViewById(R.id.edit_password);
        Remenberpass=(CheckBox)findViewById(R.id.remember);
        pref= getSharedPreferences("data",MODE_PRIVATE);
        boolean isRemember=pref.getBoolean("isRemember",false);

        if(isRemember){
            String userName=pref.getString("username","");
            String password=pref.getString("password","");
            edit_account.setText(userName);
            edit_password.setText(password);
            Remenberpass.setChecked(true);
        }

        init();

    }

    private void init() {
        edit_account = (EditText) findViewById(R.id.edit_account);
        edit_account.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_account.clearFocus();
                }
                return false;
            }
        });
        edit_password = (EditText) findViewById(R.id.edit_password);
        edit_password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    edit_password.clearFocus();
                    InputMethodManager imm =
                            (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edit_password.getWindowToken(), 0);
                }
                return false;
            }
        });
//        text_msg = (TextView) findViewById(R.id.text_msg);
        btn_login = (Button) findViewById(R.id.btn_login);
        btn_register = (Button) findViewById(R.id.btn_register);
        openpwd = (ImageButton) findViewById(R.id.btn_openpwd);
//        text_msg.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        btn_register.setOnClickListener(this);
        openpwd.setOnClickListener(this);
        dbHelper = new DBHelper(this, "Data.db", null, 1);
        Log.i("debugZaiNa","登陆时创建");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (edit_account.getText().toString().trim().equals("") | edit_password.getText().
                        toString().trim().equals("")) {
                    Toast.makeText(this, "Please enter an account or sign up!", Toast.LENGTH_SHORT).show();
                } else {
                    readUserInfo();
                }
                break;
            case R.id.btn_register:
                Intent intent = new Intent(Login_Activity.this, Register_Activity.class);
                startActivity(intent);
                break;
            case R.id.btn_openpwd:
                if (flag == true) {//不可见
                    edit_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    flag = false;
                    openpwd.setBackgroundResource(R.drawable.visible);
                } else {
                    edit_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    flag = true;
                    openpwd.setBackgroundResource(R.drawable.invisible);
                }
                break;
//            case R.id.remember:
//                if(Remenberpass.isChecked()){
//
//                    }
//            case R.id.text_msg:
//                Intent i = new Intent(Login_Activity.this, ForgotInfo_activity.class);
//                startActivity(i);
//                break;
        }
    }


    /**
     * 读取SharedPreferences存储的键值对
     * */
    public void readUsersInfo(){
        SharedPreferences sharedPreferences = getSharedPreferences("UsersInfo",MODE_PRIVATE);
        account = sharedPreferences.getString("username","");
        password = sharedPreferences.getString("password","");
    }
    /**
     * 读取UserData.db中的用户信息
     * */
    protected void readUserInfo() {
        if (login(edit_account.getText().toString(), edit_password.getText().toString())) {
            saveUsersInfo();
//            Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(Login_Activity.this, after_login.class);
            intent.putExtra("Username",edit_account.getText().toString());
            startActivity(intent);
        } else {
            Toast.makeText(this, "The account or password is incorrect, please enter again！！", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 验证登录信息
     * */
    public boolean login(String username, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "Select * from usertable where username=? and password=?";
        Cursor cursor = db.rawQuery(sql, new String[]{username, password});
        if (cursor.moveToFirst()) {
            cursor.close();
            return true;
        }
        return false;
    }

    private void saveUsersInfo() {


        SharedPreferences.Editor editor = getSharedPreferences("data", MODE_PRIVATE).edit();
            if (Remenberpass.isChecked()) {

                editor.putBoolean("isRemember", true);
                editor.putString("username", edit_account.getText().toString());
                editor.putString("password", edit_password.getText().toString());
            } else {
                editor.clear();
            }
            editor.apply();
        }


}
