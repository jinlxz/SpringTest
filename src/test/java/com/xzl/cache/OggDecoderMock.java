package com.xzl.cache;

public class OggDecoderMock {
    private String audioName=null;
    public OggDecoderMock(String audioName){
        this.audioName=audioName;
    }
    public String decodeOggPacket(){
        System.out.println(String.format("decoding ogg file %s.", this.audioName));
        return this.audioName;
    }
}
