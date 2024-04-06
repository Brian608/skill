package org.feather.skill.feign;

import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.feign.hystrix.AddressClientHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: AddressClient
 * @author: feather
 * @description: 用户账户服务 Feign 接口(安全的)
 * @since: 2024-03-31 21:27
 * @version: 1.0
 */
@FeignClient(
        contextId = "AddressClient",
        value = "skill-account-service",
        fallback = AddressClientHystrix.class
)
public interface AddressClient {

    /**
     * description: 根据 id 查询地址信息
     * @param tableId tableId
     * @return {@link FeatherResponse<AddressInfo>}
     * @author: feather
     * @since: 2024-03-31 21:29
     **/
    @RequestMapping(
            value = "/skill-account-service/address/address-info-by-table-id",
            method = RequestMethod.POST
    )
    FeatherResponse<AddressInfo> getAddressInfoByTablesId(@RequestBody TableId tableId);
}
