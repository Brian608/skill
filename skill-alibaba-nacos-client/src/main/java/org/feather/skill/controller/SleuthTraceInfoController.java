package org.feather.skill.controller;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.service.SleuthTraceInfoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: SleuthTraceInfoController
 * @author: feather
 * @description:
 * @since: 2024-01-21 10:41
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/sleuth")
public class SleuthTraceInfoController {

    private  final SleuthTraceInfoService sleuthTraceInfoService;

    public SleuthTraceInfoController(SleuthTraceInfoService sleuthTraceInfoService) {
        this.sleuthTraceInfoService = sleuthTraceInfoService;
    }
    /**
     * 打印日志跟踪信息
     */
    @GetMapping("/trace-info")
    public  void logCurrentTraceInfo(){
        sleuthTraceInfoService.logCurrentTraceInfo();
    }
}
