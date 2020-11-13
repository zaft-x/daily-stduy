package org.study.daily.leetcode;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class NumIdenticalPairs {
    /**给你一个整数数组 nums 。
     如果一组数字 (i,j) 满足 nums[i] == nums[j] 且 i < j ，就可以认为这是一组 好数对 。
     返回好数对的数目。*/

    public static void main(String[] args) {
        System.out.println(solution(new int[]{1, 1, 1, 1}));
    }
    public static int solution(int[] nums){
        int n=0;
        for(int i=0; i<nums.length; i++){
            for(int j=i+1; j <nums.length; j++){
                if(nums[i] == nums[j]){
                    n++;
                }
            }
        }
        return n;
    }
}
