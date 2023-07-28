package tags.linkedlist;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author Lan 实现一个LRU（最近最少使用）的链表
 * * Your LRUCache object will be instantiated and called as such:
 * * LRUCache obj = new LRUCache(capacity);
 * * int param_1 = obj.get(key);
 * * obj.put(key,value);
 * @createTime 2023-07-17  16:25
 **/
public class LRUCache {
    static class Node {
        public int key;
        public int value;
        public Node next;
        public Node prev;

        public Node() {

        }

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    static class NodeList {
        private Node head, tail;
        private int size;

        public NodeList() {
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            this.head.next = this.tail;
            this.tail.prev = this.head;
            this.size = 0;
        }

        /**
         * 保持 tail 不变，尾部添加节点，时间O(1)
         */
        public void addLast(Node node) {
            node.prev = this.tail.prev;
            this.tail.prev.next = node;

            node.next = this.tail;
            this.tail.prev = node;
            size++;
        }

        /**
         * 删除节点 node，node一定存在，时间O(1)
         * 到这里就能回答刚才「为什么必须要用双向链表」的问题了，因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)
         */
        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            this.size--;
        }

        /**
         * 删除链表中的第一个节点
         */
        public Node removeFirst() {
            if (this.head.next == this.tail) {
                return null;
            }
            Node first = this.head.next;
            remove(first);
            return first;
        }

        public int size() {
            return this.size;
        }
    }

    /**
     * 注意我们实现的双链表 API 只能从尾部插入，也就是说靠尾部的数据是最近使用的，靠头部的数据是最久为使用的。
     */

    private HashMap<Integer, Node> valueMap;
    private NodeList cache;
    private int capacity;

    public LRUCache(int capacity) {
        this.valueMap = new HashMap<>(capacity);
        this.capacity = capacity;
        this.cache = new NodeList();
    }

    /**
     * 标记为最近使用的
     */
    public void markRecently(int key) {
        Node node = valueMap.get(key);
        // 先删除，再添加
        this.cache.remove(node);
        this.cache.addLast(node);
    }

    /**
     * 添加最近使用的元素
     */
    public void addRecently(int key, int value) {
        Node node = new Node(key, value);
        this.cache.addLast(node);
        this.valueMap.put(key, node);
    }

    /**
     * 删除某一个 key
     */
    private void deleteKey(int key) {
        Node node = this.valueMap.get(key);
        // 从链表中删除
        this.cache.remove(node);
        // 从 map 中删除
        this.valueMap.remove(key);
    }

    /**
     * 删除最久未使用的元素
     */
    private void removeLeastRecently() {
        // 链表头部的第一个元素就是最久未使用的
        Node deletedNode = this.cache.removeFirst();
        // 同时别忘了从 map 中删除它的 key
        int deletedKey = deletedNode.key;
        this.valueMap.remove(deletedKey);
    }

    public int get(int key) {
        if (!this.valueMap.containsKey(key)) {
            return -1;
        }
        // 将该数据提升为最近使用的
        markRecently(key);
        return this.valueMap.get(key).value;
    }

    public void put(int key, int val) {
        if (this.valueMap.containsKey(key)) {
            // 删除旧的数据
            deleteKey(key);
            // 新插入的数据为最近使用的数据
            addRecently(key, val);
            return;
        }

        if (this.capacity == this.cache.size()) {
            // 删除最久未使用的元素
            removeLeastRecently();
        }
        // 添加为最近使用的元素
        addRecently(key, val);
    }
//    链接：https://leetcode.cn/problems/lru-cache/solution/lru-ce-lue-xiang-jie-he-shi-xian-by-labuladong/
}