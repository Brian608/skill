package org.feather.skill.service.impl;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.feather.skill.common.constants.CommonConstant;
import org.feather.skill.common.utils.RandomUtil;
import org.feather.skill.common.vo.LoginUserInfo;
import org.feather.skill.common.vo.UserNameAndPassword;
import org.feather.skill.constants.AuthorityConstant;
import org.feather.skill.entity.SkillUser;
import org.feather.skill.service.IJWTService;
import org.feather.skill.service.ISkillUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

/**
 * @projectName: skill
 * @package: org.feather.skill.service.impl
 * @className: JWTServiceImpl
 * @author: feather
 * @description:
 * @since: 14-Jan-24 11:36 AM
 * @version: 1.0
 */
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class JWTServiceImpl implements IJWTService {


    private  final ISkillUserService skillUserService;

    @Override
    public String generateToken(String username, String password) throws Exception {
        return generateToken(username, password, 0);
    }

    @Override
    public String generateToken(String username, String password, int expire) throws Exception {
        // 首先需要验证用户是否能够通过授权校验, 即输入的用户名和密码能否匹配数据表记录
        SkillUser user = skillUserService.findByUserNameAndPassword(new UserNameAndPassword(username,password));
        if (Objects.isNull(user)){
            return null;
        }
        // Token 中塞入对象, 即 JWT 中存储的信息, 后端拿到这些信息就可以知道是哪个用户在操作
        LoginUserInfo loginUserInfo=new LoginUserInfo(user.getId(),user.getUsername());

        if (expire<=0){
            expire = AuthorityConstant.DEFAULT_EXPIRE_DAY;
        }
        //计算超时
        ZonedDateTime zdt = LocalDate.now().plusDays(expire)
                .atStartOfDay(ZoneId.systemDefault());
        Date expireDate = Date.from(zdt.toInstant());

        return Jwts.builder()
                // jwt payload --> KV
                .claim(CommonConstant.JWT_USER_INFO_KEY, JSON.toJSONString(loginUserInfo))
                // jwt id
                .setId(UUID.randomUUID().toString())
                // jwt 过期时间
                .setExpiration(expireDate)
                // jwt 签名 --> 加密
                .signWith(getPrivateKey(), SignatureAlgorithm.RS256)
                .compact();
    }

    @Override
    public String registerUserAndGenerateToken(UserNameAndPassword usernameAndPassword) throws Exception {
        String username = usernameAndPassword.getUsername();
        String password = usernameAndPassword.getPassword();
        //先去检验用户名是否存在，存在不能重复注册
        Boolean userExist = skillUserService.checkUserExist(username);
        if (userExist){
            log.error("username is registered: [{}]", username);
            return null;
        }
        SkillUser skillUser=new SkillUser();
        String salt = RandomUtil.genRandomNumberStr(6);
        skillUser.setUsername(username);
        skillUser.setSalt(salt);
        String digestHexPassword = MD5.create().digestHex(password.concat(salt));
        skillUser.setPassword(digestHexPassword);
        boolean save = skillUserService.save(skillUser);
        log.info("register user result: [{}], [{}]",save,username );
        // 生成 token 并返回
        return generateToken(username, password);
    }


    /**
     * description:  根据本地存储的私钥获取到 PrivateKey 对象
     * @return {@link PrivateKey}
     * @author: feather
     * @since: 14-Jan-24 11:43 AM
     **/
    private PrivateKey getPrivateKey() throws Exception {

        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(AuthorityConstant.PRIVATE_KEY));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(priPKCS8);
    }
}
