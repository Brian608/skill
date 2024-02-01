package org.feather.skill.service.async;

import org.feather.skill.goods.GoodsInfo;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.async
 * @className: IAsyncService
 * @author: feather
 * @description: 异步服务接口定义
 * @since: 2024-02-01 21:34
 * @version: 1.0
 */
public interface IAsyncService {

   /**
    * description:  异步将商品信息保存下来
    * @param goodsInfos
    * @param taskId
    * @author: feather
    * @since: 2024-02-01 21:34
    **/
    void asyncImportGoods(List<GoodsInfo> goodsInfos, String taskId);
}
