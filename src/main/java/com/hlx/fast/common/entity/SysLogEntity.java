package com.hlx.fast.common.entity;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data

public class SysLogEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    /**
     * 用户名
     */
    private String username;

    private String url;
    /**
     * 用户操作
     */
    private String operation;
    /**
     * 请求方法
     */
    private String method;
    /**
     * 请求参数
     */
    private String params;

    private Object result;
    /**
     * 执行时长(毫秒)
     */
    private Long time;
    /**
     * IP地址
     */
    private String ip;
    /**
     * 创建时间
     */
    @JSONField(format = "yyyy-MM-dd HH:mm:ss")
    private Date createDate;
    private String error;
}
