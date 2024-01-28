package org.feather.skill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.feather.skill.entity.Goods;

/**
 * <p>
 * 商品表 Mapper 接口
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

}
