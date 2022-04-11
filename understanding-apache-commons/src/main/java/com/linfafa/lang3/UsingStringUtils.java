package com.linfafa.lang3;

import org.apache.commons.lang3.StringUtils;

/**
 * @author linmin
 * @date 2021/7/16
 */
public class UsingStringUtils {
    public static void main(String[] args) {
        //缩短到某长度，用...结尾，str.substring(0, maxWidth - 3)
        StringUtils.abbreviate("abcdefg", 6);   // "abc..."

        //首字母大小写转换
        StringUtils.capitalize("cat");  // "Cat"
        StringUtils.uncapitalize("Cat");// "cat"

        //字符串扩充至指定大小且居中(若扩充大小少于原字符大小，则返回原字符；若扩充大小为负数则计为0)
        StringUtils.center("abcd",2);   // "abcd"
        StringUtils.center("ab",-1);    // "ab"
        StringUtils.center("ab",4);     // " ab "
        StringUtils.center("a",4,"yz"); // "yayz"
        StringUtils.center("abc",7,""); // "  abc  "

        //去除字符串中的"\n","\r"或"\r\n"
        StringUtils.chomp("abc\r\n");   //"abc"

        //判断一字符串是否包含另一个字符串
        StringUtils.contains("abc","z");    //false
        StringUtils.containsIgnoreCase("abc","A");  //true

        //统计一字符串在另一个字符串中出现的次数
        StringUtils.countMatches("abba","a");   //2

        //清空字符串中的所有空格
        StringUtils.deleteWhitespace("     ab  c       ");  // "abc"

        //
        StringUtils.difference("abcde","absys");

    }
}
