package com.hxy.code.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.hxy.code.R;

import javax.net.ssl.HostnameVerifier;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class FaceGridAdapter extends BaseAdapter{
    private Context ctx;
    private int[] mValue;

    public FaceGridAdapter(Context ctx, int[] mValue) {
        this.ctx = ctx;
        this.mValue = mValue;
    }

    @Override
    public int getCount() {
        return mValue.length;
    }

    @Override
    public Object getItem(int i) {
        return mValue[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        Holder holder;
        if(arg1==null){
            holder=new Holder();
            arg1=View.inflate(ctx, R.layout.griditem,null);
            holder.mImg=(ImageView)arg1.findViewById(R.id.imageView1);
            arg1.setTag(holder);
        }else {
            holder=(Holder)arg1.getTag();
        }
        holder.mImg.setImageResource(mValue[arg0]);
        return arg1;
    }
    private static class Holder{
        ImageView mImg;
    }
}
