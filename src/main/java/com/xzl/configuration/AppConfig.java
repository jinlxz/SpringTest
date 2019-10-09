package com.xzl.configuration;

import com.xzl.test.CDPlayer;
import com.xzl.test.CompactDisc;
import com.xzl.test.SgtPeppers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CompactDisc compactDisc(){
        return new SgtPeppers();
    }
    @Bean
    public CDPlayer cdPlayer(CompactDisc cd){
        return new CDPlayer(cd);
    }
}
