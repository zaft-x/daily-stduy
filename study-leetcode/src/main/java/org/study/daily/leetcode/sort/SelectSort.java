package org.study.daily.leetcode.sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/19
 * @Version V1.0
 **/
public class SelectSort {
    /**选择排序   每次选选出一个最小的和第一位互换*/
    public static void main(String[] args) {

        System.out.println(Arrays.toString(select(new int[]{5, 3, 6, 8, 2})));
    }

    public static int[] select(int[] arr){
        int temp;
        for(int i=0;i<arr.length; i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]>arr[j]){
                    temp = arr[i];
                    arr[i]=arr[j];
                    arr[j]=temp;
                }
            }
        }
        return arr;
    }
}
