package org.study.daily.leetcode.sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/20
 * @Version V1.0
 **/
public class MergeSort {
    /**归并*/
    public static void main(String[] args) {


        System.out.println(Arrays.toString(merge(new int[]{3, 6, 9}, new int[]{5, 6, 10})));
    }
    /**a,b 为有序  组成一个有序的c*/
    public static int[] merge(int[]a, int[] b){
        int aLength = a.length;
        int bLength = b.length;
        int[] c = new int[aLength+bLength];
        int i=0,j=0,k=0;
        while (i<aLength && j<bLength){
            if(a[i]<b[j]){
                c[k++] = a[i++];
            }else {
                c[k++] = b[j++];
            }
        }
        /**a有剩余*/
        if(i<aLength){
            while (i<aLength){
                c[k++] = a[i++];
            }
        }
        /**b有剩余*/
        if(j<bLength){
            while (j<aLength){
                c[k++] = b[j++];
            }
        }
        return c;
    }

}
