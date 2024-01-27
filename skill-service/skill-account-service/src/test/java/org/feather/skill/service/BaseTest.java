package org.feather.skill.service;

import org.feather.skill.AccountApplication;
import org.feather.skill.common.vo.LoginUserInfo;
import org.feather.skill.filter.AccessContext;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: skill
 * @package: org.feather.skill.service
 * @className: BaseTest
 * @author: feather
 * @description:  测试用例基类, 填充登录用户信息
 * @since: 2024-01-27 18:29
 * @version: 1.0
 */
@SpringBootTest(classes = AccountApplication.class)
@RunWith(SpringRunner.class)
public abstract class BaseTest {
    protected final LoginUserInfo loginUserInfo = new LoginUserInfo(
            16L, "feather"
    );

    @Before
    public void init() {
        AccessContext.setLoginUserInfo(loginUserInfo);
    }

    @After
    public void destroy() {
        AccessContext.clearLoginUserInfo();
    }
}
