package org.feather.skill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.domain.request.AddUserRequest;
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

    /**
     * description: 通过账户和密码查找用户对象
     * @param userNameAndPassword 请求对象
     * @return {@link SkillUser}
     * @author: feather
     * @since: 14-Jan-24 1:49 PM
     **/
    SkillUser findByUserNameAndPassword(UserNameAndPassword userNameAndPassword);

    /**
     * description: 判断用户是否存在  true存在     false不存在
     * @param username 账户
     * @return {@link Boolean}
     * @author: feather
     * @since: 14-Jan-24 2:06 PM
     **/
    Boolean checkUserExist(String username);

}
