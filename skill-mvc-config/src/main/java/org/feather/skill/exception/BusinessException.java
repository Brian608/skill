package org.feather.skill.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.skill.common.constants.CoreConstant;
import org.feather.skill.common.enums.BaseErrorCodeEnum;

import java.text.MessageFormat;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.exception
 * @className: BusinessException
 * @author: feather
 * @description: 基础自定义业务异常
 * @since: 10-Jan-24 9:53 AM
 * @version: 1.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class BusinessException  extends RuntimeException{
    private final String errorCode;

    private final String errorMsg;

    public BusinessException(String errorMsg) {
        super(errorMsg);
        this.errorCode = CoreConstant.DEFAULT_ERROR_CODE;
        this.errorMsg = errorMsg;
    }

    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public BusinessException(BaseErrorCodeEnum baseErrorCodeEnum) {
        super(baseErrorCodeEnum.getErrorMsg());
        this.errorCode = baseErrorCodeEnum.getErrorCode();
        this.errorMsg = baseErrorCodeEnum.getErrorMsg();
    }

    public BusinessException(String errorCode, String errorMsg, Object... arguments) {
        super(MessageFormat.format(errorMsg, arguments));
        this.errorCode = errorCode;
        this.errorMsg = MessageFormat.format(errorMsg, arguments);
    }

    public BusinessException(BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) {
        super(MessageFormat.format(baseErrorCodeEnum.getErrorMsg(), arguments));
        this.errorCode = baseErrorCodeEnum.getErrorCode();
        this.errorMsg = MessageFormat.format(baseErrorCodeEnum.getErrorMsg(), arguments);
    }
}
