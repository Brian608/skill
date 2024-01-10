package org.feather.skill.mvcconfig.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @projectName: skill
 * @package: org.feather.skill.mvcconfig.advice.annotation
 * @className: IgnoreResponseAdvice
 * @author: feather
 * @description: 忽略统一响应注解定义
 * @since: 10-Jan-24 1:28 PM
 * @version: 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IgnoreResponseAdvice {
}
