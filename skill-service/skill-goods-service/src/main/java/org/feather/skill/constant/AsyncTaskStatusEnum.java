package org.feather.skill.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @projectName: skill
 * @package: org.feather.skill.constant
 * @className: AsyncTaskStatusEnum
 * @author: feather
 * @description: 异步任务状态枚举
 * @since: 2024-02-01 21:36
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum AsyncTaskStatusEnum {

    STARTED(0, "已经启动"),
    RUNNING(1, "正在运行"),
    SUCCESS(2, "执行成功"),
    FAILED(3, "执行失败"),
    ;

    /** 执行状态编码 */
    private final int state;

    /** 执行状态描述 */
    private final String stateInfo;
}
