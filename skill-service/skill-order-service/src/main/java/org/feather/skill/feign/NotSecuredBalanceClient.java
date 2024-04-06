package org.feather.skill.feign;

import org.feather.skill.account.BalanceInfo;
import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: NotSecuredBalanceClient
 * @author: feather
 * @description: 用户账户服务  feign 接口
 * @since: 2024-03-30 17:42
 * @version: 1.0
 */
@FeignClient(
        contextId = "NotSecuredBalanceClient",
        value = "skill-account-service"
)
public interface NotSecuredBalanceClient {
    /**
     * description: 扣减库存
     * @param balanceInfo 余额信息
     * @return {@link FeatherResponse<BalanceInfo>}
     * @author: feather
     * @since: 2024-03-30 17:44
     **/
    @RequestMapping(
            value = "/skill-account-service/balance/deduct-balance",
            method = RequestMethod.PUT
    )
    FeatherResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo);

}
