package com.bawei.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.tool.util.L;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bawei.BR;
import com.bawei.R;
import com.bawei.bean.ShoppingData;
import com.bawei.databinding.RecyItem;
import com.bawei.fragment.shopping.Frag_Shopping;
import com.bawei.utils.MoniToringUtils;
import com.bawei.utils.OnItemTouchUtils;
import com.bawei.viewholder.ShoppingHolder;
import com.bumptech.glide.Glide;

import org.antlr.v4.codegen.model.LL1AltBlock;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.media.CamcorderProfile.get;
import static com.bawei.fragment.shopping.Frag_Shopping.frag;
import static com.bawei.fragment.shopping.Frag_Shopping.utils;
/**
 * Created by Administrator on 2017/4/14.
 */
public class RecyAdapter extends RecyclerView.Adapter<ShoppingHolder> implements OnItemTouchUtils.itemTouch {
    private List<ShoppingData.DataBean.DefaultGoodsListBean> arr = new ArrayList<>();
    private RecyItem Recy_Item;
    private ArrayList<RecyItem> arritem ;
    private Context con;
    private final MoniToringUtils Utils;
    private Map<String, Boolean> map = new HashMap<String, Boolean>();
    private DecimalFormat df=   new DecimalFormat(".##");
    public RecyAdapter(ShoppingData shoppingData, Context con) {
        this.con = con;
        arr = shoppingData.getData().getDefaultGoodsList();
        Utils = utils;
        arritem=new ArrayList<>();
    }
    @Override
    public ShoppingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Recy_Item = DataBindingUtil.inflate(LayoutInflater.from(con), R.layout.recy_shppingitem, null, false);
        arritem.add(Recy_Item);
        View view = Recy_Item.getRoot();
        ViewGroup.LayoutParams layoutParams = parent.getLayoutParams();
        int width = ((Activity) parent.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        layoutParams.width = width;
        view.setLayoutParams(layoutParams);
        return new ShoppingHolder(Recy_Item);
    }
    @Override
    public void onBindViewHolder( final ShoppingHolder holder, final int position) {
        Glide.with(con).load(arr.get(position).getGoods_img()).into(Recy_Item.imageRecyItem);
        holder.GetDataBinding().setVariable(BR.RecyItem,arr.get(position));
        holder.GetDataBinding().executePendingBindings();
        Recy_Item.setRecyItem(arr.get(position));
        Recy_Item.setRecy(this);
        String id=arr.get(position).getId();
        setCheck(false,id);
        MoniToringUtils.Compile onClick = new MoniToringUtils.Compile() {
            @Override
            public void onClickCompilelistener() {//全选监听
                ClickCompile();
            }

            private void ClickCompile() {
                for (int i = 0; i < arr.size(); i++) {
                    boolean checked = arritem.get(i).cbRadio.isChecked();
                    setCheck(checked,arr.get(i).getId());
                    arritem.get(i).cbRadio.setChecked(!checked);
                    Account(i);
                }
            }
            @Override
            public void onClickItemDelete() {
                boolean checked = arritem.get(holder.getAdapterPosition()).cbRadio.isChecked();
                setCheck(checked,arr.get(holder.getAdapterPosition()).getId());
                arritem.get(holder.getLayoutPosition()).cbRadio.setChecked(!checked);
                if(checked){
                    Account(holder.getLayoutPosition());
                }
                arr.remove(holder.getLayoutPosition());
                notifyItemRemoved(holder.getLayoutPosition());
                selector();
            }//滑动删除
            @Override
            public void onClickCheckedListener() {
               int  position_2=holder.getLayoutPosition();
                String id=arr.get(position_2).getId();
                arritem.get(position_2).cbRadio.setChecked(!map.get(id));
                Account(position_2);
            }//单选监听
        };
        frag.setCompileOnClick(onClick);
        Recy_Item.setOnClick(onClick);
    }
    @Override
    public int getItemCount() {
        return arr.size();
    }
    @Override
    public void Getstate(int fromPosition, int toPosition) {//设置拖拽状态
        Collections.swap(arr,fromPosition,toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }
    //将字符串转为Double
    public double getType(String str)
    {
    return   Double.parseDouble(str);
    }
    private void Account(int position_2) {
        selector();
        double pricy = getType(arr.get(position_2).getShop_price());//
        double sum = Utils.getSum();
        boolean checked = arritem.get(position_2).cbRadio.isChecked();
        setCheck(checked,arr.get(position_2).getId());
        int stock_number = arr.get(position_2).getStock_number();
        if (checked) {
            addpricy(pricy,   sum, stock_number);//添加pricy
        } else {
            if (sum>=pricy) {
            SubtractPricy(pricy, sum, stock_number);//减少pricy
            }
        }
    }
    private void selector() {
        int count =0;
        for (int i = 0; i <arr.size() ; i++) {
            if(arritem.get(i).cbRadio.isChecked()){
                count++;
            }
        }
        Frag_Shopping.frag.selectAll.setChecked(count==arr.size()?true:false);

    }
    //增加总价钱
    private void addpricy(double pricy, double sum, int stock_number) {
        if(stock_number!=0)
        Utils.setSum(Double.parseDouble(df.format(sum+pricy*stock_number)));
        else{
            Utils.setSum(Double.parseDouble(df.format(sum+pricy)) );
        }
    }
    //减少总价钱
    private void SubtractPricy(double pricy, double sum, int stock_number) {
        if(stock_number!=0)
            Utils.setSum(Double.parseDouble(df.format(sum-pricy*stock_number)));
        else{
            Utils.setSum(Double.parseDouble( df.format( sum -   pricy ))
            );
        }
    }
    public void setCheck(boolean checkFlag,String id)//当前状态存入Map
    {
        map.put(id, checkFlag);
        }
}
