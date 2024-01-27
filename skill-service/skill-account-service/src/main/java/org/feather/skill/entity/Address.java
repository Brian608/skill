package org.feather.skill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.skill.account.AddressInfo;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户地址表
 * </p>
 *
 * @author feather
 * @since 2024-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Address extends Model<Address> {

    private static final long serialVersionUID=1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 电话号码
     */
    private String phone;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 详细地址
     */
    private String addressDetail;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    /**
     * <h2>根据 userId + AddressItem 得到 Address</h2>
     * */
    public static Address to(Long userId, AddressInfo.AddressItem addressItem) {

        Address address = new Address();

        address.setUserId(userId);
        address.setUsername(addressItem.getUsername());
        address.setPhone(addressItem.getPhone());
        address.setProvince(addressItem.getProvince());
        address.setCity(addressItem.getCity());
        address.setAddressDetail(addressItem.getAddressDetail());

        return address;
    }

    /**
     * <h2>将 Address 对象转成 AddressInfo</h2>
     * */
    public AddressInfo.AddressItem toAddressItem() {

        AddressInfo.AddressItem addressItem = new AddressInfo.AddressItem();

        addressItem.setId(this.id);
        addressItem.setUsername(this.username);
        addressItem.setPhone(this.phone);
        addressItem.setProvince(this.province);
        addressItem.setCity(this.city);
        addressItem.setAddressDetail(this.addressDetail);
        addressItem.setCreateTime(this.createTime);
        addressItem.setUpdateTime(this.updateTime);

        return addressItem;
    }

}
