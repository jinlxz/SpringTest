package com.xzl.test;

import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EmptyDisc {
    private String title;
    private String artist;
    private List<String> tracks;
    public EmptyDisc(String title,String artist,List<String> tracks){
        this.title=title;
        this.artist=artist;
        this.tracks=tracks;
    }
//    @Override
    public void play() {
        System.out.println("Playing"+title+"by "+artist);
        for(String track: this.tracks){
            System.out.println("-Track: "+ track);
        }
    }
}
