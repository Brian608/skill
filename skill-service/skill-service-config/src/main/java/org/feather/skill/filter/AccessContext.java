package org.feather.skill.filter;

import org.feather.skill.common.vo.LoginUserInfo;

/**
 * @projectName: skill
 * @package: org.feather.skill.filter
 * @className: AccessContext
 * @author: feather
 * @description: 使用ThreadLocal 去单独存储每一个线程携带的 loginInfo 信息
 * 要及时清理保存到ThreadLocal中的用户信息
 * 1：保证没有资源泄露
 * 2：保证线程在重用时，不会出现数据混乱
 * @since: 2024-01-23 19:21
 * @version: 1.0
 */
public class AccessContext {
    private static  final ThreadLocal<LoginUserInfo>  loginUserInfo=new ThreadLocal<>();


    public static LoginUserInfo getLoginUserInfo() {
        return loginUserInfo.get();
    }

    public static void setLoginUserInfo(LoginUserInfo loginUserInfo_) {
        loginUserInfo.set(loginUserInfo_);
    }

    public static void clearLoginUserInfo() {
        loginUserInfo.remove();
    }

}
