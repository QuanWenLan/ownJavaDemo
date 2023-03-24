package tags.hash;

/**
 * @author Vin lan
 * @className CanConstruct
 * @description
 * @createTime 2023-03-20  14:59
 **/
public class CanConstruct {
    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        // 定义一个哈希映射数组
        int[] record = new int[26];

        // 遍历
        for (char c : magazine.toCharArray()) {
            record[c - 'a'] += 1;
        }

        for (char c : ransomNote.toCharArray()) {
            record[c - 'a'] -= 1;
        }

        // 如果数组中存在负数，说明ransomNote字符串总存在magazine中没有的字符
        for (int i : record) {
            if (i < 0) {
                return false;
            }
        }

        return true;
    }
}
