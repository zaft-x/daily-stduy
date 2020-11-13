package org.study.daily.leetcode.search;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/20
 * @Version V1.0
 **/
public class BinarySearch {
    /**二分法*/
    public static void main(String[] args) {
        System.out.println(binary(new int[]{1, 2, 3, 5, 6, 8, 9}, 9));
    }

    public static int binary(int[] a, int key){
        int low = 0;
        int high = a.length-1;
        int mid;
        while (low<high){
            mid = (low+high)/2;
            if(a[mid] == key){
                return mid;
            }else if(a[mid] < key){
                low = mid+1;
            }else if(a[mid] > key){
                high = mid-1;
            }
        }
        return -1;
    }
}
