package com.careyq.alive.module.infra.runner;

import com.careyq.alive.module.infra.service.OssConfigService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化
 *
 * @author CareyQ
 */
@Slf4j
@Component
@AllArgsConstructor
public class InfraApplicationRunner implements ApplicationRunner {

    private final OssConfigService ossConfigService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        ossConfigService.init();
        log.info("初始化 OSS 配置成功");
    }
}
