package com.hxy.code.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import com.hxy.code.R;
import com.hxy.code.adapter.ViewPagerAdapter;
import com.zdp.aseo.content.AseoZdpAseo;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class MainActivity extends FragmentActivity {
    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    private void initView(){
        mViewPager=(ViewPager)findViewById(R.id.MyViewPager);
        ViewPagerAdapter myAdapter=new ViewPagerAdapter(this.getSupportFragmentManager(),MainActivity.this);
        mViewPager.setAdapter(myAdapter);
        AseoZdpAseo.initTimer(this);
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){
            Intent intent=new Intent(Intent.ACTION_MAIN);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addCategory(Intent.CATEGORY_HOME);
            startActivity(intent);
        }
        return true;
    }
}
