package volley;

import android.app.DownloadManager;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public interface ResponseDelivery {
    public void postResponse(Request<?> request,Response<?> response);
}
