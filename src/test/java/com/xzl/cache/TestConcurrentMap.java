package com.xzl.cache;

import com.xzl.cache.ConcurrentHashMapFactory;
import junit.framework.TestCase;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;
import java.util.function.Predicate;

public class TestConcurrentMap extends TestCase {
    @Test
    public void testNewSharedMap(){
        ArrayList<Thread> threads=new ArrayList<>();
        ArrayList<Map> maps_x=new ArrayList<>();
        Map<String, ConcurrentLinkedQueue<String>> results=new ConcurrentHashMap<>();
        for(int i=0;i<120;i++){
            String audioName=String.format("audio_%d",i%3);
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    Map map=ConcurrentHashMapFactory.<String,byte[]>newSharedHashMap("audio");
//                    System.out.println(map.hashCode());
                    OggDecoderMock mock=new OggDecoderMock(audioName);
                    String result=ConcurrentDataObjectFactory.newSharedDataObject(mock::decodeOggPacket,audioName,map);
                    synchronized (results){
                        ConcurrentLinkedQueue<String> queue=null;
                        if(results.containsKey(audioName)){
                            queue=results.get(audioName);
                        }else{
                            queue=new ConcurrentLinkedQueue<>();
                            results.put(audioName,queue);
                        }
                        queue.add(result);
                    }
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
        System.out.println(String.format("total number of threads is %d", maps_x.size()));
        for(int i=0;i<maps_x.size();i++){
            if(i< maps_x.size()-1)
                assertTrue(maps_x.get(i)==maps_x.get(i+1));
        }
        for(ConcurrentLinkedQueue queue: results.values()){
            Object[] tempArr= queue.toArray();
            for(int i=0;i<tempArr.length;i++){
                if(i<tempArr.length-1)
                    assertTrue(tempArr[i]==tempArr[i+1]);
            }
        }
    }
}
