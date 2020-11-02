package pers.crayon.user.utils;

import java.util.Arrays;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/10/10 14:53
 * @since JDK 1.8
 * TODO 3、写一段代码，从含有1000000个元素的随机字符串数组中找出重复过的字符串
 */
public class RioExam {
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

    /**
     * 获得随机字符串
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
}
