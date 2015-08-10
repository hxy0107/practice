package com.hxy.code.adapter;

import android.content.Context;
import android.media.Image;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.hxy.code.R;

import java.util.List;
import java.util.Map;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class SearchMainAdapter  extends BaseAdapter{
    private Context ctx;
    private List<Map<String,Object>> list;
    private int position=0;
    private boolean isloadingimg=true;
    private int layout= R.layout.search_more_morelist_item;

    public SearchMainAdapter(Context ctx, List<Map<String, Object>> list) {
        this.ctx = ctx;
        this.list = list;
    }

    public SearchMainAdapter(Context ctx, List<Map<String, Object>> list, int layout, boolean isloadingimg) {
        this.ctx = ctx;
        this.list = list;
        this.layout = layout;
        this.isloadingimg = isloadingimg;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Holder hold;
        if (view == null) {
            hold=new Holder();
            view=View.inflate(ctx,layout,null);
            hold.txt=(TextView)view.findViewById(R.id.Search_more_mainitem_txt);
            hold.img = (ImageView) view
                    .findViewById(R.id.Search_more_mainitem_img);
            hold.layout = (LinearLayout) view
                    .findViewById(R.id.Search_more_mainitem_layout);
            view.setTag(hold);
        }else{
            hold=(Holder)view.getTag();
        }
        if(isloadingimg==true){
            hold.img.setImageResource(Integer.parseInt(list.get(i).get("img").toString()));
        }
        hold.txt.setText(list.get(i).get("txt").toString());
        hold.layout.setBackgroundResource(R.drawable.search_more_mainlistselect);
        if(i==position){
            hold.layout.setBackgroundResource(R.drawable.list_bkg_line_u);
        }
        return view;
    }
    private static class Holder{
        LinearLayout layout;
        ImageView img;
        TextView txt;
    }
    public void setSelectItem(int i){
        position=i;
    }
    private int getSelectItem(){
        return position;
    }
}
