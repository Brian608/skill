package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.entity.Goods;

import java.util.Optional;

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

}
