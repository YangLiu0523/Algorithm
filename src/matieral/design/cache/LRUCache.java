package matieral.design.cache;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/lru-cache/
 * it takes constant time to add and remove nodes from the head or tail
 */

class DLinkedNode {
    public int key;
    public int value;
    public DLinkedNode prev;
    public DLinkedNode next;
}

class LRUCache {
    Map<Integer, DLinkedNode> map = new HashMap<>();
    int capacity;
    int size;
    DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.next = head;
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        DLinkedNode node = map.get(key);
        moveToHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (!map.containsKey(key)) {
            DLinkedNode node = new DLinkedNode();
            node.key = key;
            node.value = value;
            map.put(key, node);
            add(node);
            size++;
            if (size > capacity) {
                DLinkedNode tail = popTail();
                map.remove(tail.key);
                size--;
            }
        } else {
            DLinkedNode node = map.get(key);
            node.value = value;
            moveToHead(node);
        }
    }

    private void remove(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    private void add(DLinkedNode node) {
        node.prev = head;
        node.next = head.next;

        head.next = node;
        node.next.prev = node;
    }

    private DLinkedNode popTail() {
        DLinkedNode node = tail.prev;
        remove(node);
        return node;
    }

    private void moveToHead(DLinkedNode node) {
        remove(node);
        add(node);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */