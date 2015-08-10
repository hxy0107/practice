package com.hxy.code.myfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.hxy.code.R;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class MyFragmentthree extends Fragment{
    private Context ctx;

    public MyFragmentthree(Context ctx) {
        super();
        this.ctx = ctx;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = null;
        view = View.inflate(ctx, R.layout.fragment_my03, null);
        ImageView mBtn = (ImageView) view.findViewById(R.id.MyLoginBtn);
        mBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(ctx, FrameActivity.class);
                ctx.startActivity(intent);
            }
        });
        return view;
    }

}
