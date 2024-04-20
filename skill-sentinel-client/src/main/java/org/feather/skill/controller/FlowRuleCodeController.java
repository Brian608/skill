package org.feather.skill.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.blockHandler.FeatherBlockHandler;
import org.feather.skill.common.vo.FeatherResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: FlowRuleCodeController
 * @author: feather
 * @description: 流控规则硬编码
 * @since: 2024-04-12 08:21
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/code")
public class FlowRuleCodeController {

    @PostConstruct
    public  void init(){
        //流控规则集合
        List<FlowRule> flowRules=new ArrayList<>();
        //创建流控规则
        FlowRule flowRule=new FlowRule();
        //设置流控规则 QPS，限流阈值类型(QPS ,并发线程数)
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
        //流控手段
        flowRule.setControlBehavior(RuleConstant.CONTROL_BEHAVIOR_DEFAULT);
        //设置受保护的资源
        flowRule.setRefResource("flowRuleCode");
        //设置受保护的资源阈值
        flowRule.setCount(1);
        flowRules.add(flowRule);
        //加载配置好的规则
        FlowRuleManager.loadRules(flowRules);
    }
    @RequestMapping("/flow-rule")
  //  @SentinelResource(value = "flowRuleCode")
//    @SentinelResource(value = "flowRuleCode",
//            blockHandler = "handleException")
    @SentinelResource(value = "flowRuleCode",
            blockHandler = "featherHandleBlockException",
            blockHandlerClass = FeatherBlockHandler.class)
    public FeatherResponse<String> flowRule(){
        log.info("request flowRuleCode");
        return  new FeatherResponse<>("0","","feather-skill");
    }

   /**
    * description: 当限流异常抛出时，指定调用的方法 是一个兜底策略
    * @param exception
    * @return {@link FeatherResponse<String>}
    * @author: feather
    * @since: 2024-04-12 08:37
    **/
    public FeatherResponse<String> handleException(BlockException exception){
        log.error("has block exception :[{}]", JSON.toJSON(exception.getRule()));
        return new FeatherResponse<>("-1","flow rule exception",exception.getClass().getCanonicalName());

    }
}
