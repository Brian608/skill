package org.feather.skill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.entity.Logistics;
import org.feather.skill.mapper.LogisticsMapper;
import org.feather.skill.order.LogisticsMessage;
import org.feather.skill.service.ILogisticsService;
import org.feather.skill.sink.LogisticsSink;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.stereotype.Service;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.impl
 * @className: LogisticsServiceImpl
 * @author: feather
 * @description:
 * @since: 2024-04-06 15:43
 * @version: 1.0
 */
@RequiredArgsConstructor
@EnableBinding(LogisticsSink.class)
@Slf4j
@Service
public class LogisticsServiceImpl extends ServiceImpl<LogisticsMapper, Logistics> implements ILogisticsService {

    @Override
    public void consumeLogisticsMessage(Object payload) {
        log.info("receive and consume logistics message: [{}]", payload.toString());
        LogisticsMessage logisticsMessage = JSON.parseObject(
                payload.toString(), LogisticsMessage.class
        );
        Logistics logistics =new Logistics(
                logisticsMessage.getUserId(),
                logisticsMessage.getOrderId(),
                logisticsMessage.getAddressId(),
                logisticsMessage.getExtraInfo()
        );
        this.save(logistics);
        log.info("consume logistics message success: [{}]", logistics.getId());
    }
}
