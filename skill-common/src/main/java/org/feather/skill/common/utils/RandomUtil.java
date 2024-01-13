package org.feather.skill.common.utils;

import java.util.Random;

/**
 * @projectName: skill
 * @package: org.feather.skill.skill.common.utils
 * @className: RandomUtil
 * @author: feather
 * @description:
 * @since: 13-Jan-24 7:25 PM
 * @version: 1.0
 */
public class RandomUtil {
  private  static final Random random = new Random();
   /**
    * description:  生成指定长度的随机数
    * @param length 长度
    * @return {@link String}
    * @author: feather
    * @since: 13-Jan-24 7:25 PM
    **/
    public static String genRandomNumber(int length) {

        String sources = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < length; j++) {
            sb.append(sources.charAt(random.nextInt(9)));
        }
        return sb.toString();
    }


    private static final String ALL_CHARS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz!@#$%^&*()";

  /**
   * description:  生成指定长度的随机字符串
   * @param length 长度
   * @return {@link String}
   * @author: feather
   * @since: 13-Jan-24 7:26 PM
   **/
    public static String genRandomNumberStr(int length) {
        Random random = new Random();
        StringBuilder saltString = new StringBuilder(length);
        for (int i = 1; i <= length; ++i) {
            saltString.append(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        return saltString.toString();
    }
}
