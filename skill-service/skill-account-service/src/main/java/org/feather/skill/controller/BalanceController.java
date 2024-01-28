package org.feather.skill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.feather.skill.account.BalanceInfo;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.service.IBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: BalanceController
 * @author: feather
 * @description: 用户余额服务
 * @since: 2024-01-28 09:42
 * @version: 1.0
 */
@Api(tags = "用户余额服务")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/balance")
public class BalanceController {

    private final IBalanceService balanceService;

    @ApiOperation(value = "当前用户", notes = "获取当前用户余额信息", httpMethod = "GET")
    @GetMapping("/current-balance")
    public FeatherResponse<BalanceInfo> getCurrentUserBalanceInfo() {
        return FeatherResponse.success(balanceService.gteCurrentUserBalanceInfo());
    }

    @ApiOperation(value = "扣减", notes = "扣减用于余额", httpMethod = "PUT")
    @PutMapping("/deduct-balance")
    public FeatherResponse<BalanceInfo> deductBalance(@RequestBody BalanceInfo balanceInfo) {
        return FeatherResponse.success(balanceService.deductBalance(balanceInfo));
    }
}
