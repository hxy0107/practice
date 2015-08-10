package com.hxy.code.myfragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.hxy.code.R;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class MyFragmentone extends Fragment {
    private Context ctx;

    public MyFragmentone(Context ctx) {
        super();
        this.ctx = ctx;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=null;
        view=View.inflate(ctx, R.layout.fragment_my01,null);
        return view;
    }
}
