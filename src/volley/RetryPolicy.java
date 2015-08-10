package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public interface RetryPolicy {
    public int getCurrentTimeout();
    public int getCurrentRetryCount();
    public void retry(VolleyError error)throws VolleyError;
}
