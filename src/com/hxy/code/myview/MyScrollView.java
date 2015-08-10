package com.hxy.code.myview;

import android.content.Context;
import android.util.AttributeSet;

import android.widget.ScrollView;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class MyScrollView extends ScrollView {
    private OnScrollListener onScrollListener;
    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if(onScrollListener!=null){
            onScrollListener.onScroll(t);
        }
    }

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void setOnScrollListener(OnScrollListener onScrollListener){
        this.onScrollListener=onScrollListener;
    }


    public interface OnScrollListener{
        public void onScroll(int scrollY);
    }
}
