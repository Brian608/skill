package org.feather.skill.common.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    public FeatherResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

}
