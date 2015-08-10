package com.hxy.code.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class IsNet {
    private Context ctx;

    public IsNet(Context ctx) {
        this.ctx = ctx;
    }
    public boolean IsConnect(){
        ConnectivityManager manager=(ConnectivityManager) ctx.getSystemService(ctx.CONNECTIVITY_SERVICE);
        NetworkInfo info=manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
       NetworkInfo.State state=null;
        if(info!=null){
            state=info.getState();
            if(state== NetworkInfo.State.CONNECTED)
                return true;
        }
        info=null;
        info=manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        state=null;
        if(info!=null){
            state=info.getState();
            if(state== NetworkInfo.State.CONNECTED)
                return true;
        }
        return false;
    }
}
