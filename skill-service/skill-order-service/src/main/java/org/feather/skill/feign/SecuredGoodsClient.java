package org.feather.skill.feign;

import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.feign.hystrix.GoodsClientHystrix;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: SecuredGoodsClient
 * @author: feather
 * @description: 安全的商品服务 Feign 接口
 * @since: 2024-03-31 21:23
 * @version: 1.0
 */
@FeignClient(
        contextId = "SecuredGoodsClient",
        value = "e-commerce-goods-service",
        fallback = GoodsClientHystrix.class
)
public interface SecuredGoodsClient {

    /**
     * description:  简单商品信息
     * @param tableId  tableId
     * @return {@link FeatherResponse<List<SimpleGoodsInfo>>}
     * @author: feather
     * @since: 2024-03-31 21:24
     **/
    @RequestMapping(
            value = "/skill-goods-service/goods/simple-goods-info",
            method = RequestMethod.POST
    )
    FeatherResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId( @RequestBody TableId tableId);
}
