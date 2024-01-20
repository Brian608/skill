package org.feather.skill.common.utils;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.feather.skill.common.constants.CommonConstant;
import org.feather.skill.common.vo.LoginUserInfo;
import sun.misc.BASE64Decoder;

import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.Calendar;

/**
 * @projectName: skill
 * @package: org.feather.skill.common.utils
 * @className: TokenParseUtil
 * @author: feather
 * @description: jwt token解析工具类
 * @since: 14-Jan-24 2:29 PM
 * @version: 1.0
 */
public class TokenParseUtil {
   /**
    * description:  从 JWT Token 中解析 LoginUserInfo 对象
    * @param token token
    * @return {@link LoginUserInfo}
    * @author: feather
    * @since: 14-Jan-24 2:31 PM
    **/
    public static LoginUserInfo parseUserInfoFromToken(String token) throws Exception {

        if (null == token) {
            return null;
        }

        Jws<Claims> claimsJws = parseToken(token, getPublicKey());
        Claims body = claimsJws.getBody();

        // 如果 Token 已经过期了, 返回 null
        if (body.getExpiration().before(Calendar.getInstance().getTime())) {
            return null;
        }

        // 返回 Token 中保存的用户信息
        return JSON.parseObject(
                body.get(CommonConstant.JWT_USER_INFO_KEY).toString(),
                LoginUserInfo.class
        );

    }
  /**
   * description:  过公钥去解析 JWT Token
   * @param token token
   * @param publicKey publicKey
   * @return {@link Jws<Claims>}
   * @author: feather
   * @since: 14-Jan-24 2:31 PM
   **/
    private static Jws<Claims> parseToken(String token, PublicKey publicKey) {

        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

  /**
   * description:  根据本地存储的公钥获取到PublicKey 对象
   * @return {@link PublicKey}
   * @author: feather
   * @since: 14-Jan-24 2:31 PM
   **/
    private  static PublicKey getPublicKey() throws  Exception{

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(
                new BASE64Decoder().decodeBuffer(CommonConstant.PUBLIC_KEY)
        );
        return KeyFactory.getInstance("RSA").generatePublic(keySpec);
    }

    public static void main(String[] args) {
        System.out.println("加密后的值:"+MD5.create().digestHex("123456"));
    }
}
