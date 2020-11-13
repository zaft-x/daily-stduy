package org.study.daily.leetcode.search;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/20
 * @Version V1.0
 **/
public class OrderSearch {
    /**顺序查找*/
    public static void main(String[] args) {

        System.out.println(order(new int[]{3, 7, 2, 9, 4}, 9));
    }
    public static int order(int[] a,int key){
        for(int i=0;i<a.length;i++){
            if(key == a[i]){
                return i;
            }
        }
        return -1;
    }
}
