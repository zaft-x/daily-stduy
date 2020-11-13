package org.study.daily.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class TwoNum {
    /**给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
     你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。*/

    public static void main(String[] args) {

        System.out.println(Arrays.toString(solution1(new int[]{1, 3, 6, 7, 8}, 7)));

    }

    public static int[] solution1(int[] nums, int target){

        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int a = target - nums[i];
            if(map.containsKey(a)){
                return new int[]{map.get(a), i};
            }
            map.put(nums[i],i);
        }

        return null;
    }

    public static int[] solution(int[] nums, int target){
        for(int i=0; i < nums.length; i++){
            for(int j=nums.length-1;j>=0;j--){
                if(target == nums[i]+nums[j]){
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

}
