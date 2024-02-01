package org.feather.skill.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.feather.skill.goods.SimpleGoodsInfo;

import java.util.List;

/**
 * @projectName: skill
 * @package: org.feather.skill.vo
 * @className: PageSimpleGoodsInfo
 * @author: feather
 * @description:
 * @since: 2024-02-01 21:26
 * @version: 1.0
 */
@ApiModel(description = "分页商品信息对象")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageSimpleGoodsInfo {

    @ApiModelProperty(value = "分页简单商品信息")
    private List<SimpleGoodsInfo> simpleGoodsInfos;

    @ApiModelProperty(value = "是否有更多的商品(分页)")
    private Boolean hasMore;
}
