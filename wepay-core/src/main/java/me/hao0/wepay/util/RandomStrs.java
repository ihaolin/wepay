package me.hao0.wepay.util;

import java.util.Random;

/**
 * 随机字符串工具
 * Author: haolin
 * Email: haolin.h0@gmail.com
 * Date: 26/11/15
 * @since 1.0.0
 */
public final class RandomStrs {

    private static final String seed = "abcdefghijklmnopqrstuvwxyz0123456789";

    private static final Random random = new Random();

    /**
     * 生成随机字符串
     * @param length 长度
     * @return 长度为length的字符串
     */
    public static String generate(Integer length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(seed.length());
            sb.append(seed.charAt(number));
        }
        return sb.toString();
    }
}
