package com.bawei.activity.main;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bawei.R;
import com.bawei.fragment.claassify.Frag_Claassify;
import com.bawei.fragment.home.Frag_Home;
import com.bawei.fragment.shopping.Frag_Shopping;
import com.bawei.fragment.user.Frag_User;
import com.bawei.view.MyRadioButton;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private   MyRadioButton rb_home, rb_classify, rb_shopping, rb_user;
    private ArrayList<MyRadioButton> MyRb_arr;
    private String []  rbname=new String[]{"home","classify","shopping","user"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initview();
        initdata();
        onClick();

    }

    private void initdata() {
        MyRb_arr =new ArrayList<>();
        MyRb_arr.add(rb_home);
        MyRb_arr.add(rb_classify);
        MyRb_arr.add(rb_shopping);
        MyRb_arr.add(rb_user);
        setStatus(rb_home.getId());
        getSupportFragmentManager().beginTransaction().add(R.id.Frag,new Frag_Home()).commit();

    }
    private void onClick() {
        rb_home.setOnClickListener(this);
        rb_classify.setOnClickListener(this);
        rb_shopping.setOnClickListener(this);
        rb_user.setOnClickListener(this);
    }
    private void initview() {
        rb_home = (MyRadioButton) findViewById(R.id.rb_home);
        rb_classify = (MyRadioButton) findViewById(R.id.rb_classify);
        rb_shopping = (MyRadioButton) findViewById(R.id.rb_shopping);
        rb_user = (MyRadioButton) findViewById(R.id.rb_user);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rb_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frag,new Frag_Home()).commit();
                break;
            case R.id.rb_classify:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frag,new Frag_Claassify()).commit();
                break;
            case R.id.rb_shopping:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frag,new Frag_Shopping()).commit();
                break;
            case R.id.rb_user:
                getSupportFragmentManager().beginTransaction().replace(R.id.Frag,new Frag_User()).commit();
                break;
        }
        setStatus(v.getId());

    }
    public void setStatus(int id){
        for (int i = 0; i < MyRb_arr.size() ; i++) {
            if(id==MyRb_arr.get(i).getId()){
                MyRb_arr.get(i).setTextColor(Color.RED);
                MyRb_arr.get(i).setBackground(rbname[i],true);
            }else {
                MyRb_arr.get(i).setTextColor(Color.BLACK);
                MyRb_arr.get(i).setBackground(rbname[i],false);
            }


        }

    }
}
