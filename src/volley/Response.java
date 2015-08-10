package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class Response<T> {
    public Response( T result,Cache.Entry cacheEntry) {
        this.cacheEntry = cacheEntry;
        this.result = result;
        this.error=null;
    }

    public Response(VolleyError error) {
        this.result=null;
        this.cacheEntry=null;
        this.error = error;
    }


    public interface Listener<T>{
        public void onResponse(T response);
    }
    public interface ErrorListener{
        public void onErrorResponse(VolleyError error);
    }
    public static <T> Response<T> success(T result,Cache.Entry cachEntry){
        return new Response<T>(result,cachEntry);
    }
    public static <T> Response<T> error(VolleyError error){
        return new Response<T>(error);
    }


    public final T result;
    public final Cache.Entry cacheEntry;
    public final VolleyError error;
    public boolean intermediate=false;

    public boolean isSuccess(){
        return error==null;
    }

}
