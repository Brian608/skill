package org.feather.skill.service;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.TableId;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.page.BasePage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: GoodsServiceTest
 * @author: feather
 * @description:
 * @since: 2024-02-29 21:50
 * @version: 1.0
 */
@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class GoodsServiceTest {
    @Autowired
    private IGoodsService goodsService;

    @Test
    public void testGetGoodsInfoByTableId() {

        List<Long> ids = Arrays.asList(10L, 11L, 12L);
        List<TableId.Id> tIds = ids.stream()
                .map(TableId.Id::new).collect(Collectors.toList());
        log.info("test get goods info by table id: [{}]",
                JSON.toJSONString(goodsService.getGoodsInfoByTableId(new TableId(tIds))));
    }

    @Test
    public void testGetSimpleGoodsInfoByPage() {

        log.info("test get simple goods info by page: [{}]", JSON.toJSONString(
                goodsService.getSimpleGoodsInfoByPage(new BasePage())
        ));
    }

    @Test
    public void testGetSimpleGoodsInfoByTableId() {

        List<Long> ids = Arrays.asList(10L, 11L, 12L);
        List<TableId.Id> tIds = ids.stream()
                .map(TableId.Id::new).collect(Collectors.toList());
        log.info("test get simple goods info by table id: [{}]", JSON.toJSONString(
                goodsService.getSimpleGoodsInfoByTableId(new TableId(tIds))
        ));
    }

    @Test
    public void testDeductGoodsInventory() {

        List<DeductGoodsInventory> deductGoodsInventories = Arrays.asList(
                new DeductGoodsInventory(10L, 100),
                new DeductGoodsInventory(11L, 66)
        );
        log.info("test deduct goods inventory: [{}]",
                goodsService.deductGoodsInventory(deductGoodsInventories));
    }
}
