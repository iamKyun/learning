//给你一个字符串 s，找到 s 中最长的回文子串。
// 回文串（palindromic string）是指这个字符串无论从左读还是从右读
//
// 示例 1：
//
//
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
//
//
// 示例 2：
//
//
//输入：s = "cbbd"
//输出："bb"
//
//
// 示例 3：
//
//
//输入：s = "a"
//输出："a"
//
//
// 示例 4：
//
//
//输入：s = "ac"
//输出："a"
//
//
//
//
// 提示：
//
//
// 1 <= s.length <= 1000
// s 仅由数字和英文字母（大写和/或小写）组成
//
// Related Topics 字符串 动态规划
// 👍 3267 👎 0

package com.iamkyun.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
        System.out.println(solution.longestPalindrome("banana"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            int start = 0, end = -1;
            StringBuffer t = new StringBuffer("#");
            for (int i = 0; i < s.length(); ++i) {
                t.append(s.charAt(i));
                t.append('#');
            }
            t.append('#');
            s = t.toString();

            List<Integer> arm_len = new ArrayList<Integer>();
            int right = -1, j = -1;
            for (int i = 0; i < s.length(); ++i) {
                int cur_arm_len;
                if (right >= i) {
                    int i_sym = j * 2 - i;
                    int min_arm_len = Math.min(arm_len.get(i_sym), right - i);
                    cur_arm_len = expand(s, i - min_arm_len, i + min_arm_len);
                } else {
                    cur_arm_len = expand(s, i, i);
                }
                arm_len.add(cur_arm_len);
                if (i + cur_arm_len > right) {
                    j = i;
                    right = i + cur_arm_len;
                }
                if (cur_arm_len * 2 + 1 > end - start) {
                    start = i - cur_arm_len;
                    end = i + cur_arm_len;
                }
            }

            StringBuffer ans = new StringBuffer();
            for (int i = start; i <= end; ++i) {
                if (s.charAt(i) != '#') {
                    ans.append(s.charAt(i));
                }
            }
            return ans.toString();
        }

        public int expand(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }
            return (right - left - 2) / 2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
