package org.study.daily.leetcode;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class Reverse {


    /**数字反序*/
    public static void main(String[] args) {
        System.out.println(reverse(-2147483648));
    }

    public static int reverse(int x) {
        char[] chars = String.valueOf(x).toCharArray();
        int result = 0;
        while(x !=0){
            int remain = x%10;
            x = x/10;
            if(result > Integer.MAX_VALUE/10){
                return 0;
            }
            if(result < Integer.MIN_VALUE/10){
                return 0;
            }
            result = result*10 + remain;

        }
        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            result = 0;
        }
        return result;
    }
}
