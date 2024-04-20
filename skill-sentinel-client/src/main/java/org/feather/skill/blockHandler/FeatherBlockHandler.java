package org.feather.skill.blockHandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.FeatherResponse;

/**
 * @projectName: skill
 * @package: org.feather.skill.blockHandler
 * @className: FeatherBlockHandler
 * @author: feather
 * @description:
 * @since: 2024-04-12 08:38
 * @version: 1.0
 */
@Slf4j
public class FeatherBlockHandler {
    /**
     * description: 通用限流处理方法 这个方法必须是 static
     * @param exception
     * @return {@link FeatherResponse<String>}
     * @author: feather
     * @since: 2024-04-12 08:39
     **/
    public static FeatherResponse<String> featherHandleBlockException(BlockException exception){
        log.error("trigger feather block handler :[{}] ,[{}]",
                JSON.toJSONString(exception.getRule())
                ,exception.getRuleLimitApp());
        return  new FeatherResponse<>("-1","flow rule trigger block exception",null);

    }
}
