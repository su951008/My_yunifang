package com.bawei.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.R;

/**
 * Created by Administrator on 2017/4/10.
 */

public class MyRadioButton extends LinearLayout {

    private TextView tv_rb;
    private ImageView im_rb;

    public MyRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MyRadioButton(Context context) {
        super(context);

    }
    public MyRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyRadioButton);
        int anInt = typedArray.getResourceId(R.styleable.MyRadioButton_backgrounds, -1);
        int color = typedArray.getColor(R.styleable.MyRadioButton_setTextColor, Color.BLACK);
        String string = typedArray.getString(R.styleable.MyRadioButton_text);
        View view = inflate(context, R.layout.myradiobutton, this);
        tv_rb = (TextView) view.findViewById(R.id.tv_rb);
        im_rb = (ImageView) view.findViewById(R.id.im_rb);
        im_rb.setBackgroundResource(anInt);//设置背景
        tv_rb.setText(string);//设置字体
        tv_rb.setTextColor(color);//设置颜色
        typedArray.recycle();
    }
    public void setText(String text){
        tv_rb.setText(text);
    }
    public void setBackground(int image){
        im_rb.setBackgroundResource(image);
    }
    public  void setTextColor(int Color){
        tv_rb.setTextColor(Color);
    }
    public void setBackground(String name,boolean  checked){
        switch (name){
            case  "home":
                im_rb.setBackgroundResource(true==checked?R.mipmap.bottom_tab_home_selected:R.mipmap.bottom_tab_home_normal);
                break;
            case  "classify":
                im_rb.setBackgroundResource(true==checked?R.mipmap.bottom_tab_classify_selected:R.mipmap.bottom_tab_classify_normal);
                break;
            case  "shopping":
                im_rb.setBackgroundResource(true==checked?R.mipmap.bottom_tab_shopping_selected:R.mipmap.bottom_tab_shopping_normal);
                break;
            case  "user":
                im_rb.setBackgroundResource(true==checked?R.mipmap.bottom_tab_user_selected:R.mipmap.bottom_tab_user_normal);
                break;
        }
    }



}
