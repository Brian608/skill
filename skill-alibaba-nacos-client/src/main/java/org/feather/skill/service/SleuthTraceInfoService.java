package org.feather.skill.service;

import brave.Tracer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: SleuthTraceInfoService
 * @author: feather
 * @description:  使用代码更直观 的看到sleuth 生成的相关跟踪信息
 * @since: 21-Jan-24 10:34 AM
 * @version: 1.0
 */
@Slf4j
@Service
public class SleuthTraceInfoService {
    /** brave.Tracer 跟踪对象 */
    private final  Tracer tracer;

    public SleuthTraceInfoService(Tracer tracer) {
        this.tracer = tracer;
    }
    /**
     * description: 打印当前的跟踪信息到日志中
     * @author: feather
     * @since: 21-Jan-24 10:37 AM
     **/
    public  void logCurrentTraceInfo(){
        log.info("Sleuth trace id :[{}]",tracer.currentSpan().context().traceId());
        log.info("Sleuth span id :[{}]",tracer.currentSpan().context().spanId());
    }
}
