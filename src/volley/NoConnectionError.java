package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class NoConnectionError extends NetworkError {
    public NoConnectionError(Throwable cause) {
        super(cause);
    }

    public NoConnectionError() {
        super();
    }
}
