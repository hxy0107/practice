package volley;

import java.util.Collections;
import java.util.Map;

/**
 * Created by xianyu.hxy on 2015/8/7.
 */
public interface Cache {
    public Map.Entry get(String key);
    public void put(String key,Map.Entry entry);
    public void initialize();
    public void invalidate(String key,boolean fullExpire);
    public void remove(String key);
    public void clear();
    public static class Entry{
        public byte[] data;
        public String etag;
        public long serverDate;
        public long ttl;
        public long softTtl;
        public Map<String,String> responseHeaders= Collections.emptyMap();
        public boolean isExpired(){
            return this.ttl<System.currentTimeMillis();
        }
        public boolean refreshNeeded(){
            return this.softTtl<System.currentTimeMillis();
        }
    }
}
