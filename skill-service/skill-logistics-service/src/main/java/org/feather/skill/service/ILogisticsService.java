package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.entity.Logistics;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.handler.annotation.Payload;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: ILogisticsService
 * @author: feather
 * @description:
 * @since: 2024-04-06 15:40
 * @version: 1.0
 */
public interface ILogisticsService extends IService<Logistics> {

    /**
     * description: 订阅监听订单微服务发送的物流消息
     * @param payload 消息体
     * @author: feather
     * @since: 2024-04-06 15:55
     **/
    @StreamListener("logisticsInput")
    void consumeLogisticsMessage(@Payload Object payload);


}
