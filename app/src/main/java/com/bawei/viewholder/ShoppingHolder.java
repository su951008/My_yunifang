package com.bawei.viewholder;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/4/14.
 */

public class ShoppingHolder<T extends ViewDataBinding>
        extends RecyclerView.ViewHolder {
    private T mBinding;
    public ShoppingHolder(T Binding) {
        super(Binding.getRoot());
        this.mBinding=Binding;
    }
    public T GetDataBinding(){
        return mBinding;
    }

}
