package org.feather.skill.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.skill.constant.AsyncTaskStatusEnum;

import java.util.Date;

/**
 * @projectName: skill
 * @package: org.feather.skill.vo
 * @className: AsyncTaskInfo
 * @author: feather
 * @description: 异步任务执行信息
 * @since: 2024-02-01 21:36
 * @version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AsyncTaskInfo {

    /** 异步任务 id */
    private String taskId;

    /** 异步任务执行状态 */
    private AsyncTaskStatusEnum status;

    /** 异步任务开始时间 */
    private Date startTime;

    /** 异步任务结束时间 */
    private Date endTime;

    /** 异步任务总耗时 */
    private String totalTime;
}
