package org.study.daily.leetcode.sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/19
 * @Version V1.0
 **/
public class BubbleSort {
    /**冒泡排序*/

    public static void main(String[] args) {
        System.out.println(Arrays.toString(bubble1(new int[]{1, 4, 3, 9, 5, 7})));
        System.out.println(Arrays.toString(bubble(new int[]{1, 4, 3, 9, 5, 7})));
    }

    public static int[] bubble(int [] arr){
        int temp;
        for(int i=0; i<arr.length; i++){
            for(int j=0;j<arr.length-1;j++){
                if(arr[j]>arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
        return arr;
    }

    /**添加一个标志位，减少无用遍历*/
    public static int[] bubble1(int [] arr){
        int temp;
        boolean flag;
        for(int i=0; i<arr.length-1; i++){
             flag = false;
            for(int j=arr.length-1;j>i;j--){
                if(arr[j]<arr[j-1]){
                    temp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = temp;
                    flag = true;
                }
            }
            if(!flag){
                break;
            }
        }

        return arr;
    }

}
