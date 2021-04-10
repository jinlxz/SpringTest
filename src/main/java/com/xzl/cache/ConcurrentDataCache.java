package com.xzl.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentDataCache<K,V> {
    private Map<K,V> cacheMap=new ConcurrentHashMap<>();
    public ConcurrentDataCache(){
    }
    public V get(K cacheKey){
        return cacheMap.get(cacheKey);
    }
}
