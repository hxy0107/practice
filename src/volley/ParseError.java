package volley;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class ParseError extends VolleyError {
    public ParseError() {
        super();
    }

    public ParseError(NetworkResponse response) {
        super(response);
    }

    public ParseError(Throwable cause) {
        super(cause);
    }
}
