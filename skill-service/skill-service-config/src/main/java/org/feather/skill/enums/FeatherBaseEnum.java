package org.feather.skill.enums;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @projectName: skill
 * @package: org.feather.skill.enums
 * @className: BaseEnum
 * @author: feather
 * @description: 枚举基类
 * @since: 2024-01-28 17:54
 * @version: 1.0
 */
public interface FeatherBaseEnum {
    Integer getCode();

    String getMsg();

  /**
   * description:  获取枚举
   * @param code code
   * @param clazz clazz
   * @return {@link E}
   * @author: feather
   * @since: 2024-01-28 17:55
   **/

    static <E extends Enum<E> & FeatherBaseEnum> E getByCode(Integer code, Class<E> clazz) {
        Objects.requireNonNull(code);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((FeatherBaseEnum)e).getCode().equals(code)).findFirst().orElse(null);
    }

  /**
   * description:  值获取枚举
   * @param msg msg
   * @param clazz clazz
   * @return {@link E}
   * @author: feather
   * @since: 2024-01-28 17:55
   **/
    static <E extends Enum<E> & FeatherBaseEnum> E getByMsg(String msg, Class<E> clazz) {
        Objects.requireNonNull(msg);
        EnumSet<E> all = EnumSet.allOf(clazz);
        return all.stream().filter((e) -> ((FeatherBaseEnum)e).getMsg().equals(msg)).findFirst().orElse(null);
    }

   /**
    * description:  枚举转 map
    * @param enumClass enumClass
    * @return {@link Map<Integer,String>}
    * @author: feather
    * @since: 2024-01-28 17:55
    **/
    static <E extends Enum<E> & FeatherBaseEnum> Map<Integer, String> toMap(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(FeatherBaseEnum::getCode, FeatherBaseEnum::getMsg));
    }
}
