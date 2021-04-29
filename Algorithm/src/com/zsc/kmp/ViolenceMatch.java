package com.zsc.kmp;

/**
 * ����ƥ��
 *
 * @author zsc
 * @date 2021/4/26 9:39
 */
public class ViolenceMatch {
    public static void main(String[] args) {
        String s1 = "���� �й�����й� �й�����й�����й����";
        String s2 = "�й�����й���";
        int index = violenceMatch(s1, s2);
        System.out.println(index);
    }

    //����ƥ���㷨ʵ��
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
            } else { //û��ƥ��ɹ�
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
