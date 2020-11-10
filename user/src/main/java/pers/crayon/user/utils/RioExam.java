package pers.crayon.user.utils;

import java.util.*;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/10 14:53
 * @since JDK 1.8
 * TODO 3、写一段代码，从含有1000000个元素的随机字符串数组中找出重复过的字符串
 */
public class RioExam {
    public static String pattern = "^.*(?=.{6,16})(?=.*\\d)(?=.*[A-Z]{2,})(?=.*[a-z]{2,})(?=.*[!@#$%^&*?\\(\\)]).*$";

    public static void main(String[] args) {
        getRepetition(100);
    }

    /**
     * 获得随机字符串数组的重复数据；
     *
     * @param length 重复字符串数组的长度
     */
    private static void getRepetition(int length) {
        String[] strArr = new String[length];
        for (int i = 0; i < strArr.length; i++) {
            strArr[i] = getRandomStr(2);
        }
        //输出随机字符串数组
        System.out.println(Arrays.toString(strArr));
        //使用选择排序，让每一个数组元素进行比较
        for (int i = 0; i < strArr.length - 1; i++) {
            for (int j = i + 1; j < strArr.length; j++) {
                if (strArr[i].equals(strArr[j])) {
                    System.out.print(strArr[i]);
                }
            }
        }
    }

    public void countDupChars(String str) {
        //创建一个HashMap对象
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        //将字符串转换为char数组
        char[] chars = str.toCharArray();
        /* logic: 将每个字符插入到map中，map中的每个元素是[key,value]的组合，
         * key记录字符，value记录这个字符出现的次数。
         * 如果map中已经存在ch，则修改该字符出现的次数（原来次数+1）。
         * 如果map中还没有ch,则将ch插入到map中，key为ch的值，value为1*/
        for (Character ch : chars) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }

        //获得map的键集
        Set<Character> keys = map.keySet();

        /* 对出现超过1次的字符，显示其个数.
         */
        for (Character ch : keys) {
            int n = map.get(ch);//从map中获取ch的个数
            if (n > 1) {
                System.out.println("Char " + ch + " " + n);
            }
        }
    }

    /**
     * 获得随机字符串
     * 使用StringBuilder拼接字符串
     *
     * @param length 随机字符串的长度
     * @return
     */
    private static String getRandomStr(int length) {
        StringBuilder sb = new StringBuilder();
        String result = "";
        for (int i = '0'; i < '9'; i++) {
            sb.append((char) i);
        }
        for (int i = 'a'; i < 'z'; i++) {
            sb.append((char) i);
        }
        for (int i = 'A'; i < 'Z'; i++) {
            sb.append((char) i);
        }
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * sb.length());
            result += sb.charAt(index);
        }
        return result;
    }

    // 获取随机字符串
    public static String getRandomString(int length) {
        //含有字符和数字的字符串
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789~！@#￥%……&*()_+:?/><}{";
        Random random = new Random();//随机类初始化
        StringBuffer sb = new StringBuffer();//StringBuffer类生成，为了拼接字符串

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(62);// [0,62)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }
}
