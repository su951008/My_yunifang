package com.bawei.utils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.bawei.adapter.RecyAdapter;

/**
 * Created by Administrator on 2017/4/14.
 */

public class OnItemTouchUtils extends ItemTouchHelper.Callback {
    private RecyAdapter  recy;

    public OnItemTouchUtils(RecyAdapter recy) {
        this.recy = recy;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
       int weizhi =ItemTouchHelper.UP|ItemTouchHelper.DOWN|ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT;
        return makeMovementFlags(weizhi,0);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        recy.Getstate(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }
    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }
    public interface itemTouch{
        void Getstate(int a,int b);
    }
}
