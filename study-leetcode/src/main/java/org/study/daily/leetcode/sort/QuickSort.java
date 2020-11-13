package org.study.daily.leetcode.sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/19
 * @Version V1.0
 **/
public class QuickSort {

    public static void main(String[] args) {

        System.out.println(Arrays.toString(quick(new int[]{5, 3, 6, 1, 8}, 0, 4)));
    }

    public static int[] quick(int[] a, int l, int r) {
        if (l >= r)
            return null;
        int i = l;
        int j = r;
        int key = a[l];//选择第一个数为key
        while (i < j) {
            while (i < j && a[j] >= key)//从右向左找第一个小于key的值
                j--;
            if (i < j) {
                a[i] = a[j];
                i++;
            }
            while (i < j && a[i] < key)//从左向右找第一个大于key的值
                i++;
            if (i < j) {
                a[j] = a[i];
                j--;
            }
        }
        a[i] = key;
        quick(a, l, i - 1);//递归调用
        quick(a, i + 1, r);//递归调用
        return a;

    }

    public static int getIndex(int[] arr, int low, int high) {
        int temp = arr[low];
        while (low < high) {
            if (low < high && temp <= arr[high]) {
                high--;
            }
            if (low < high) {
                arr[low] = arr[high];
                low++;
            }
            while (low < high && arr[low] <= temp) {
                low++;
            }
            if (low < high) {
                arr[high] = arr[low];
                high--;
            }

        }
        arr[low] = temp;
        return low;
    }
}
