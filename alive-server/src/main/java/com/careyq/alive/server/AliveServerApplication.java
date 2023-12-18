package com.careyq.alive.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 项目启动类
 *
 * @author CareyQ
 */
@SpringBootApplication(scanBasePackages = {"com.careyq.alive.module"})
public class AliveServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AliveServerApplication.class, args);
    }
}
