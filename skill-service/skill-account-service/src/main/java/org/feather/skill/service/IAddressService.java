package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.entity.Address;

import java.util.List;

/**
 * <p>
 * 用户地址表 服务类
 * </p>
 *
 * @author feather
 * @since 2024-01-23
 */
public interface IAddressService extends IService<Address> {

    /**
     * description: 根据用户id 获取用户地址信息
     * @param userId 用户id
     * @return {@link List<Address>}
     * @author: feather
     * @since: 2024-01-27 18:08
     **/
    List<Address> findAllByUserId(Long userId);

    /**
     * description:  创建用户地址信息
     * @param addressInfo 创建地址信息对象
     * @return {@link Boolean}
     * @author: feather
     * @since: 2024-01-27 17:25
     **/
    Boolean  createAddressInfo(AddressInfo addressInfo);

    /**
     * description: 获取当前用户地址信息
     * @return {@link AddressInfo}
     * @author: feather
     * @since: 2024-01-27 17:26
     **/
    AddressInfo getCurrentAddressInfo();

    /**
     * description: 通过id获取地址信息，id 是Address的表主键
     * @param id id
     * @return {@link AddressInfo}
     * @author: feather
     * @since: 2024-01-27 17:42
     **/
    AddressInfo getAddressInfoById(Long id);


    /**
     * description: 通过 tableId 获取用户地址信息
     * @param tableId  tableId
     * @return {@link AddressInfo}
     * @author: feather
     * @since: 2024-01-27 17:43
     **/
    AddressInfo  getAddressInfoByTableId(TableId tableId);



}
