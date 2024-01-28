package org.feather.skill.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;
import java.util.stream.Stream;

/**
 * @projectName: skill
 * @package: org.feather.skill.constant
 * @className: GoodsStatusEnum
 * @author: feather
 * @description:
 * @since: 2024-01-28 17:52
 * @version: 1.0
 */
@Getter
@AllArgsConstructor
public enum GoodsStatusEnum {
    ONLINE(101, "上线"),
    OFFLINE(102, "下线"),
    STOCK_OUT(103, "缺货"),
    ;

    /** 状态码 */
    private final Integer status;

    /** 状态描述 */
    private final String description;

    /**
     * <h2>根据 code 获取到 GoodsStatus</h2>
     * */
    public static GoodsStatusEnum of(Integer status) {

        Objects.requireNonNull(status);

        return Stream.of(values())
                .filter(bean -> bean.status.equals(status))
                .findAny()
                .orElseThrow(
                        () -> new IllegalArgumentException(status + " not exists")
                );
    }
}
