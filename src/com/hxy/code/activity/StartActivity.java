package com.hxy.code.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.LinearLayout;
import com.hxy.code.R;
import com.zdp.aseo.content.AseoZdpAseo;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class StartActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my01);
        LinearLayout mLinear=(LinearLayout)findViewById(R.id.Fragment01Linear);
        mLinear.setBackgroundResource(R.drawable.ic_splash_screen);
        AseoZdpAseo.initType(this, AseoZdpAseo.INSERT_TYPE);
        new Thread(){

            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg=hand.obtainMessage();
                hand.sendMessage(msg);

            }
        }.start();
    }
    Handler hand=new Handler(){

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(isFristRun()){
                Intent intent=new Intent(StartActivity.this,MainActivity.class);
                startActivity(intent);
            }else {
                Intent intent=new Intent(StartActivity.this,FrameActivity.class);
                startActivity(intent);
            }
            finish();
        }
    };
    private boolean isFristRun(){
        SharedPreferences sharedPreferences=getSharedPreferences("share",MODE_PRIVATE);
        boolean isFirstRun=sharedPreferences.getBoolean("isFirstRun",true);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        if(!isFirstRun){
            return false;
        }else {
            editor.putBoolean("isFirstRun",false);
            editor.commit();
            return true;
        }
    }
    public boolean onKeyDown(int keyCode,KeyEvent event){
        if(keyCode==KeyEvent.KEYCODE_BACK){}
        return true;
    }
}
