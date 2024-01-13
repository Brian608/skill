package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.domain.AddUserRequest;
import org.feather.skill.entity.SkillUser;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: ISkillUserService
 * @author: feather
 * @description:
 * @since: 13-Jan-24 7:04 PM
 * @version: 1.0
 */
public interface ISkillUserService extends IService<SkillUser> {

    /**
     * description:  新增用户
     * @param request 新增用户对象
     * @return {@link Boolean}
     * @author: feather
     * @since: 13-Jan-24 7:23 PM
     **/
    Boolean saveUser(AddUserRequest request);

}
