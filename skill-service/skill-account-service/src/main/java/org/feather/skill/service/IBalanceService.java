package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.account.BalanceInfo;
import org.feather.skill.entity.Balance;

/**
 * <p>
 * 用户账户余额表 服务类
 * </p>
 *
 * @author feather
 * @since 2024-01-25
 */
public interface IBalanceService extends IService<Balance> {

    /**
     * description:  获取当前用户余额信息
     * @return {@link BalanceInfo}
     * @author: feather
     * @since: 2024-01-27 17:45
     **/
    BalanceInfo  gteCurrentUserBalanceInfo();

    /**
     * description:  扣减用户余额
     * @param balanceInfo balanceInfo 代表想要扣减的余额
     * @return {@link BalanceInfo}
     * @author: feather
     * @since: 2024-01-27 17:45
     **/
    BalanceInfo deductBalance(BalanceInfo balanceInfo);

    /**
     * description: 通过用户id查询余额信息
     * @param userId 用户id
     * @return {@link Balance}
     * @author: feather
     * @since: 2024-01-28 09:07
     **/
    Balance findByUserId(Long userId);

}
