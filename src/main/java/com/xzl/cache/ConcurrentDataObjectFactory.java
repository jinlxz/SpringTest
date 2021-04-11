package com.xzl.cache;

import java.util.Map;
import java.util.concurrent.Callable;

public class ConcurrentDataObjectFactory {
    /**Return the cached object with the key cacheName in the map cacheMap or if no cache is found, execute the callable and
     * store the computation result with the key cacheName into the map cacheMap, the action is only executed once for
     * multiple threads calling this method with the same parameters cacheName and cacheMap.
     *
     * @param callable a callable with a return value
     * @param cacheName the key for storing the computation result in the map.
     * @param cacheMap a map used to store the pair of cacheName and computation result of the callable, it should be a thread-safe map, e.g. ConcurrentHashMap.
     * @param <T> the type of a object returned by the callable and stored in the map cacheMap with the key cacheName.
     * @return return the cached object with the key cacheName in the map cacheMap or if no cache is found, return the computation result of the callable or null if exception is occurred when executing the callable.
     * calls with the same parameters cacheName and cacheMap from multiple threads are returned with the same object.
     */
    public static <T> T newSharedDataObject(Callable<T> callable, String cacheName, Map<String,T> cacheMap){
        if(cacheMap.containsKey(cacheName))
            return  cacheMap.get(cacheName);
        else{
                T obj= null;
                try {
                    synchronized (cacheMap) {
                        if(cacheMap.containsKey(cacheName))
                            return  cacheMap.get(cacheName);
                        obj = callable.call();
                        cacheMap.put(cacheName, obj);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return obj;
        }
    }
}
