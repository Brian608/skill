package org.feather.skill.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.skill.common.exception.BaseErrorCodeEnum;

import java.io.Serializable;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.vo
 * @className: FeatherResponse
 * @author: feather
 * @description: 通用结果返回对象
 * @since: 09-Jan-24 10:07 PM
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FeatherResponse<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 错误码 */
    private Integer code;

    /** 错误消息 */
    private String message;

    /** 泛型响应数据 */
    private T Data;

    /**
     * 默认错误码
     */
    private static final Integer DEFAULT_ERROR_CODE = -1;

    public FeatherResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
    /**
     * description: 成功不返回数据
     * @return {@link FeatherResponse<T>}
     * @author: feather
     * @since: 09-Jan-24 10:42 PM
     **/
    public static <T> FeatherResponse<T> success() {
        return new FeatherResponse<>( null, null, null);
    }

   /**
    * description:  成功，返回数据
    * @param data 返回数据
    * @return {@link FeatherResponse<T>}
    * @author: feather
    * @since: 09-Jan-24 10:42 PM
    **/
    public static <T> FeatherResponse<T> success(T data) {
        return new FeatherResponse<>( null, null, data);
    }

    /**
     * description: 失败，固定状态码
     * @param errorMsg 错误消息
     * @return {@link FeatherResponse<T>}
     * @author: feather
     * @since: 09-Jan-24 10:42 PM
     **/
    public static <T> FeatherResponse<T> fail(String errorMsg) {
        return new FeatherResponse<>( DEFAULT_ERROR_CODE, errorMsg,null);
    }

    /**
     * description: 失败，自定义错误码和信息
     * @param errorCode 错误码
     * @param errorMsg 错误消息
     * @return {@link FeatherResponse<T>}
     * @author: feather
     * @since: 09-Jan-24 10:43 PM
     **/
    public static <T> FeatherResponse<T> fail(Integer errorCode, String errorMsg) {
        return new FeatherResponse<>( errorCode, errorMsg,null);
    }

    /**
     * description:  失败，枚举类定义错误码和信息
     * @param baseErrorCodeEnum 错误枚举
     * @return {@link FeatherResponse<T>}
     * @author: feather
     * @since: 09-Jan-24 10:43 PM
     **/
    public static <T> FeatherResponse<T> fail(BaseErrorCodeEnum baseErrorCodeEnum) {
        return new FeatherResponse<>( baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg());
    }

}
