package org.feather.skill.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.domain.AddUserRequest;
import org.feather.skill.entity.SkillUser;
import org.feather.skill.mapper.SkillUserMapper;
import org.feather.skill.service.ISkillUserService;
import org.feather.skill.common.utils.RandomUtil;
import org.feather.skill.utils.ParamCheckUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.impl
 * @className: SkillUserServiceImpl
 * @author: feather
 * @description:
 * @since: 13-Jan-24 7:04 PM
 * @version: 1.0
 */
@Slf4j
@Service
public class SkillUserServiceImpl extends ServiceImpl<SkillUserMapper, SkillUser> implements ISkillUserService {

    @Override
    public Boolean saveUser(AddUserRequest request) {
        ParamCheckUtil.checkObjectNonNull(request);
        SkillUser skillUser=new SkillUser();
        BeanUtils.copyProperties(request,skillUser);
        String salt = RandomUtil.genRandomNumberStr(6);
        skillUser.setSalt(salt);
        skillUser.setPassword(MD5.create().digestHex(request.getPassword().concat(salt)));
        return this.save(skillUser);
    }
}
