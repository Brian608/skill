package org.feather.skill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.feather.skill.account.BalanceInfo;
import org.feather.skill.entity.Balance;
import org.feather.skill.mapper.BalanceMapper;
import org.feather.skill.service.IBalanceService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户账户余额表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-25
 */
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IBalanceService {

    @Override
    public BalanceInfo gteCurrentUserBalanceInfo() {
        return null;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {
        return null;
    }
}
