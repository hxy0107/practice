package com.hxy.code.activity;

import android.app.ActivityGroup;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class FrameActivity extends ActivityGroup {
    private LinearLayout mMyBottemSearchBtn, mMyBottemTuanBtn,
            mMyBottemCheckinBtn, mMyBottemMyBtn, mMyBottemMoreBtn;
    private ImageView mMyBottemSearchImg, mMyBottemTuanImg,
            mMyBottemCheckinImg, mMyBottemMyImg, mMyBottemMoreImg;
    private TextView mMyBottemSearchTxt, mMyBottemTuanTxt, mMyBottemCheckinTxt,
            mMyBottemMyTxt, mMyBottemMoreTxt;
    private List<View> list = new ArrayList<View>();// 相当于数据源
    private View view = null;
    private View view1 = null;
    private View view2 = null;
    private View view3 = null;
    private View view4 = null;
    private android.support.v4.view.ViewPager mViewPager;
    private PagerAdapter pagerAdapter = null;// 数据源和viewpager之间的桥梁

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private void initView(){

    }
}
