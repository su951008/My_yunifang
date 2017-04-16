package com.bawei.utils;

import android.app.Activity;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import com.bawei.BR;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

import static android.R.attr.checked;
import static android.R.attr.onClick;

/**
 * Created by Administrator on 2017/4/13.
 */

public class MoniToringUtils extends BaseObservable {
    public static boolean isShow = true; //控制显示隐藏编辑
    public double sum = 0;//总价钱


    public int GetCondition() {
        return isShow ? View.VISIBLE : View.GONE;
    }
@Bindable
    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
       notifyChange();
    }





    public interface Compile {
          void onClickCompilelistener();
          void onClickItemDelete();
          void onClickCheckedListener();
    }
     public static  String GetPath(){
         return new String("http://m.yunifang.com/yunifang/mobile/home?random=84831&encode=9dd34239798e8cb22bf99a75d1882447&id=10");
     }
}
