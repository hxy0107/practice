package com.hxy.code.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class FileUtiles {
    private Context ctx;

    public FileUtiles(Context ctx) {
        this.ctx = ctx;
    }
    public String getAbsolutePath(){
        File root=ctx.getExternalFilesDir(null);
        if(root!=null)
            return root.getAbsolutePath();
        return null;
    }
    public boolean isBitmap(String name){
        File root=ctx.getExternalFilesDir(null);
        File file=new File(root,name);
        return file.exists();
    }
    public void saveBitmap(String name,Bitmap bitmap){
        if(bitmap==null){
            return;
        }
        if(!Environment.getExternalStorageState().equals(Environment.MEDIA_UNMOUNTED)){
            return;
        }
        String BitPath=getAbsolutePath()+"/"+name;

        try {
            FileOutputStream fos=new FileOutputStream(BitPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100,fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
