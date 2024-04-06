package org.feather.skill.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @projectName: skill
 * @package: org.feather.skill.entity
 * @className: Order
 * @author: feather
 * @description: 订单实体
 * @since: 2024-03-30 16:48
 * @version: 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Orders extends Model<Orders> {

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
     * 用户地址记录 id
     */
    private Long addressId;

    /**
     * 订单详情(json 存储, goodsId, count)
     */
    private String orderDetail;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    public Orders(Long userId, Long addressId, String orderDetail){
        this.userId=userId;
        this.addressId=addressId;
        this.orderDetail=orderDetail;
    }


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
