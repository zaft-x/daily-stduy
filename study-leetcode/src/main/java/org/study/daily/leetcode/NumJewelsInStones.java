package org.study.daily.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author x.zaft
 * @Date 2020/11/13
 * @Version v1
 **/
public class NumJewelsInStones {

    /**给定字符串J 代表石头中宝石的类型，和字符串 S代表你拥有的石头。 S 中每个字符代表了一种你拥有的石头的类型，你想知道你拥有的石头中有多少是宝石。
    J 中的字母不重复，J 和 S中的所有字符都是字母。字母区分大小写，因此"a"和"A"是不同类型的石头。
    示例 1:
    输入: J = "aA", S = "aAAbbbb"
    输出: 3
    示例 2:
    输入: J = "z", S = "ZZ"
    输出: 0
    注意:
    S 和 J 最多含有50个字母。
             J 中的字符不重复。*/

    public static void main(String[] args) {
        System.out.println(numJewelsInStones("aA", "aAABBB"));
        System.out.println(numJewelsInStones("z", "ZZ"));
    }
    public static int numJewelsInStones(String J, String S) {
        int sum = 0;

        List<Character> jewelList = new ArrayList<>();
        for (char c : J.toCharArray()) {
            jewelList.add(c);
        }
        for (char c : S.toCharArray()) {
            if(jewelList.contains(c)){
                sum++;
            }
        }
        return sum;
    }

}
