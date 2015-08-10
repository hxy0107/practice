package com.hxy.code.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hxy.code.R;

/**
 * Created by xianyu.hxy on 2015/8/5.
 */
public class SearchMoreAdapter extends BaseAdapter {
    private Context ctx;
    private String[] text;
    private int position=0;
    private int layout= R.layout.search_more_morelist_item;

    public SearchMoreAdapter(Context ctx, String[] text) {
        this.ctx = ctx;
        this.text = text;
    }

    public SearchMoreAdapter(Context ctx, String[] text, int layout) {
        this.ctx = ctx;
        this.text = text;
        this.layout = layout;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int i) {
        return text[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder hold;
        if(view==null){
            hold=new Holder();
            view=View.inflate(ctx,R.id.Search_more_moreitem_txt,null);
            hold.txt=(TextView)view.findViewById(R.id.Search_more_moreitem_txt);
            hold.layout=(LinearLayout)view.findViewById(R.id.More_list_lishi);
            view.setTag(hold);
        }else {
            hold=(Holder)view.getTag();
        }
        hold.txt.setText(text[i]);
        hold.layout.setBackgroundResource(R.drawable.my_list_txt_background);
        hold.txt.setTextColor(Color.parseColor("#ff666666"));
        if(position==position){
            hold.layout.setBackgroundResource(R.drawable.search_more_morelisttop_bkg);
            hold.txt.setTextColor(Color.parseColor("#ffff8c00"));
        }
        return view;
    }

    public void setSelectItem(int i){
        position=i;
    }
    private static class Holder{
        LinearLayout layout;
        TextView txt;
    }
}
