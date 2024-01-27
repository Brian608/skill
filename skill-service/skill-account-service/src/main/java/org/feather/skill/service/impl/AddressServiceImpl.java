package org.feather.skill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Address;
import org.feather.skill.mapper.AddressMapper;
import org.feather.skill.service.IAddressService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-23
 */
@Service
public class  AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    @Override
    public TableId createAddressInfo(AddressInfo addressInfo) {
        return null;
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {
        return null;
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {
        return null;
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {
        return null;
    }
}
