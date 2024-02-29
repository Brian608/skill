package org.feather.skill.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.feather.skill.page.BasePage;
import org.feather.skill.service.IGoodsService;
import org.feather.skill.vo.PageSimpleGoodsInfo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: GoodsController
 * @author: feather
 * @description:
 * @since: 2024-02-29 22:00
 * @version: 1.0
 */
@RequiredArgsConstructor
@Api(tags = "商品微服务功能接口")
@Slf4j
@RestController
@RequestMapping("/goods")
public class GoodsController {
    private final IGoodsService goodsService;

    @ApiOperation(value = "详细商品信息", notes = "根据 TableId 查询详细商品信息",
            httpMethod = "POST")
    @PostMapping("/goods-info")
    public FeatherResponse<List<GoodsInfo>> getGoodsInfoByTableId(@RequestBody TableId tableId) {
        return FeatherResponse.success(goodsService.getGoodsInfoByTableId(tableId));
    }

    @ApiOperation(value = "简单商品信息", notes = "获取分页的简单商品信息", httpMethod = "POST")
    @PostMapping("/page-simple-goods-info")
    public FeatherResponse<Page<SimpleGoodsInfo>> getSimpleGoodsInfoByPage(
            @RequestBody BasePage basePage) {
        return FeatherResponse.success(goodsService.getSimpleGoodsInfoByPage(basePage));
    }

    @ApiOperation(value = "简单商品信息", notes = "根据 TableId 查询简单商品信息",
            httpMethod = "POST")
    @PostMapping("/simple-goods-info")
    public FeatherResponse<List<SimpleGoodsInfo>> getSimpleGoodsInfoByTableId(@RequestBody TableId tableId) {
        return FeatherResponse.success(goodsService.getSimpleGoodsInfoByTableId(tableId));
    }

    @ApiOperation(value = "扣减商品库存", notes = "扣减商品库存", httpMethod = "PUT")
    @PutMapping("/deduct-goods-inventory")
    public FeatherResponse<Boolean> deductGoodsInventory(
            @RequestBody List<DeductGoodsInventory> deductGoodsInventories) {
        return FeatherResponse.success(goodsService.deductGoodsInventory(deductGoodsInventories));
    }
}
