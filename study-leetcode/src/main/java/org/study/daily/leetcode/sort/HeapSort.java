package org.study.daily.leetcode.sort;

import java.util.Arrays;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/20
 * @Version V1.0
 **/
public class HeapSort {
    /**堆排序*/
    public static void main(String[] args) {

        int[] a = new int[]{3,2,6,8,1};
//        MakeMinHeap(arr, 5);
int n=5;
        int temp = 0;
        MakeMinHeap(a,n);

        for(int i=n-1;i>0;i--){
            temp = a[0];
            a[0] = a[i];
            a[i] = temp;
            MinHeapFixdown(a,0,i);
        }

        System.out.println(Arrays.toString(a));
    }

    //构建最小堆
    public static void MakeMinHeap(int a[], int n){
        for(int i=(n-1)/2 ; i>=0 ; i--){
            MinHeapFixdown(a,i,n);
        }
    }
    //从i节点开始调整,n为节点总数 从0开始计算 i节点的子节点为 2*i+1, 2*i+2
    public static void MinHeapFixdown(int a[],int i,int n){

        int j = 2*i+1; //子节点
        int temp = 0;

        while(j<n){
            //在左右子节点中寻找最小的
            if(j+1<n && a[j+1]<a[j]){
                j++;
            }

            if(a[i] <= a[j])
                break;
            //较大节点下移
            temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            i = j;
            j = 2*i+1;
        }
    }
}
