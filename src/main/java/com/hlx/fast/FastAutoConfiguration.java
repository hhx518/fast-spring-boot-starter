package com.hlx.fast;

import com.hlx.fast.properties.FastProperties;
import com.hlx.fast.uid.impl.CachedUidGenerator;
import com.hlx.fast.uid.impl.DefaultUidGenerator;
import com.hlx.fast.uid.worker.DisposableWorkerIdAssigner;
import com.hlx.fast.uid.worker.WorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;

/**
 * @program: fast-spring-boot-starter
 * @description: fast
 * @author: hlx
 * @create: 2019-07-10 13:49
 **/

@Configuration
@ConditionalOnClass({ DefaultUidGenerator.class, CachedUidGenerator.class })
@MapperScan({ "com.hlx.fast.uid.worker.dao" })
@EnableConfigurationProperties(FastProperties.class)

public class FastAutoConfiguration {
    @Autowired
    private FastProperties fastProperties;
   /* @Bean
    @ConditionalOnMissingBean
    @Lazy
    DefaultUidGenerator defaultUidGenerator() {
        return new DefaultUidGenerator(fastProperties);
    }*/
    @Bean
    @ConditionalOnMissingBean
    @Lazy
    CachedUidGenerator cachedUidGenerator() {
        return new CachedUidGenerator(fastProperties);
    }
    @Bean
    @ConditionalOnMissingBean
      WorkerIdAssigner workerIdAssigner() {
        return new DisposableWorkerIdAssigner();
    }

}