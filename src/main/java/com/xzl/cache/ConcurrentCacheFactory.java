package com.xzl.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentCacheFactory {
    public static Map<String,ConcurrentDataCache> cacheMap=new ConcurrentHashMap<>();
    public static synchronized <K,V> ConcurrentDataCache<K,V> newCache(String cacheName){
        if(cacheMap.containsKey(cacheName))
            return cacheMap.get(cacheName);
        else{
           ConcurrentDataCache<K,V> cache=new ConcurrentDataCache<K, V>();
           cacheMap.put(cacheName, cache);
           return cache;
        }

    }
}
