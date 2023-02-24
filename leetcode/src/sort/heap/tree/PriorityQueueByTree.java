package sort.heap.tree;

import java.util.Arrays;

/**
 * @author Vin lan
 * @className PriorityQueueByTree
 * @description 使用二叉堆实现优先队列
 * @createTime 2021-06-16  16:44
 **/
public class PriorityQueueByTree {
    private int[] array;
    private int size;

    public PriorityQueueByTree() {
        this.array = new int[32];
    }

    public void enQueue(int key) {
        // 队列的长度超出范围，扩容
        if (this.size >= this.array.length) {
            resize();
        }
        this.array[this.size++] = key;
        upAdjust();
    }

    private void resize() {
        // 队列容量翻倍
        int newSize = this.size * 2;
        this.array = Arrays.copyOf(this.array, newSize);
    }

    /**
     * 上浮调整
     */
    private void upAdjust() {
        int childIndex = this.size - 1;
        int parentIndex = (childIndex - 1) / 2;
        // temp 保存插入的叶子节点值，用于最后的赋值
        int temp = this.array[childIndex];
        while (childIndex > 0 && temp > this.array[parentIndex]) {  // 大于父节点的值，一直都是用的temp去做比较
            // 不需要真正的交换，单向赋值即可
            this.array[childIndex] = this.array[parentIndex];
            childIndex = parentIndex;
            parentIndex = (parentIndex - 1) / 2;
        }
        this.array[childIndex] = temp;
    }

    public int deQueue() throws Exception {
        if (this.size <= 0) {
            throw new Exception("the queue is empty");
        }
        // 获取堆顶元素
        int head = this.array[0];
        // 让最后一个元素移动到堆顶
        this.array[0] = this.array[--this.size];
        downAdjust();
        return head;
    }

    /**
     * 下沉 调整
     */
    public void downAdjust() {
        // temp 保持父节点值，用于最后赋值
        int parentIndex = 0;
        int temp = this.array[parentIndex];
        int childIndex = 1;
        while (childIndex < this.size) {
            // 如果有右孩子，且右孩子小于左孩子的值，定位到右孩子
            if (childIndex + 1 < this.size && array[childIndex + 1] > array[childIndex]) {
                childIndex++;
            }
            // 如果父节点大于任何一个孩子的值，则直接跳出
            if (temp >= array[childIndex]) {
                break;
            }
            // 不需要真正的交换，单向赋值即可
            array[parentIndex] = array[childIndex];
            parentIndex = childIndex; // 注意我们这里的下标，父节点的下标是原来孩子节点
            childIndex = childIndex * 2 + 1;
        }
        array[parentIndex] = temp; // 替换到了最后的孩子节点
    }

    public static void main(String[] args) throws Exception {
        PriorityQueueByTree priorityQueueByTree = new PriorityQueueByTree();
        priorityQueueByTree.enQueue(3);
        priorityQueueByTree.enQueue(5);
        priorityQueueByTree.enQueue(10);
        priorityQueueByTree.enQueue(2);
        priorityQueueByTree.enQueue(7);
        System.out.println("出队的元素： " + priorityQueueByTree.deQueue());
        System.out.println("出队的元素： " + priorityQueueByTree.deQueue());
    }

}
