package com.xzl.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapFactory {
    public static Map<String,ConcurrentHashMap> cacheMap=new ConcurrentHashMap<>();
    public static <K,V> ConcurrentHashMap<K,V> newSharedHashMap(String cacheName){
        if(cacheMap.containsKey(cacheName))
            return cacheMap.get(cacheName);
        else{
            ConcurrentHashMap<K,V> cache=null;
            synchronized (cacheMap) {
                if(cacheMap.containsKey(cacheName))
                    return cacheMap.get(cacheName);
                else {
                    cache = new ConcurrentHashMap<>();
                    cacheMap.put(cacheName, cache);
                }
            }
           return cache;
        }
    }
}
