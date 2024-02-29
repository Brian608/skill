package org.feather.skill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.service.async.AsyncTaskManager;
import org.feather.skill.vo.AsyncTaskInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: AsyncGoodsController
 * @author: feather
 * @description: 异步任务服务  对外提供api
 * @since: 2024-02-29 21:28
 * @version: 1.0
 */
@RequiredArgsConstructor
@Api(tags = "商品异步入库服务")
@Slf4j
@RestController
@RequestMapping("/async-goods")
public class AsyncGoodsController {

    private final AsyncTaskManager asyncTaskManager;

    @ApiOperation(value = "导入商品", notes = "导入商品进入到商品表", httpMethod = "POST")
    @PostMapping("/import-goods")
    public FeatherResponse<AsyncTaskInfo> importGoods(@RequestBody List<GoodsInfo> goodsInfos) {
        return FeatherResponse.success(asyncTaskManager.submit(goodsInfos));
    }

    @ApiOperation(value = "查询状态", notes = "查询异步任务的执行状态", httpMethod = "GET")
    @GetMapping("/task-info")
    public FeatherResponse<AsyncTaskInfo> getTaskInfo(@RequestParam String taskId) {
        return  FeatherResponse.success(asyncTaskManager.getTaskInfo(taskId));
    }

}
