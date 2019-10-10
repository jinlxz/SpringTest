package com.xzl.configuration;

import com.xzl.test.CDPlayer;
import com.xzl.test.CompactDisc;
import com.xzl.test.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import soundsystem.EmptyDisc;


import java.util.ArrayList;
import java.util.Arrays;

@Configuration
//@ComponentScan(basePackageClasses = CompactDisc.class)
public class CDPlayerConfig {
    @Bean
    public CompactDisc sgtPepper(){
        return new SgtPeppers();
    }
    @Bean
    public CDPlayer cdPlayer(){
        return new CDPlayer(sgtPepper());
    }

    @Bean
    public EmptyDisc emptyDisc(){
        return new EmptyDisc("hello world","xianzhou.liu", Arrays.asList("fdsas","fasdfads"));
    }
}
