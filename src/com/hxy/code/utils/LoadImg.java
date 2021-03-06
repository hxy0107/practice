package com.hxy.code.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import com.hxy.code.net.DownBitmap;

import java.io.InputStream;
import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by xianyu.hxy on 2015/8/4.
 */
public class LoadImg {
    private static final int Max=5;
    private Map<String,SoftReference<Bitmap>> imageCaches=null;
    private FileUtiles fileUtiles;
    private ExecutorService threadPools=null;

    public LoadImg(Context ctx){
        imageCaches=new HashMap<String, SoftReference<Bitmap>>();
        fileUtiles=new FileUtiles(ctx);
    }
    public Bitmap loadImage(final ImageView imageView,final  String imageUrl,final ImageDownloadCallBack imageDownloadCallBack){
        final  String filename=imageUrl.substring(imageUrl.lastIndexOf("/")+1,imageUrl.length());
        String filepath=fileUtiles.getAbsolutePath()+"/"+filename;
        if(imageCaches.containsKey(imageUrl)) {
            SoftReference<Bitmap> soft = imageCaches.get(imageUrl);
            Bitmap bit = soft.get();
            if (bit != null) {
                return bit;
            }
        }
            if(fileUtiles.isBitmap(filename)){
                Bitmap bit1= BitmapFactory.decodeFile(filename);
                imageCaches.put(imageUrl,new SoftReference<Bitmap>(bit1));
                return bit1;
            }
            if(imageUrl!=null&&imageUrl.equals("")){
                if(threadPools==null){
                    threadPools= Executors.newFixedThreadPool(Max);
                }
                final Handler hand=new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        if(msg.what==111&&imageDownloadCallBack!=null){
                            Bitmap bit=(Bitmap)msg.obj;
                            imageDownloadCallBack.onImageDownload(imageView, bit);
                        }
                        super.handleMessage(msg);
                    }
                };
                Thread thread=new Thread(){

                    @Override
                    public void run() {
                        InputStream inputStream= DownBitmap.getInstance().getInputStream(imageUrl);
                        BitmapFactory.Options op=new BitmapFactory.Options();
                        op.inSampleSize=2;
                        Bitmap bit=BitmapFactory.decodeStream(inputStream, null, op);
                        if(bit!=null){
                            imageCaches.put(imageUrl,new SoftReference<Bitmap>(bit));
                            fileUtiles.saveBitmap(filename, bit);
                            Message msg=hand.obtainMessage();
                            msg.what=111;
                            msg.obj=bit;
                            hand.sendMessage(msg);
                        }
                        super.run();
                    }
                };
                threadPools.execute(thread);

        }
            return null;
    }


    public interface  ImageDownloadCallBack{
        void onImageDownload(ImageView imageView,Bitmap bitmap);
    }
}
