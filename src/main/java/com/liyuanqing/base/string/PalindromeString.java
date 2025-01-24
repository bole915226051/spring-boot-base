package com.liyuanqing.base.string;

/**
 * 判断回文的几种方式
 */
public class PalindromeString {
    public static final String palindrome = "abccba";


    public static void main(String[] args) {
        // 通过字符串反转进行判断
        method1();
        // 比较开始和尾部是否相同，然后逐步获取下一个字符比较
        method2();
        // 通过栈的特性。（先进后出）
        // 1、将字符串对半切压入栈中，如果是奇数则丢掉栈外字符串的首个字符。
        // 2、然后与后半部分进行比较。

    }

    private static void method1() {
        // 通过字符串反转进行判断
        String reverse = new StringBuffer(palindrome).reverse().toString();
        System.out.println(reverse.equals(palindrome));
    }

    private static void method2() {
        int left = 0;
        int right = palindrome.length() - 1;
        while (left < right) {
            if (palindrome.charAt(left) != palindrome.charAt(right)) {
                System.out.println(false);
                return;
            }
            left++;
            right--;
        }
        System.out.println(true);
    }
}
