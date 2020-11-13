package org.study.daily.leetcode;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class Palindrome {
    /**回文  121 是  -121不是  */

    public static void main(String[] args) {
        System.out.println(palindrome1(1221));
    }

    public static boolean palindrome1(int x){
        if(x == Reverse.reverse(x)){
            return true;
        }
        return false;
    }

    public static boolean palindrome(int x){
        char[] chars = String.valueOf(x).toCharArray();
        int length = chars.length;
        int mid = length/2;
        for(int i=0; i< mid; i++){
            if(chars[i] != chars[length - 1 - i]){
                return false;
            }
        }
        return true;
    }

}
