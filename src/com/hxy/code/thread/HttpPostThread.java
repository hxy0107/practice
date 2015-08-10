package com.hxy.code.thread;

import android.os.Handler;
import android.os.Message;
import com.hxy.code.R;
import com.hxy.code.net.MyPost;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class HttpPostThread implements Runnable {
    private Handler hand;
    private String url;
    private String mycode;
    private String value;
    private MyPost myGet = new MyPost();
    public HttpPostThread(Handler hand,String endParamerse,String mycode,String value){
        this.hand = hand;
        //拼接访问服务器完整的地址
        url = endParamerse;
        this.mycode = mycode;
        this.value = value;
    }
    @Override
    public void run() {
        Message msg=hand.obtainMessage();
        String result=myGet.doPost(url, mycode, value);
        msg.what=200;
        msg.obj=result;
        hand.sendMessage(msg);
    }
}
