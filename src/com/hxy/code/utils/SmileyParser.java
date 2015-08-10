package com.hxy.code.utils;

import android.content.Context;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import com.hxy.code.R;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class SmileyParser {
    private static SmileyParser sInstance;
    public static SmileyParser getInstance(){
        return sInstance;
    }
    public static void init(Context context){
        sInstance=new SmileyParser(context);
    }
    private final Context mContext;
    private final String[] arrText;
    private final Pattern mPattern;
    private final HashMap<String,Integer> mSmileyToRes;
    public static final int DEFAULT_SMILEY_TEXTS= R.array.default_smiley_texts;
    public static final int DEFAULT_SMILEY_NAMES=R.array.default_smiley_names;
    private SmileyParser(Context context){
        mContext=context;
        arrText=mContext.getResources().getStringArray(DEFAULT_SMILEY_NAMES);
        mSmileyToRes=buildSmileyToRes();
        mPattern=buildPattern();
    }
    private static final int[] DEFAULT_SMILEY_RES_IDS = { R.drawable.emoji000,
            R.drawable.emoji001, R.drawable.emoji002, R.drawable.emoji003,
            R.drawable.emoji004, R.drawable.emoji005, R.drawable.emoji006,
            R.drawable.emoji007, R.drawable.emoji008, R.drawable.emoji009,
            R.drawable.emoji010, R.drawable.emoji011, R.drawable.emoji012,
            R.drawable.emoji013, R.drawable.emoji014, R.drawable.emoji015,
            R.drawable.emoji016, R.drawable.emoji017, R.drawable.emoji018,
            R.drawable.emoji019, R.drawable.emoji020, R.drawable.emoji021,
            R.drawable.emoji022, R.drawable.emoji023, R.drawable.emoji024,
            R.drawable.emoji025, R.drawable.emoji026, R.drawable.emoji027,
            R.drawable.emoji101, R.drawable.emoji102, R.drawable.emoji103,
            R.drawable.emoji104, R.drawable.emoji105, R.drawable.emoji106,
            R.drawable.emoji107, R.drawable.emoji108, R.drawable.emoji109,
            R.drawable.emoji110, R.drawable.emoji201, R.drawable.emoji202,
            R.drawable.emoji203, R.drawable.emoji204, R.drawable.emoji205,
            R.drawable.emoji206, R.drawable.emoji207, R.drawable.emoji208,
            R.drawable.emoji209, R.drawable.emoji210, R.drawable.emoji211,
            R.drawable.emoji212, R.drawable.emoji213, R.drawable.emoji214,
            R.drawable.emoji215, R.drawable.emoji216, R.drawable.emoji217,
            R.drawable.emoji218, R.drawable.emoji219, R.drawable.emoji220,
            R.drawable.emoji221, R.drawable.emoji222, R.drawable.emoji301,
            R.drawable.emoji302, R.drawable.emoji303, R.drawable.emoji304,
            R.drawable.emoji305, R.drawable.emoji306, R.drawable.emoji307,
            R.drawable.emoji308, R.drawable.emoji309, R.drawable.emoji310,
            R.drawable.emoji311, R.drawable.emoji312, R.drawable.emoji313,
            R.drawable.emoji314, R.drawable.emoji315, R.drawable.emoji316 };
    private HashMap<String,Integer> buildSmileyToRes(){
        if(DEFAULT_SMILEY_RES_IDS.length!=arrText.length){
            throw new IllegalStateException("id not match");
        }
        HashMap<String,Integer> smileyToRes=new HashMap<String, Integer>(arrText.length);
        for(int i=0;i<arrText.length;i++){
            smileyToRes.put(arrText[i],DEFAULT_SMILEY_RES_IDS[i]);
        }
        return smileyToRes;
    }
    private Pattern buildPattern(){
        StringBuilder patternString=new StringBuilder(arrText.length*3);
        patternString.append('(');
        for(String s:arrText){
            patternString.append(Pattern.quote(s));
            patternString.append('|');
        }
        patternString.replace(patternString.length() - 1, patternString.length(), ")");
        return Pattern.compile(patternString.toString());
    }
    public CharSequence addSmileySpans(CharSequence text){
        SpannableStringBuilder builder=new SpannableStringBuilder(text);
        Matcher matcher=mPattern.matcher(text);
        while(matcher.find()){
            int resId=mSmileyToRes.get(matcher.group());
            builder.setSpan(new ImageSpan(mContext,resId),matcher.start(),matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return builder;
    }


}
