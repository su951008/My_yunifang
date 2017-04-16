package com.bawei.fragment.shopping;

import android.content.SharedPreferences;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bawei.R;
import com.bawei.adapter.RecyAdapter;
import com.bawei.bean.ShoppingData;
import com.bawei.databinding.Cart_empty;
import com.bawei.databinding.FragShopping;
import com.bawei.databinding.RecyShopping;
import com.bawei.utils.MoniToringUtils;
import com.bawei.utils.OnItemTouchUtils;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;


public class Frag_Shopping extends Fragment {
    public static FragShopping frag;
    private RecyShopping inflate;
    private Cart_empty cart_empty;
    public  static MoniToringUtils utils;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initDataBinding();
        return frag.getRoot();
    }
    @Override
    public void onResume() {
        super.onResume();
        initview();
    }
    private void initview()
    {
        SharedPreferences sp = getActivity().getSharedPreferences("sp", 0);
        isShou(sp);
    }
    private void isShou(SharedPreferences sp) {
        boolean isbool = sp.getBoolean("isbool", true);
        if (isbool) {
            frag.fragLayout.removeAllViews();
            sp.edit().putBoolean("isbool", false).commit();
            MoniToringUtils.isShow=false;
            frag.fragLayout.addView(cart_empty.getRoot());
        } else {
            sp.edit().putBoolean("isbool", true).commit();
            MoniToringUtils.isShow=true;
            frag.fragLayout.removeAllViews();
            frag.fragLayout.addView(inflate.getRoot());
        }
    }//待修改
    private void initDataBinding() {
        frag = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.frag_shopping, null, false);
        cart_empty =DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.cart_empty, null, false);
        cart_empty.setFinish(this);
        utils  = new MoniToringUtils();
        frag.setCompile(utils);
        initRecy();

    }//初始化Databinding
    private void initRecy() {
        inflate = DataBindingUtil.inflate(LayoutInflater.from(getActivity()), R.layout.recy_shopping, null, false);
        inflate.recyshopping.setLayoutManager(new LinearLayoutManager(getActivity()));
        OkHttpUtils.get().url(  MoniToringUtils.GetPath() ).build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }
            @Override
            public void onResponse(String response, int id) {
                ShoppingData shoppingData = new Gson().fromJson(response, ShoppingData.class);
                RecyAdapter recyAdapter = new RecyAdapter(shoppingData,getActivity());
                inflate.recyshopping.setAdapter(recyAdapter);
                new ItemTouchHelper(new OnItemTouchUtils(recyAdapter)).attachToRecyclerView(inflate.recyshopping);
            }
        });
    }//初始化RecyclerView
    public  void finish(){
        getActivity().finish();
    }
}
