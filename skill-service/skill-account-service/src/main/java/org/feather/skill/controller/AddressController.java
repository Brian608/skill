package org.feather.skill.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.feather.skill.account.AddressInfo;
import org.feather.skill.common.TableId;
import org.feather.skill.common.vo.FeatherResponse;
import org.feather.skill.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @projectName: skill
 * @package: org.feather.skill.controller
 * @className: AddressController
 * @author: feather
 * @description: 用户地址服务
 * @since: 2024-01-28 09:31
 * @version: 1.0
 */
@Api(tags = "用户地址")
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AddressController {

    private  final IAddressService addressService;


    @ApiOperation(value = "创建",notes = "创建用户地址信息",httpMethod = "POST")
    @PostMapping("/create-address")
    public FeatherResponse<Boolean> createAddressInfo(@RequestBody AddressInfo addressInfo){
        return FeatherResponse.success(addressService.createAddressInfo(addressInfo));
    }

    @ApiOperation(value = "当前用户", notes = "获取当前登录用户地址信息", httpMethod = "GET")
    @GetMapping("/current-address")
    public FeatherResponse<AddressInfo> getCurrentAddressInfo() {
        return FeatherResponse.success(addressService.getCurrentAddressInfo());
    }

    @ApiOperation(value = "获取用户地址信息",
            notes = "通过 id 获取用户地址信息, id 是 Address 表的主键",
            httpMethod = "GET")
    @GetMapping("/address-info")
    public FeatherResponse<AddressInfo> getAddressInfoById(@RequestParam Long id) {
        return FeatherResponse.success(addressService.getAddressInfoById(id));
    }

    @ApiOperation(value = "获取用户地址信息",
            notes = "通过 TableId 获取用户地址信息", httpMethod = "POST")
    @PostMapping("/address-info-by-table-id")
    public FeatherResponse<AddressInfo> getAddressInfoByTablesId(@RequestBody TableId tableId) {
        return FeatherResponse.success(addressService.getAddressInfoByTableId(tableId));
    }
}
