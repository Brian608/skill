package org.feather.skill.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @projectName: skill
 * @package: org.feather.skill.constant
 * @className: BrandCategoryEnum
 * @author: feather
 * @description:
 * @since: 2024-01-28 18:07
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum BrandCategoryEnum {
    BRAND_A("20001", "品牌A"),
    BRAND_B("20002", "品牌B"),
    BRAND_C("20003", "品牌C"),
    BRAND_D("20004", "品牌D"),
    BRAND_E("20005", "品牌E"),
            ;

    /** 品牌分类编码 */
    private final String code;

    /** 品牌分类描述信息 */
    private final String description;

    /**
     * <h2>根据 code 获取到 BrandCategoryEnum</h2>
     * */
    public static BrandCategoryEnum of(String code) {

        Objects.requireNonNull(code);

        return Stream.of(values())
                .filter(bean -> bean.code.equals(code))
                .findAny()
                .orElse(null);
    }
}
