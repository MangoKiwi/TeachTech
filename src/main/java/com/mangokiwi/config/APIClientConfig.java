package com.mangokiwi.config;

import com.restfb.DefaultFacebookClient;
import com.restfb.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * Created by zhenfeng on 5/4/17.
 */

@Configuration
public class APIClientConfig {

    @Autowired
    Environment environment;

    @Bean
    public DefaultFacebookClient facebookClient(){
        String accessToken = environment.getProperty("security.oauth2.client.access-token-uri");
        DefaultFacebookClient facebookClient = new DefaultFacebookClient(accessToken, Version.LATEST);
        return facebookClient;
    }
}
