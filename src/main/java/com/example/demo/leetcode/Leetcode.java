package com.example.demo.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Leetcode {
    /**
     * 13. 罗马数字转整数
     */
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);
        char[] chars = s.toCharArray();
        int old = 0;
        int sum = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            int newint = romanMap.get(chars[i]);
            if (newint < old) {
                sum -= newint;
            } else {
                sum += newint;
            }
            old = newint;
        }
        return sum;
    }

    /**
     * 14:编写一个函数来查找字符串数组中的最长公共前缀。
     */
    public String longestCommonPrefix(String[] strs) {
        String result = "";
        if (strs == null)
            return result;
        int len = strs.length;
        if (len == 0) {
            return result;
        }
        if (len == 0) {
            return strs[0];
        }
        int minlength = strs[0].length();
        int minIndex = 0;
        for (int i = 1; i < len; i++) {
            if (minlength > strs[i].length()) {
                minlength = strs[i].length();
                minIndex = i;
            }
        }
        for (int j = minlength; j >= 0; j--) {
            result = strs[minIndex].substring(0, j);
            int x = 0;
            for (String s : strs) {
                if (x == minIndex) {
                    x++;
                    continue;
                }
                if (!s.substring(0, j).equals(result)) {
                    break;
                }
                x++;
            }
            if (x == len) {
                return result;
            }

        }
        return result;
    }

    /**
     * 20:给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     */
    public boolean isValid(String s) {
        if ("".equals(s)) {
            return true;
        }
        if (s == null) {
            return false;
        }
        Map<String, String> maps = new HashMap<>();
        maps.put("(", ")");
        maps.put("[", "]");
        maps.put("{", "}");

        /**
         *使用堆栈，将左边括号全部压入栈中，
         * 然后碰见右边括号时候，先判断栈中是否为空，如果为空返回false（说明只有半个右边括号）
         * 否则从栈中取出最上面的左边括号拿去对应map中取到对应的右边括号，
         * 比较右边括号是否相等
         * 字符全部比较完成之后，判断栈中是否还存在字符，如果存在返回false（说明只有半个左边括号）
         */
        Stack<String> stack = new Stack();
        for (String s1 : s.split("")) {
            if ("(".equals(s1) || "[".equals(s1) || "{".equals(s1)) {
                stack.push(s1);
            } else {
                if (stack.isEmpty())
                    return false;
                String pop = stack.pop();
                if (!s1.equals(maps.get(pop))) {
                    return false;
                }
            }
        }
        if (!stack.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * 21：将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode heads = new ListNode(0);
        ListNode p = heads;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1 != null ? l1 : l2;
        return heads.next;
    }

    public static void main(String[] args) {
        Leetcode leetcode=new Leetcode();
        ListNode l11=new ListNode(1);
        ListNode l1=l11;
        l1=l1.next=new ListNode(2);
        l1=l1.next=new ListNode(4);
        l1=l1.next=new ListNode(8);

        ListNode l22=new ListNode(1);
        ListNode l2=l22;
        l2=l2.next=new ListNode(3);
        l2=l2.next=new ListNode(4);
        l2=l2.next=new ListNode(5);

        ListNode listNode = leetcode.mergeTwoLists(l11, l22);
        while (listNode!=null){
            int value=listNode.val;
            listNode=listNode.next;
            System.out.print(" "+value);
        }
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
