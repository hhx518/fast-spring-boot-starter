package com.hlx.fast.common.exception;

import com.hlx.fast.common.entity.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;

/**
 * 异常处理器
 */
@RestControllerAdvice
@Order(-1000)
public class FastExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(BizException.class)
    public R handleRRException(BizException e) {
        R r = new R();
        r.put("code", e.getCode());
        r.put("msg", e.getMessage());
        return r;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public R handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return R.error("数据库中已存在该记录");
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R handleDuplicateKeyException(HttpMessageNotReadableException e) {
        logger.error(e.getMessage(), e);
        return R.error("参数传值不正确:"+e.getMessage());
    }


	/*@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return R.error("没有权限，请联系管理员授权");
	}*/
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public R handlerNoHandlerFoundException(NoHandlerFoundException e) {
        // logger.error(e.getMessage(), e);
        return R.error("404:"+e.getMessage());
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数", e);
        return R.error("缺少请求参数"+e.getMessage());
    }
    @ExceptionHandler(Exception.class)
    public R handleException(Exception e) {
        // logger.error(e.getMessage(), e);
        return R.error(e.getMessage());
    }
}
