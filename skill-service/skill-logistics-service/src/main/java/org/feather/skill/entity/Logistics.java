package org.feather.skill.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;


/**
 * @projectName: skill
 * @package: org.feather.skill.entity
 * @className: Logistics
 * @author: feather
 * @description: 物流
 * @since: 2024-04-06 15:37
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Logistics extends Model<Logistics> {

    private static final long serialVersionUID=1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户 id
     */
    private Long userId;

    /**
     * 订单 id
     */
    private Long orderId;

    /**
     * 用户地址记录 id
     */
    private Long addressId;

    /**
     * 备注信息(json 存储)
     */
    private String extraInfo;

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

    public Logistics(Long userId, Long orderId, Long addressId, String extraInfo) {

        this.userId = userId;
        this.orderId = orderId;
        this.addressId = addressId;
        this.extraInfo = StringUtils.isNotBlank(extraInfo) ? extraInfo : "{}";
    }

}

