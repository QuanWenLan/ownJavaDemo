package temptest;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author Vin lan
 * @className LinkedTest
 * @description
 * @createTime 2021-07-29  14:11
 **/
public class LinkedTest {
    @Test
    public void test1() {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offerFirst(1);
        queue.offerFirst(2);
        queue.offerFirst(3);
        System.out.println(Arrays.toString(queue.toArray()));
    }
}
