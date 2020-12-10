package pers.crayon.works.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/16 16:02
 * @since JDK 1.8
 * <p>
 * MD5加密方法
 * <p>
 * 工具类都是静态字段和函数
 * 通过定义私有构造参数，屏蔽默认公有构造，避免没必要的实例化
 */
public class MD5Util {
    //@Autowired
    //private static SelfEncryptionProperties properties;

    @Value("${encryption.MD5KEY}")
    private static String key;

    /**
     * 使用Spring自带的工具类生成标志
     *
     * @param value
     * @return
     */
    public static String springMD5(String value) {
        String md5Str = DigestUtils.md5DigestAsHex(addTwoArray("value".getBytes(), key.getBytes()));
        return md5Str;
    }

    /**
     * 拼接数组
     *
     * @param btX
     * @param btY
     * @return
     */
    public static byte[] addTwoArray(byte[] btX, byte[] btY) {
        //定义目标数组  目标数组应该等于将要拼接的两个数组的总长度
        byte[] btZ = new byte[btX.length + btY.length];

        System.arraycopy(btX, 0, btZ, 0, btX.length);
        System.arraycopy(btY, 0, btZ, btX.length, btY.length);
        return btZ;
    }

    /**
     * 使用MessageDigest 生成MD5
     *
     * @param str
     * @return
     */
    public static String getMD5Str(String str) {
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

    /**
     * @param value
     * @return
     */
    public static String stringMD5(String value) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            byte[] inputByteArray = value.getBytes();
            messageDigest.update(inputByteArray);
            byte[] resultByteArray = messageDigest.digest();
            return byteArrayToHex(resultByteArray);
        } catch (NoSuchAlgorithmException e) {
            return null;
        }
    }

    /**
     * @param byteArray
     * @return
     */
    public static String byteArrayToHex(byte[] byteArray) {
        char[] hexDigits = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] resultCharArray = new char[byteArray.length * 2];
        int index = 0;
        for (byte b : byteArray) {
            resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
            resultCharArray[index++] = hexDigits[b & 0xf];
        }
        return new String(resultCharArray);
    }

    /**
     * @param originStr
     * @return
     */
    public static String enCodeByMD5(String originStr) {
        String result = null;
        char hexDigits[] = {
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (originStr != null) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] source = originStr.getBytes("utf-8");
                md.update(source);
                byte[] tmp = md.digest();
                char[] str = new char[32];
                for (int i = 0, j = 0; i < 16; i++) {
                    byte b = tmp[i];
                    str[j++] = hexDigits[b >>> 4 & 0xf];
                    str[j++] = hexDigits[b & 0xf];
                }
                result = new String(str);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}