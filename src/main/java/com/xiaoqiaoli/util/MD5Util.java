package com.xiaoqiaoli.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by hanlei6 on 2016/8/6.
 */
public class MD5Util {
    /**
     * @param arg0 传入内容
     * @param arg1 盐
     * @return
     */
    public static String encode(String arg0, String arg1) {
        // 计算消息摘要
        String result = "";
        try {
            MessageDigest m = MessageDigest.getInstance("MD5");
            m.update(arg1.getBytes("UTF8"));
            m.update(arg0.getBytes("UTF8"));
            byte s[] = m.digest();
            for (int i = 0; i < s.length; i++) {
                result += Integer.toHexString((0x000000ff & s[i]) | 0xffffff00)
                        .substring(6);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return result;
    }
}
