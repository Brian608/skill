package org.feather.skill.filter.factory;

import org.feather.skill.filter.HeadTokenGateWayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

/**
 * @projectName: skill
 * @package: org.feather.skill.filter.factory
 * @className: HeaderTokenGatewayFilterFactory
 * @author: feather
 * @description:
 * @since: 20-Jan-24 1:56 PM
 * @version: 1.0
 */
@Component
public class HeaderTokenGatewayFilterFactory
        extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return  new HeadTokenGateWayFilter();
    }
}
