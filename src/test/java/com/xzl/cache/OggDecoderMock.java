package com.xzl.cache;

public class OggDecoderMock {
    public static String decodeOggPacket(){
        System.out.println("decoding ogg packet.");
        return "hello";
    }
}
