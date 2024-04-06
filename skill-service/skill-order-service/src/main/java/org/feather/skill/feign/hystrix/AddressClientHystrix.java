package org.feather.skill.feign.hystrix;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.feign.AddressClient;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign.hystrix
 * @className: AddressClientHystrix
 * @author: feather
 * @description: 账户服务熔断降级兜底策略
 * @since: 2024-03-31 21:32
 * @version: 1.0
 */
@Slf4j
@Component
public class AddressClientHystrix  implements AddressClient {
    @Override
    public FeatherResponse<AddressInfo> getAddressInfoByTablesId(TableId tableId) {
        log.error("[account client feign request error in order service] get address info" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new FeatherResponse<>(
                "500",
                "[account client feign request error in order service]",
                new AddressInfo(-1L, Collections.emptyList())
        );
    }
}
