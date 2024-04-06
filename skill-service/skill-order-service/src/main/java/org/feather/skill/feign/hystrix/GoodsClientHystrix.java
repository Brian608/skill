package org.feather.skill.feign.hystrix;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.feign.SecuredGoodsClient;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.feign.hystrix
 * @className: GoodsClientHystrix
 * @author: feather
 * @description: 商品服务熔断降级兜底
 * @since: 2024-03-31 21:29
 * @version: 1.0
 */
@Slf4j
@Component
public class GoodsClientHystrix implements SecuredGoodsClient {
    @Override
    public FeatherResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(TableId tableId) {
        log.error("[goods client feign request error in order service] get simple goods" +
                "error: [{}]", JSON.toJSONString(tableId));
        return new FeatherResponse<>(
                "500",
                "[goods client feign request error in order service]",
                Collections.emptyList()
        );
    }
}
