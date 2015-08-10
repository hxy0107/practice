package com.hxy.code.thread;

import android.os.Handler;
import android.os.Message;
import com.hxy.code.model.Model;
import com.hxy.code.net.MyGet;

import java.io.IOException;


/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class HttpGetThread implements Runnable {
    private Handler hand;
    private String url;
    private MyGet myGet=new MyGet();

    public HttpGetThread(Handler hand,String endParamerse) {
        this.hand = hand;
        url= Model.HTTPURL+endParamerse;
    }

    @Override
    public void run() {
        Message msg=hand.obtainMessage();

        try {
            String result=myGet.doGet(url);
            msg.what=200;
            msg.obj=result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        hand.sendMessage(msg);
    }
}
