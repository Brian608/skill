package org.feather.skill.entity;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.feather.skill.constant.BrandCategoryEnum;
import org.feather.skill.constant.GoodsCategoryEnum;
import org.feather.skill.constant.GoodsStatusEnum;
import org.feather.skill.goods.GoodsInfo;
import org.feather.skill.goods.SimpleGoodsInfo;

import java.io.Serializable;
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


    /**
     * <h2>将 GoodsInfo 转成实体对象</h2>
     * */
    public static Goods to(GoodsInfo goodsInfo) {

        Goods goods = new Goods();

        goods.setGoodsCategory(GoodsCategoryEnum.of(goodsInfo.getGoodsCategory()).getCode());
        goods.setBrandCategory(BrandCategoryEnum.of(goodsInfo.getBrandCategory()).getCode());
        goods.setGoodsName(goodsInfo.getGoodsName());
        goods.setGoodsPic(goodsInfo.getGoodsPic());
        goods.setGoodsDescription(goodsInfo.getGoodsDescription());
        goods.setGoodsStatus(GoodsStatusEnum.ONLINE.getStatus());  // 可以增加一个审核的过程
        goods.setPrice(goodsInfo.getPrice());
        goods.setSupply(goodsInfo.getSupply());
        goods.setInventory(goodsInfo.getInventory());
        goods.setGoodsProperty(
                JSON.toJSONString(goodsInfo.getGoodsProperty())
        );

        return goods;
    }

    /**
     * <h2>将实体对象转成 GoodsInfo 对象</h2>
     * */
    public GoodsInfo toGoodsInfo() {

        GoodsInfo goodsInfo = new GoodsInfo();

        goodsInfo.setId(this.id);
        goodsInfo.setGoodsCategory(this.goodsCategory);
        goodsInfo.setBrandCategory(this.brandCategory);
        goodsInfo.setGoodsName(this.goodsName);
        goodsInfo.setGoodsPic(this.goodsPic);
        goodsInfo.setGoodsDescription(this.goodsDescription);
        goodsInfo.setGoodsStatus(this.goodsStatus);
        goodsInfo.setPrice(this.price);
        goodsInfo.setGoodsProperty(
                JSON.parseObject(this.goodsProperty, GoodsInfo.GoodsProperty.class)
        );
        goodsInfo.setSupply(this.supply);
        goodsInfo.setInventory(this.inventory);
        goodsInfo.setCreateTime(this.createTime);
        goodsInfo.setUpdateTime(this.updateTime);

        return goodsInfo;
    }

    /**
     * <h2>将实体对象转成 SimpleGoodsInfo 对象</h2>
     * */
    public SimpleGoodsInfo toSimple() {

        SimpleGoodsInfo goodsInfo = new SimpleGoodsInfo();

        goodsInfo.setId(this.id);
        goodsInfo.setGoodsName(this.goodsName);
        goodsInfo.setGoodsPic(this.goodsPic);
        goodsInfo.setPrice(this.price);
        return goodsInfo;
    }

}
