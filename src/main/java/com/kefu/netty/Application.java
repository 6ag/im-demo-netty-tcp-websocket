package com.kefu.netty;

import com.kefu.netty.config.NettyProperties;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * 启动类
 *
 * @author feng
 * @date 2019-04-22
 */
@EnableConfigurationProperties(NettyProperties.class)
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
