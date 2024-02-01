package org.feather.skill.service.async;

import lombok.extern.slf4j.Slf4j;
import org.feather.skill.goods.GoodsInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.async
 * @className: AsyncServiceImpl
 * @author: feather
 * @description:
 * @since: 2024-02-01 21:35
 * @version: 1.0
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AsyncServiceImpl implements  IAsyncService{
    @Override
    public void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId) {

    }
}
