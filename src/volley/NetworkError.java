package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class NetworkError extends VolleyError {
    public NetworkError() {
        super();
    }

    public NetworkError(Throwable cause) {
        super(cause);
    }

    public NetworkError(NetworkResponse response) {
        super(response);
    }
}
