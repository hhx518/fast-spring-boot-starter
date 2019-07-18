
package com.hlx.fast.common.annotation;

import com.hlx.fast.common.config.Level;

import java.lang.annotation.*;

/**
 * 系统日志注解
 *
 * @author Mark sunlightcs@gmail.com
 */
@Target({ ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {
    String des() default "";

    Level level() default Level.INFO;
}
