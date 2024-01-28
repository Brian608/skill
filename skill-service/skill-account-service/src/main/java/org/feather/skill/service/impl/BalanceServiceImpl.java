package org.feather.skill.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.account.BalanceInfo;
import org.feather.skill.common.vo.LoginUserInfo;
import org.feather.skill.entity.Balance;
import org.feather.skill.enums.AccountErrorCodeEnums;
import org.feather.skill.exception.BusinessException;
import org.feather.skill.filter.AccessContext;
import org.feather.skill.mapper.BalanceMapper;
import org.feather.skill.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 用户账户余额表 服务实现类
 * </p>
 *
 * @author feather
 * @since 2024-01-25
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Transactional(rollbackFor = Exception.class)
@Slf4j
@Service
public class BalanceServiceImpl extends ServiceImpl<BalanceMapper, Balance> implements IBalanceService {

    @Override
    public BalanceInfo gteCurrentUserBalanceInfo() {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        Long userInfoId = loginUserInfo.getId();
        BalanceInfo balanceInfo=new BalanceInfo(userInfoId,0L);
        Balance balance = this.findByUserId(userInfoId);
        if (null!=balance){
            balanceInfo.setBalance(balance.getBalance());
        }else {
            //如果还没有用户余额记录，这里创建出来，余额设置为0
            Balance newBalance=new Balance();
            newBalance.setUserId(userInfoId);
            newBalance.setBalance(0L);
            this.save(newBalance);
            log.info("init user balance ");
        }

        return balanceInfo;
    }

    @Override
    public BalanceInfo deductBalance(BalanceInfo balanceInfo) {
        LoginUserInfo loginUserInfo = AccessContext.getLoginUserInfo();
        Long userInfoId = loginUserInfo.getId();
        //扣减用户余额的一个基本原则，扣减余额<=当前用户余额
        Balance balance = this.findByUserId(userInfoId);
        if (null==balance){
            throw  new BusinessException(AccountErrorCodeEnums.BALANCE_IS_NOT_ENOUGH);
        }
        Long sourceBalance = balance.getBalance();
        Long infoBalance = balanceInfo.getBalance();
        if (sourceBalance-infoBalance<0){
            throw  new BusinessException(AccountErrorCodeEnums.BALANCE_IS_NOT_ENOUGH);
        }
        balance.setBalance(sourceBalance-infoBalance);
        boolean update = this.updateById(balance);
        log.info("deduct balance result : [{}],原来余额[{}],扣减余额[{}]",update,sourceBalance,infoBalance);
        return new BalanceInfo(infoBalance,balance.getBalance());
    }

    @Override
    public Balance findByUserId(Long userId) {
        return this.getOne(new LambdaQueryWrapper<Balance>().eq(Balance::getUserId,userId));
    }
}
