package org.feather.skill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Goods;
import org.feather.skill.goods.DeductGoodsInventory;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.goods.SimpleGoodsInfo;
import org.feather.skill.mapper.GoodsMapper;
import org.feather.skill.service.IGoodsService;
import org.feather.skill.vo.PageSimpleGoodsInfo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
@RequiredArgsConstructor
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {
    private  final GoodsMapper goodsMapper;

    @Override
    public Goods findByGoodsCategoryAndBrandCategoryAndGoodsName(String goodsCategory, String brandCategory, String goodsName) {
        return this.getOne(
                new LambdaQueryWrapper<Goods>().
                        eq(Goods::getGoodsCategory,goodsCategory).
                        eq(Goods::getBrandCategory,brandCategory)
                        .eq(Goods::getGoodsName,goodsName));
    }

    @Override
    public List<GoodsInfo> getGoodsInfoByTableId(TableId tableId) {
        return null;
    }

    @Override
    public PageSimpleGoodsInfo getSimpleGoodsInfoByPage(int page) {
        return null;
    }

    @Override
    public List<SimpleGoodsInfo> getSimpleGoodsInfoByTableId(TableId tableId) {
        return null;
    }

    @Override
    public Boolean deductGoodsInventory(List<DeductGoodsInventory> deductGoodsInventories) {
        return null;
    }
}
