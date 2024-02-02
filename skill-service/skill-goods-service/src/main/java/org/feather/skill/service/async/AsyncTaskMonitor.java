package org.feather.skill.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.feather.skill.constant.AsyncTaskStatusEnum;
import org.feather.skill.vo.AsyncTaskInfo;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.async
 * @className: AsyncTaskMonitor
 * @author: feather
 * @description: 异步任务执行监控切面
 * @since: 2024-02-02 21:20
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Aspect
@Component
public class AsyncTaskMonitor {

    private final AsyncTaskManager asyncTaskManager;

    /**
     * description:  异步任务执行的环绕切面
     * @param joinPoint
     * @return {@link Object}
     * @author: feather
     * @since: 2024-02-02 21:22
     **/
    @Around("execution(* org.feather.skill.service.async.AsyncServiceImpl.*(..))")
    public  Object taskHandle(ProceedingJoinPoint joinPoint){
        //获取  taskId , 调用异步任务传入的第二个参数
        String taskId=joinPoint.getArgs()[1].toString();
        //获取任务信息 在提交任务的时候就已经放入到容器当中了
        AsyncTaskInfo taskInfo = asyncTaskManager.getTaskInfo(taskId);
        log.info("AsyncTaskMonitor is monitoring async task :[{}]",taskId);
        //设置为运行状态
        taskInfo.setStatus(AsyncTaskStatusEnum.RUNNING);
        //重新放入到容器
        asyncTaskManager.setTaskInfo(taskInfo);
        AsyncTaskStatusEnum status;

        Object result;
        try {
            //异步任务执行
           result= joinPoint.proceed();
            status=AsyncTaskStatusEnum.SUCCESS;
        } catch (Throwable e) {
            result=null;
            status=AsyncTaskStatusEnum.FAILED;
            log.error("AsyncTaskMonitor: async task [{}] is failed, Error Info: [{}]",
                    taskId, e.getMessage(), e);
        }
        // 设置异步任务其他的信息, 再次重新放入到容器中
        taskInfo.setEndTime(new Date());
        taskInfo.setStatus(status);
        taskInfo.setTotalTime(String.valueOf(
                taskInfo.getEndTime().getTime() - taskInfo.getStartTime().getTime()
        ));
        asyncTaskManager.setTaskInfo(taskInfo);
        return result;


    }


}
