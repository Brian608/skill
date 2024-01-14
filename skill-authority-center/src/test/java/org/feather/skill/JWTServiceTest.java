package org.feather.skill;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.utils.TokenParseUtil;
import org.feather.skill.common.vo.LoginUserInfo;
import org.feather.skill.service.IJWTService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: JWTServiceTest
 * @author: feather
 * @description:  jwt 相关服务测试
 * @since: 14-Jan-24 4:26 PM
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class JWTServiceTest {
    @Autowired
    private IJWTService ijwtService;

    @Test
    public void testGenerateAndParseToken() throws Exception {
        String jwtToken = ijwtService.generateToken("feather", "e10adc3949ba59abbe56e057f20f883e");
        log.info("jwt token is [{}]",jwtToken);

        LoginUserInfo loginUserInfo = TokenParseUtil.parseUserInfoFromToken(jwtToken);
        log.info("parse token :[{}]", JSON.toJSON(loginUserInfo));
    }
}
