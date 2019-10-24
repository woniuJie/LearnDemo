package com.zsj.learndemo.snap;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

/**
 * Created by zhangshijie on 2019/6/5;
 */
public class SnapLayoutManager extends StaggeredGridLayoutManager {

    private double speedRatio;
    public SnapLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public SnapLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollHorizontallyBy((int)(speedRatio*dx), recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
        if(a == (int)(speedRatio*dx)){
            return dx;
        }
        return a;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        int a = super.scrollVerticallyBy((int)(speedRatio*dy), recycler, state);//屏蔽之后无滑动效果，证明滑动的效果就是由这个函数实现
        if(a == (int)(speedRatio*dy)){
            return dy;
        }
        return a;
    }

    public void setSpeedRatio(double speedRatio){
        this.speedRatio = speedRatio;
    }
}
