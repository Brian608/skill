package org.feather.skill.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author feather
 * @since 2024-01-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Goods extends Model<Goods> {

    private static final long serialVersionUID=1L;

    /**
     * 自增主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品类别
     */
    private String goodsCategory;

    /**
     * 品牌分类
     */
    private String brandCategory;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品图片
     */
    private String goodsPic;

    /**
     * 商品描述信息
     */
    private String goodsDescription;

    /**
     * 商品状态
     */
    private Integer goodsStatus;

    /**
     * 商品价格
     */
    private Integer price;

    /**
     * 总供应量
     */
    private Long supply;

    /**
     * 库存
     */
    private Long inventory;

    /**
     * 商品属性
     */
    private String goodsProperty;

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


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
