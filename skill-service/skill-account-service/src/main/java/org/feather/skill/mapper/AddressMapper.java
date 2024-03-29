package org.feather.skill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.feather.skill.entity.Address;

/**
 * <p>
 * 用户地址表 Mapper 接口
 * </p>
 *
 * @author feather
 * @since 2024-01-23
 */
@Mapper
public interface AddressMapper extends BaseMapper<Address> {

}
