package com.hxy.code.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.hxy.code.R;
import com.hxy.code.info.SignInfo;
import com.hxy.code.model.Model;
import com.hxy.code.utils.LoadImg;
import com.hxy.code.utils.SmileyParser;

import java.util.List;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class SignAdapter extends BaseAdapter {
    private List<SignInfo> list;
    private Context ctx;
    private LoadImg loadImg;
    private String[] mFaceValue;
    final String arrText1[] = new String[20];
    final String arrText2[] = new String[20];
    final String arrText3[] = new String[20];
    final String arrText4[] = new String[16];
    private EditText mshop_qiandao_edittext1;

    public SignAdapter(List<SignInfo> list, Context ctx) {
        this.list = list;
        this.ctx = ctx;
        loadImg = new LoadImg(ctx);
        mFaceValue = this.ctx.getResources().getStringArray(
                R.array.default_smiley_texts);
        initModel();
    }

    private void initModel() {
        for (int i = 0; i < 20; i++) {
            arrText1[i] = mFaceValue[i];
        }
        for (int i = 20; i < 40; i++) {
            arrText2[i - 20] = mFaceValue[i];
        }
        for (int i = 40; i < 60; i++) {
            arrText3[i - 40] = mFaceValue[i];
        }
        for (int i = 60; i < 76; i++) {
            arrText4[i - 60] = mFaceValue[i];
        }
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return arg0;
    }

    @Override
    public View getView(final int arg0, View arg1, ViewGroup arg2) {
        final Holder hold;
        SmileyParser.init(ctx);
        final SmileyParser parser = SmileyParser.getInstance();
        if (arg1 == null) {
            hold = new Holder();
            arg1 = View.inflate(ctx, R.layout.lv_item_picc, null);
            hold.mLevel = (ImageView) arg1.findViewById(R.id.SignStarImg);
            hold.mImg = (ImageView) arg1.findViewById(R.id.picc_item_iv);
            hold.mContent = (TextView) arg1
                    .findViewById(R.id.picc_item_content);
            hold.mNick = (TextView) arg1.findViewById(R.id.SignNick);
            hold.mTime = (TextView) arg1.findViewById(R.id.SignTime);
            arg1.setTag(hold);
        } else {
            hold = (Holder) arg1.getTag();
        }
        hold.mContent.setText(parser.addSmileySpans(list.get(arg0)
                .getSigncontent()));
        hold.mNick.setText(list.get(arg0).getName());
        hold.mTime.setText(list.get(arg0).getSigntime());
        int slevel = Integer.valueOf(list.get(arg0).getSignlevel());
        switch (slevel) {
            case 0:
                hold.mLevel.setImageResource(R.drawable.star0);
                break;
            case 1:
                hold.mLevel.setImageResource(R.drawable.star1);
                break;
            case 2:
                hold.mLevel.setImageResource(R.drawable.star2);
                break;
            case 3:
                hold.mLevel.setImageResource(R.drawable.star3);
                break;
            case 4:
                hold.mLevel.setImageResource(R.drawable.star4);
                break;
            case 5:
                hold.mLevel.setImageResource(R.drawable.star5);
                break;
        }

        hold.mImg.setTag(Model.SIGNLISTIMGURL + list.get(arg0).getSignimage());
        // 设置默认显示的图片
        hold.mImg.setVisibility(View.INVISIBLE);
        if (list.get(arg0).getSignimage().equals("")) {
            hold.mImg.setVisibility(View.GONE);
        }
        // 网络获取图片
        Bitmap bit = loadImg.loadImage(hold.mImg, Model.SIGNLISTIMGURL
                + list.get(arg0).getSignimage(), new LoadImg.ImageDownloadCallBack() {
            @Override
            public void onImageDownload(ImageView imageView, Bitmap bitmap) {
                // 网络交互时回调进来防止错位
                if (hold.mImg.getTag().equals(
                        Model.SIGNLISTIMGURL + list.get(arg0).getSignimage())) {
                    hold.mImg.setVisibility(View.VISIBLE);
                    // 设置网络下载回来图片显示
                    hold.mImg.setImageBitmap(bitmap);
                }
            }
        });

        // 从本地获取的
        if (bit != null) {
            // 设置缓存图片显示
            hold.mImg.setVisibility(View.VISIBLE);
            hold.mImg.setImageBitmap(bit);
        }

        return arg1;
    }

    static class Holder {
        ImageView mLevel;
        ImageView mImg;
        TextView mContent;
        TextView mNick;
        TextView mTime;
    }

}
