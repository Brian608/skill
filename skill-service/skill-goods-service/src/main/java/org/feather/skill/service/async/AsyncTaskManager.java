package org.feather.skill.service.async;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.constant.AsyncTaskStatusEnum;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.vo.AsyncTaskInfo;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.async
 * @className: AsyncTaskManager
 * @author: feather
 * @description:  对异步任务执行包装管理，记录并塞入异步任务执行信息
 * @since: 2024-02-02 21:11
 * @version: 1.0
 */
@RequiredArgsConstructor
@Slf4j
@Component
public class AsyncTaskManager {

    /**
     * 异步任务执行信息容器
     */
    private final Map<String, AsyncTaskInfo> taskContainer=new HashMap<>(16);

    private final IAsyncService asyncService;

    /**
     * description:  初始化异步任务
     * @return {@link AsyncTaskInfo}
     * @author: feather
     * @since: 2024-02-02 21:12
     **/
    public  AsyncTaskInfo initTask(){
        AsyncTaskInfo taskInfo=new AsyncTaskInfo();
        //设置一个唯一的异步任务id，只要唯一即可
        taskInfo.setTaskId(UUID.randomUUID().toString());
        taskInfo.setStatus(AsyncTaskStatusEnum.STARTED);
        taskInfo.setStartTime(new Date());

        //初始化的时候就要吧异步任务执行信息放入到存储容器中
        taskContainer.put(taskInfo.getTaskId(),taskInfo);
        return  taskInfo;
    }

   /**
    * description: 提交异步任务
    * @return {@link AsyncTaskInfo}
    * @author: feather
    * @since: 2024-02-02 21:14
    **/
    public AsyncTaskInfo submit(List<GoodsInfo> goodsInfos){
        //初始化一个异步任务监控信息
        AsyncTaskInfo taskInfo=initTask();
        asyncService.asyncImportGoods(goodsInfos,taskInfo.getTaskId());
        return  taskInfo;
    }
    /**
     * description:  设置异步任务执行状态信息
     * @param taskInfo
     * @author: feather
     * @since: 2024-02-02 21:15
     **/
    public  void  setTaskInfo(AsyncTaskInfo taskInfo){
        taskContainer.put(taskInfo.getTaskId(),taskInfo);
    }
    /**
     * description: 获取异步任务执行状态信息
     * @param taskId
     * @return {@link AsyncTaskInfo}
     * @author: feather
     * @since: 2024-02-02 21:17
     **/
    public AsyncTaskInfo getTaskInfo(String taskId){
        return taskContainer.get(taskId);
    }
}
