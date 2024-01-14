package org.feather.skill;

import cn.hutool.core.codec.Base64;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * @projectName: skill
 * @package: org.feather.skill
 * @className: RSATest
 * @author: feather
 * @description: RSA 非对称加密算法 生成公钥和私钥
 * @since: 14-Jan-24 11:04 AM
 * @version: 1.0
 */
@Slf4j
@SpringBootTest(classes = AuthorityCenterApplication.class)
@RunWith(SpringRunner.class)
public class RSATest {

    @Test
    public  void  generateKeyBytes() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        //生成公钥和私钥对
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //获取公钥和私钥对象
        RSAPublicKey rsaPublicKey =(RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey rsaPrivateKey=   (RSAPrivateKey) keyPair.getPrivate();
        log.info("private key: [{}]", Base64.encode(rsaPrivateKey.getEncoded()));
        log.info("public key: [{}]", Base64.encode(rsaPublicKey.getEncoded()));


    }
}
