package volley;

import org.apache.http.HttpStatus;

import java.util.Collections;
import java.util.Map;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class NetworkResponse {
    public final int statusCode;
    public final byte[] data;
    public final Map<String,String> headers;
    public final boolean notModified;

    public NetworkResponse(int statusCode,byte[] data, Map<String, String> headers, boolean notModified) {
        this.data = data;
        this.statusCode = statusCode;
        this.headers = headers;
        this.notModified = notModified;
    }
    public NetworkResponse(byte[] data){
        this(HttpStatus.SC_OK,data, Collections.<String,String>emptyMap(),false);
    }
    public NetworkResponse(byte[] data,Map<String,String> headers){
        this(HttpStatus.SC_OK,data,headers,false);
    }
}
