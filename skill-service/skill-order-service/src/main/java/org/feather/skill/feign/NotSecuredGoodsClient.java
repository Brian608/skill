package org.feather.skill.feign;

import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign
 * @className: NotSecuredGoodsClient
 * @author: feather
 * @description: 不安全的商品服务 Feign 接口
 * @since: 2024-03-30 17:47
 * @version: 1.0
 */
@FeignClient(
        contextId = "NotSecuredGoodsClient",
        value = "skill-goods-service"
)
public interface NotSecuredGoodsClient {


    /**
     * description: 扣减商品库存
     * @param deductGoodsInventories 扣减库存信息
     * @return {@link FeatherResponse<Boolean>}
     * @author: feather
     * @since: 2024-03-30 17:50
     **/
    @RequestMapping(
            value = "/skill-goods-service/goods/deduct-goods-inventory",
            method = RequestMethod.PUT
    )
    FeatherResponse<Boolean> deductGoodsInventory(
            @RequestBody List<DeductGoodsInventory> deductGoodsInventories);


        /**
         * description: 根据 ids 查询简单的商品信息
         * @param tableId tableId
         * @return {@link FeatherResponse<List<SimpleGoodsInfo>>}
         * @author: feather
         * @since: 2024-03-30 17:52
         **/
    @RequestMapping(
            value = "/skill-goods-service/goods/simple-goods-info",
            method = RequestMethod.POST
    )
    FeatherResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(
            @RequestBody TableId tableId);
}
