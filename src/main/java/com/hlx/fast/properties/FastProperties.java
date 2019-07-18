package com.hlx.fast.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.TimeUnit;

/**
 * @program: fast-spring-boot-starter
 * @description: fast
 * @author: hlx
 * @create: 2019-07-10 13:51
 **/


@Data
@ConfigurationProperties(prefix = "fast")
@Primary
public class FastProperties {
    /**
     * 时间增量值占用位数。当前时间相对于时间基点的增量值，单位为秒
     */
    private int idTimeBits = 30;

    /**
     * 工作机器ID占用的位数
     */
    private int idWorkerBits = 16;

    /**
     * 序列号占用的位数
     */
    private int idSeqBits = 7;

    /**
     * 时间基点. 例如 2019-02-20 (毫秒: 1550592000000)
     */
    private String idEpochStr = "2019-02-20";

    /**
     * 时间基点对应的毫秒数
     */
    private long idEpochSeconds = TimeUnit.MILLISECONDS.toSeconds(1550592000000L);

    /**
     * 是否容忍时钟回拨, 默认:true
     */
    private boolean idEnableBackward = true;

    /**
     * 时钟回拨最长容忍时间（秒）
     */
    private long idMaxBackwardSeconds = 1L;




}