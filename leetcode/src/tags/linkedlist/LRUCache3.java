package tags.linkedlist;

import java.util.HashMap;

/**
 * @author Lan
 * @createTime 2023-07-18  09:41
 * 官方题解
 **/
public class LRUCache3 {
    private HashMap<Integer, LRUCache.Node> cache = new HashMap<>();
    private LRUCache.Node head, tail;
    private int capacity;
    private int size;

    public LRUCache3(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        // 使用伪头部和伪尾部节点
        this.head = new LRUCache.Node(0, 0);
        this.tail = new LRUCache.Node(-1, -1);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        LRUCache.Node node = this.cache.get(key);
        if (node == null) {
            return -1;
        }
        // 如果 key 存在，先通过哈希表定位，再移动到头部
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        LRUCache.Node node = this.cache.get(key);
        if (node == null) {
            node = new LRUCache.Node(key, value);
            this.cache.put(key, node);
            addToHead(node);
            ++this.size;
            if (this.size > this.capacity) {
                // 超出容量，删除尾节点
                LRUCache.Node removeTail = removeTail();
                this.cache.remove(removeTail.key);
                --this.size;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
    }

    /**
     * 删除尾节点
     */
    private LRUCache.Node removeTail() {
        LRUCache.Node desc = this.tail.prev;
        removeNode(desc);
        return desc;
    }

    private void removeNode(LRUCache.Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    /**
     * 添加至头部，顺序不能错
     */
    private void addToHead(LRUCache.Node node) {
        node.prev = this.head;
        node.next = this.head.next;
        this.head.next.prev = node;
        this.head.next = node;
    }

    /**
     * 移动到头部
     */
    private void moveToHead(LRUCache.Node node) {
        removeNode(node);
        addToHead(node);
    }

    public static void main(String[] args) {
        LRUCache3 lRUCache = new LRUCache3(2);
        lRUCache.put(1, 1); // 缓存是 {1=1}
        lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
        System.out.println(lRUCache.get(1));    // 返回 1
        lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
        System.out.println(lRUCache.get(2));    // 返回 -1 (未找到)
        lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
        System.out.println(lRUCache.get(1));    // 返回 -1 (未找到)
        System.out.println(lRUCache.get(3));    // 返回 3
        System.out.println(lRUCache.get(4));    // 返回 4
    }
}
