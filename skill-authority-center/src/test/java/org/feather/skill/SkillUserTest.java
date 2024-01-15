package org.feather.skill;

import cn.hutool.crypto.digest.MD5;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.domain.request.AddUserRequest;
import org.feather.skill.service.ISkillUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: SkillUserTest
 * @author: feather
 * @description:
 * @since: 13-Jan-24 7:15 PM
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class SkillUserTest {


    @Autowired
    private   ISkillUserService skillUserService;

    @Test
    public  void  contextLoad(){

    }

    @Test
    public void  saveUser(){
        AddUserRequest addUserRequest =new AddUserRequest();
        addUserRequest.setUsername("feather");
        addUserRequest.setPassword(MD5.create().digestHex("123456"));
        log.info("save user result :[{}]",skillUserService.saveUser(addUserRequest)) ;
    }


}
