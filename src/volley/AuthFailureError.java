package volley;

import android.content.Intent;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class AuthFailureError extends VolleyError {
    private Intent mResolutionIntent;

    public AuthFailureError(Intent mResolutionIntent) {
        this.mResolutionIntent = mResolutionIntent;
    }

    public AuthFailureError() {
    }

    public AuthFailureError(NetworkResponse response) {
        super(response);
    }
    public AuthFailureError(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        if(mResolutionIntent!=null){
            return "User needs to enter credentials.";
        }
        return super.getMessage();
    }

    public AuthFailureError(String message, Exception reason) {
        super(message, reason);
    }

    public Intent getResolutionIntent() {
        return mResolutionIntent;
    }

}
