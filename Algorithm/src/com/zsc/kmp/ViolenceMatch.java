package com.zsc.kmp;

/**
 * ±©Á¦Æ¥Åä
 *
 * @author zsc
 * @date 2021/4/26 9:39
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "¹è¹è¹È ÉĞ¹è¹ÈÄãÉĞ¹è ÉĞ¹è¹ÈÄãÉĞ¹è¹ÈÄãÉĞ¹èÄãºÃ";
        String s2 = "ÉĞ¹è¹ÈÄãÉĞ¹èÄã";
        int index = violenceMatch(s1, s2);
        System.out.println(index);
    }

    //±©Á¦Æ¥ÅäËã·¨ÊµÏÖ
    public static int violenceMatch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;
        while (i < s1Len && j < s2Len) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else { //Ã»ÓĞÆ¥Åä³É¹¦
                i = i - (j - 1);
                j = 0;
            }
        }
        if (j == s2Len) {
            return i - j;
        } else {
            return -1;
        }
    }
}
