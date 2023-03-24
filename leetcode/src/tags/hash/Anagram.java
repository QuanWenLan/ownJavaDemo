package tags.hash;

/**
 * @author Vin lan
 * @className Anagram
 * @description 242.有效的字母异位词
 * @createTime 2023-03-20  14:34
 **/
public class Anagram {
    public boolean isAnagram(String s, String t) {
        int[] arr = new int[26];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            arr[c - 'a']--;
        }

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                return false;
            }
        }
        return true;

    }
}
