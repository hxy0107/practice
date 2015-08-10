package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class ServerError extends VolleyError {

    public ServerError() {
        super();
    }

    public ServerError(NetworkResponse response) {
        super(response);
    }
}
