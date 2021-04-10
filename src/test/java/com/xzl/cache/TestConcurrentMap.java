package com.xzl.cache;

import com.xzl.cache.ConcurrentHashMapFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class TestConcurrentMap extends TestCase {
    @Test
    public void testNewSharedMap(){
        ArrayList<Thread> threads=new ArrayList<>();
        ArrayList<Map> maps_x=new ArrayList<>();
        for(int i=0;i<10;i++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    Map map=ConcurrentHashMapFactory.<String,byte[]>newSharedHashMap("audio");
//                    System.out.println(map.hashCode());
                    synchronized (maps_x) {
                        maps_x.add(map);
                    }
                }
            });
            threads.add(t);
            t.start();
        }
        for(Thread t: threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for(int i=0;i<maps_x.size();i++){
            if(i< maps_x.size()-1)
                assertTrue(maps_x.get(i)==maps_x.get(i+1));
        }

    }
}
