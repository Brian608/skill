package org.feather.skill.utils;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.feather.skill.exception.BusinessException;
import org.feather.skill.common.enums.BaseErrorCodeEnum;
import org.feather.skill.common.enums.CommonErrorCodeEnum;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.utils
 * @className: ParamCheckUtil
 * @author: feather
 * @description:
 * @since: 13-Jan-24 9:34 PM
 * @version: 1.0
 */
public class ParamCheckUtil {
    public static void checkObjectNonNull(Object o) throws BusinessException {
        if (Objects.isNull(o)) {
            throw new BusinessException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkObjectNonNull(Object o, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (Objects.isNull(o)) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkObjectNull(Object o, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (Objects.nonNull(o)) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }


    public static void checkStringNonEmpty(String s) throws BusinessException {
        if (StringUtils.isBlank(s)) {
            throw new BusinessException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkStringNonEmpty(String s, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (StringUtils.isBlank(s)) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntAllowableValues(Integer i, Set<Integer> allowableValues, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (Objects.nonNull(i) && !allowableValues.contains(i)) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntMin(Integer i, int min, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BusinessException {
        if (Objects.isNull(i) || i<min) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkLongMin(Long i, Long min, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (Objects.isNull(i) || i<min) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkCollectionNonEmpty(Collection<?> collection) throws BusinessException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(CommonErrorCodeEnum.SERVER_ILLEGAL_ARGUMENT_ERROR);
        }
    }

    public static void checkCollectionNonEmpty(Collection<?> collection, BaseErrorCodeEnum baseErrorCodeEnum, Object... arguments) throws BusinessException {
        if (CollectionUtils.isEmpty(collection)) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkIntSetAllowableValues(Set<Integer> intSet, Set<Integer> allowableValues, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BusinessException {
        if (CollectionUtils.isNotEmpty(intSet) && !diffSet(intSet,allowableValues).isEmpty()) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    public static void checkSetMaxSize(Set<?> setParam, Integer maxSize, BaseErrorCodeEnum baseErrorCodeEnum,Object... arguments) throws BusinessException {
        if (CollectionUtils.isNotEmpty(setParam) && setParam.size() > maxSize) {
            throw new BusinessException(baseErrorCodeEnum.getErrorCode(), baseErrorCodeEnum.getErrorMsg(),arguments);
        }
    }

    /**
     * description:  求set 差集合
     * @param set1 set1
     * @param set2 set2
     * @return {@link Set<Integer>}
     * @author: feather
     * @since: 13-Jan-24 9:35 PM
     **/
    private static Set<Integer> diffSet(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> result = new HashSet<>();
        result.addAll(set1);
        result.removeAll(set2);
        return result;
    }
}
