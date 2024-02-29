package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Goods;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.feather.skill.page.BasePage;
import org.feather.skill.vo.PageSimpleGoodsInfo;

import java.util.List;

/**
 * <p>
 * 商品表 服务类
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
public interface IGoodsService extends IService<Goods> {

    Goods findByGoodsCategoryAndBrandCategoryAndGoodsName(
            String goodsCategory, String brandCategory,
            String goodsName
    );

    /**
     * description: 根据  tableId  查询商品信息
     * @param tableId  tableId
     * @return {@link List<GoodsInfo>}
     * @author: feather
     * @since: 2024-02-01 21:30
     **/
    List<GoodsInfo>  getGoodsInfoByTableId(TableId tableId);

    /**
     * description: 获取分页的商品信息
     * @param basePage 分页对象
     * @return {@link PageSimpleGoodsInfo}
     * @author: feather
     * @since: 2024-02-01 21:31
     **/
    Page<SimpleGoodsInfo> getSimpleGoodsInfoByPage(BasePage basePage);

   /**
    * description:  根据 TableId 查询简单商品信息
    * @param tableId
    * @return {@link List<SimpleGoodsInfo>}
    * @author: feather
    * @since: 2024-02-01 21:31
    **/
    List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId);


     /**
      * description:  扣减商品库存
      * @param deductGoodsInventories
      * @return {@link Boolean}
      * @author: feather
      * @since: 2024-02-01 21:32
      **/
    Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories);

}
