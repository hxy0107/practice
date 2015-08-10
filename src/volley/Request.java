package volley;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import com.hxy.code.net.ThreadPoolUtils;

import java.lang.ref.PhantomReference;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;


/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public abstract class Request<T> implements Comparable<Request<T>> {
    private static final String DEFAULT_PARAMS_ENCODING="UTF-8";
    public interface Method{
        int DEPRECATED_GET_OR_POST=-1;
        int GET=0;
        int POST=1;
        int DELETE=3;
        int HEAD=4;
        int OPTIONS=5;
        int TRACE=6;
        int PATCH=7;
    }
    private final VolleyLog.MarkerLog mEventLog= VolleyLog.MarkerLog.ENABLE?new VolleyLog.MarkerLog():null;
    private final int mMethod;
    private final String mUrl;
    private final int mDefaultTrafficStatsTag;
    private final Response.ErrorListener mErrorListener;
    private Integer mSequence;
    private RequestQueue mRequestQueue;
    private boolean mShouldCache=true;
    private boolean mCanceled=false;
    private boolean mResponseDelivered=false;
    private long mRequestBirthTime=0;
    private static final long SLOW_REQUEST_THRESHOLD_MS=3000;
    private RetryPolicy mRetryPolicy ;
    private Cache.Entry mCacheEntry=null;
    private Object mTag;

    public Request(String url,Response.ErrorListener listener){
        this(Method.DEPRECATED_GET_OR_POST,url,listener);
    }
    public Request(int method,String url,Response.ErrorListener listener){
        mMethod=method;
        mUrl=url;
        mErrorListener=listener;
        setRetryPolicy(new DefaultRetryPolicy());
        mDefaultTrafficStatsTag=findDefaultTrafficStatsTag(url);
    }
    public int getmMethod(){
        return mMethod;
    }
    public Request<?> setTag(Object tag){
        mTag=tag;
        return this;
    }
    public Object getTag(){
        return mTag;
    }
    public int getTrafficStatsTag(){
        return mDefaultTrafficStatsTag;
    }
    private static int findDefaultTrafficStatsTag(String url){
        if(!TextUtils.isEmpty(url)){
            Uri uri=Uri.parse(url);
            if(uri!=null){
                String host=uri.getHost();
                if(host!=null){
                    return host.hashCode();
                }
            }
        }
        return 0;
    }
    public Request<?> setRetryPolicy(RetryPolicy retryPolicy){
        mRetryPolicy=retryPolicy;
        return this;
    }
    public void addMarker(String tag){
        if(VolleyLog.MarkerLog.ENABLE){
            mEventLog.add(tag,Thread.currentThread().getId());
        }else if(mRequestBirthTime==0){
            mRequestBirthTime= SystemClock.elapsedRealtime();
        }
    }
    void finish(final String tag){
        if(mRequestQueue!=null){
            mRequestQueue.finish(this);
        }
        if(VolleyLog.MarkerLog.ENABLE){
            final long threadId= Thread.currentThread().getId();
            if(Looper.myLooper()!=Looper.getMainLooper()){
                Handler mainThread=new Handler(Looper.getMainLooper());
                mainThread.post(new Runnable() {
                    @Override
                    public void run() {
                       mEventLog.add(tag,threadId);
                        mEventLog.finish(this.toString());
                    }
                });
                return;
            }
            mEventLog.add(tag,threadId);
            mEventLog.finish(this.toString());
        }else {
            long requestTime=SystemClock.elapsedRealtime()-mRequestBirthTime;
            if(requestTime>=SLOW_REQUEST_THRESHOLD_MS){
                VolleyLog.d("%d ms:%s",requestTime,this.toString());
            }
        }
    }
    public Request<?> setRequestQueue(RequestQueue requestQueue){
        mRequestQueue=requestQueue;
        return this;
    }
    public final Request<?> setSequence(int sequence){
        mSequence=sequence;
        return this;
    }
    public final int getSequence(){
        if(mSequence==null){
            throw  new IllegalStateException("getSequence called before setSequence");
        }
        return mSequence;
    }
    public String getmUrl(){
        return mUrl;
    }
    public String getCacheKey(){
        return getmUrl();
    }
    public Cache.Entry getmCacheEntry(){
        return mCacheEntry;
    }
    public void cancel(){
        mCanceled=true;
    }
    public boolean ismCanceled(){
        return mCanceled;
    }
    public Map<String,String> getHeaders() throws AuthFailureError{
        return Collections.emptyMap();
    }
    protected Map<String,String> getPostParams() throws AuthFailureError{
        return getParams();
    }
    protected String getPostParamsEncoding(){
        return getParamsEncoding();
    }
    public String getPostBodyContentType(){
        return getBodyContentType();
    }
    public byte[] getPostBody() throws AuthFailureError{

    }

}


























