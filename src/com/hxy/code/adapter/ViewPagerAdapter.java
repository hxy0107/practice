package com.hxy.code.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.hxy.code.myfragment.MyFragmentone;
import com.hxy.code.myfragment.MyFragmentthree;
import com.hxy.code.myfragment.MyFragmenttwo;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private Context ctx;

    //FragmentManager fragment������ ,������
    public ViewPagerAdapter(FragmentManager fm,Context ctx) {
        super(fm);
        this.ctx = ctx;
    }
    //����һ��fragment
    //arg0 �������ڼ�ҳ
    @Override
    public Fragment getItem(int arg0) {
        Fragment mFragment = null;
        if(arg0 == 0){
            mFragment = new MyFragmentone(ctx);
        }else if(arg0 == 1){
            mFragment = new MyFragmenttwo(ctx);
        }else if(arg0 == 2){
            mFragment = new MyFragmentthree(ctx);
        }
        return mFragment;
    }

    //������������
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 3;
    }
}
