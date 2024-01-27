package org.feather.skill.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.LoginUserInfo;
import org.feather.skill.entity.Address;
import org.feather.skill.enums.AddressErrorCodeEnums;
import org.feather.skill.exception.BusinessException;
import org.feather.skill.filter.AccessContext;
import org.feather.skill.mapper.AddressMapper;
import org.feather.skill.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户地址表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-23
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Transactional(rollbackFor = Exception.class)
@Service
public class  AddressServiceImpl extends ServiceImpl<AddressMapper, Address> implements IAddressService {

    private final AddressMapper addressMapper;


    @Override
    public List<Address> findAllByUserId(Long userId) {
        return this.addressMapper.selectList(new LambdaQueryWrapper<Address>().eq(Address::getUserId,userId));
    }

    @Override
    public Boolean createAddressInfo(AddressInfo addressInfo) {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        Long userInfoId = loginUserInfo.getId();
        List<Address> addressList=addressInfo.getAddressItems().stream().map(a->Address.to(userInfoId,a)).collect(Collectors.toList());
        //保存数据
        boolean saveBatch = this.saveBatch(addressList);
        log.info("create address  success info:[{}], userId:[{}]",saveBatch,userInfoId);
        return saveBatch;
    }

    @Override
    public AddressInfo getCurrentAddressInfo() {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        Long userInfoId = loginUserInfo.getId();
        //根据用户id获取用户地址信息
        List<Address> allByUserId = this.findAllByUserId(userInfoId);
        List<AddressInfo.AddressItem> addressItems=allByUserId.stream().map(Address::toAddressItem).collect(Collectors.toList());
        return new AddressInfo(userInfoId,addressItems);
    }

    @Override
    public AddressInfo getAddressInfoById(Long id) {
        Address address = this.baseMapper.selectById(id);
        if (null==address){
            throw  new BusinessException(AddressErrorCodeEnums.NOT_EXIST);
        }
        return new AddressInfo(address.getUserId(), Collections.singletonList(address.toAddressItem()));
    }

    @Override
    public AddressInfo getAddressInfoByTableId(TableId tableId) {
        List<Long> ids=tableId.getIds().stream().map(TableId.Id::getId).collect(Collectors.toList());
        log.info("get address info by table id:[{}]", JSON.toJSONString(ids));
        List<Address> addressList = this.getBaseMapper().selectBatchIds(ids);
        if (CollectionUtils.isEmpty(addressList)){
            return  new AddressInfo(-1L,Collections.emptyList());
        }
        List<AddressInfo.AddressItem> addressItems=addressList.stream().map(Address::toAddressItem).collect(Collectors.toList());
        return new AddressInfo(addressList.get(0).getUserId(), addressItems);
    }
}
