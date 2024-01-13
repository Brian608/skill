package org.feather.skill.exception;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.enums.CommonErrorCodeEnum;
import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.core.annotation.Order;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Iterator;
import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.exception
 * @className: GlobalExceptionHandler
 * @author: feather
 * @description: 默认的Controller全局异常处理增强组件
 * @since: 10-Jan-24 10:04 AM
 * @version: 1.0
 */
@Slf4j
@RestControllerAdvice
@Order
public class GlobalExceptionHandler {
    // =========== 系统级别未知异常 =========

    @ExceptionHandler(value = Exception.class)
    public FeatherResponse<Object> handle(Exception e) {
        log.error("[ 系统未知错误 ]", e);
        return FeatherResponse.fail(CommonErrorCodeEnum.SYSTEM_UNKNOWN_ERROR);
    }

    // =========== 客户端异常 =========

   /**
    * description: 1001 HTTP请求方法类型错误
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:40 AM
    **/
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public FeatherResponse<Object> handle(HttpRequestMethodNotSupportedException e) {
        log.error("[客户端HTTP请求方法错误]", e);
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_HTTP_METHOD_ERROR);
    }

   /**
    * description: 1002 客户端请求体参数校验不通过
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:41 AM
    **/
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public FeatherResponse<Object> handle(MethodArgumentNotValidException e) {
        log.error("[客户端请求体参数校验不通过]", e);
        String errorMsg = this.handle(e.getBindingResult().getFieldErrors());
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_BODY_CHECK_ERROR.getErrorCode(), errorMsg);
    }

    private String handle(List<FieldError> fieldErrors) {
        StringBuilder sb = new StringBuilder();
        for (FieldError obj : fieldErrors) {
            sb.append(obj.getField());
            sb.append("=[");
            sb.append(obj.getDefaultMessage());
            sb.append("]  ");
        }
        return sb.toString();
    }

    /**
     * description: 1003 客户端请求体JSON格式错误或字段类型不匹配
     * @param e error
     * @return {@link FeatherResponse<Object>}
     * @author: feather
     * @since: 10-Jan-24 10:41 AM
     **/
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public FeatherResponse<Object> handle(HttpMessageNotReadableException e) {
        log.error("[客户端请求体JSON格式错误或字段类型不匹配]", e);
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_BODY_FORMAT_ERROR);
    }

   /**
    * description:  1004 客户端URL中的参数类型错误
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:41 AM
    **/
    @ExceptionHandler(value = BindException.class)
    public FeatherResponse<Object> handle(BindException e) {
        log.error("[客户端URL中的参数类型错误]", e);
        FieldError fieldError = e.getBindingResult().getFieldError();
        String errorMsg = null;
        if (fieldError != null) {
            errorMsg = fieldError.getDefaultMessage();
            if (errorMsg != null && errorMsg.contains("java.lang.NumberFormatException")) {
                errorMsg = fieldError.getField() + "参数类型错误";
            }
        }
        if (errorMsg != null && !errorMsg.isEmpty()) {
            return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_PATH_VARIABLE_ERROR.getErrorCode(), errorMsg);
        }
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_PATH_VARIABLE_ERROR);
    }

   /**
    * description:  1005 客户端请求参数校验不通过
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:41 AM
    **/
    @ExceptionHandler(value = ConstraintViolationException.class)
    public FeatherResponse<Object> handle(ConstraintViolationException e) {
        log.error("[客户端请求参数校验不通过]", e);
        Iterator<ConstraintViolation<?>> it = e.getConstraintViolations().iterator();
        String errorMsg = null;
        if (it.hasNext()) {
            errorMsg = it.next().getMessageTemplate();
        }
        if (errorMsg != null && !errorMsg.isEmpty()) {
            return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_PARAM_CHECK_ERROR.getErrorCode(), errorMsg);
        }
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_PARAM_CHECK_ERROR);
    }


    /**
     * description: 1006 客户端请求缺少必填的参数
     * @param e error
     * @return {@link FeatherResponse<Object>}
     * @author: feather
     * @since: 10-Jan-24 10:42 AM
     **/
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public FeatherResponse<Object> handle(MissingServletRequestParameterException e) {
        log.error("[客户端请求缺少必填的参数]", e);
        String errorMsg = null;
        String parameterName = e.getParameterName();
        if (!parameterName.isEmpty()) {
            errorMsg = parameterName + "不能为空";
        }
        if (errorMsg != null) {
            return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_PARAM_REQUIRED_ERROR.getErrorCode(), errorMsg);
        }
        return FeatherResponse.fail(CommonErrorCodeEnum.CLIENT_REQUEST_PARAM_REQUIRED_ERROR);
    }

    // =========== 服务端异常 =========

   /**
    * description:  2001 业务方法参数检查不通过
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:42 AM
    **/
    @ExceptionHandler(value = IllegalArgumentException.class)
    public FeatherResponse<Object> handle(IllegalArgumentException e) {
        log.error("[业务方法参数检查不通过]", e);
        return FeatherResponse.fail(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
    }

   /**
    * description: 系统自定义业务异常
    * @param e error
    * @return {@link FeatherResponse<Object>}
    * @author: feather
    * @since: 10-Jan-24 10:42 AM
    **/
    @ExceptionHandler(value = BusinessException.class)
    public FeatherResponse<Object> handle(BusinessException e) {
        log.error("[ 业务异常 ]", e);
        return FeatherResponse.fail(e.getErrorCode(), e.getErrorMsg());
    }

}
