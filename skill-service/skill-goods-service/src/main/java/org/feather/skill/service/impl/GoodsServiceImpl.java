package org.feather.skill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.feather.skill.entity.Goods;
import org.feather.skill.mapper.GoodsMapper;
import org.feather.skill.service.IGoodsService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public Goods findByGoodsCategoryAndBrandCategoryAndGoodsName(String goodsCategory, String brandCategory, String goodsName) {
        return null;
    }
}
