package volley.toolbox;

import volley.Cache;

import java.util.Map;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public class NoCache implements Cache {
    @Override
    public Map.Entry get(String key) {
        return null;
    }

    @Override
    public void put(String key, Map.Entry entry) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void invalidate(String key, boolean fullExpire) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void clear() {

    }
}
