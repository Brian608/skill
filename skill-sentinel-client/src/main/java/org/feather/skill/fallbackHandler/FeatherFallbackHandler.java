package org.feather.skill.fallbackHandler;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.JwtToken;
import org.feather.skill.common.vo.UserNameAndPassword;

/**
 * @projectName: skill
 * @package: org.feather.skill.fallbackHandler
 * @className: FeatherFallbackHandler
 * @author: feather
 * @description: sentinel 回退降级的兜底策略
 * @since: 2024-04-20 08:36
 * @version: 1.0
 */
@Slf4j
public class FeatherFallbackHandler {
    /**
     * description: getTokenFromAuthorityService  方法的fallback
     * @param userNameAndPassword
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-04-20 08:38
     **/
    public  static JwtToken getTokenFromAuthorityServiceFallback(
            UserNameAndPassword userNameAndPassword
    ){
        log.error("get token from authority service fallback :[{}]",
                JSON.toJSONString(userNameAndPassword));
        return new JwtToken("feather- token");
    }

    /**
     * description:  ignoreException 方法的 fallback
     * @param code
     * @return {@link JwtToken}
     * @author: feather
     * @since: 2024-04-20 08:41
     **/
    public static JwtToken ignoreExceptionFallback(Integer code) {
        log.error("ignore exception input code: [{}] has trigger exception", code);
        return new JwtToken("feather-fallback");
    }
}
