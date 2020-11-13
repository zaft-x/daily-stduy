package org.study.daily.leetcode;

/**
 * @Description: TODO
 * @Author x.zaft
 * @Date 2020/9/18
 * @Version V1.0
 **/
public class ReverseLeftWords {

    public static void main(String[] args) {
        System.out.println(solution("abcdefg", 2));
        System.out.println(solution("lrloseumgh", 6));

        int[] formArray={101,102,103,104,105,106};
        int[] toArray={201,202,203,204,205,206,207};
        System.arraycopy(formArray, 2, toArray, 3, 2);
        for(int i=0;i<toArray.length;i++){
            System.out.println(i+":"+toArray[i]);
        }

    }

    public static String solution1(String s, int n){
        char[] chars = s.toCharArray();


        String pre = s.substring(0, n );
        String last = s.substring(n, s.length());
        return last+pre;
    }

    public static String solution(String s, int n){
        String pre = s.substring(0, n );
        String last = s.substring(n, s.length());
        return last+pre;
    }
}
