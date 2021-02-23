package matieral.design.cache;
import java.util.*;

/**
 * Test: https://leetcode.com/problems/lfu-cache/
 * Ref: https://leetcode.com/problems/lfu-cache/discuss/1055640/Java-O(1)-Solution-using-HashMap-and-Self-defined-LinkedList
 */

class LFUCache {

    private int capacity = 0;  // [0, 10000]
    private int count = 0;
    private HashMap<Integer, Node> nodeMap;
    private HashMap<Integer, Integer> frequencyMap;  // <= 100,000 calls
    private HashMap<Integer, LinkedList> sameFreqMap; // Each entry value is a linked list
    private int minFrequency = Integer.MAX_VALUE;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        nodeMap = new HashMap<>();
        frequencyMap = new HashMap<>();
        sameFreqMap = new HashMap<>();
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }
        Node node = nodeMap.get(key);
        addFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0){
            return;
        }
        Node node = null;
        if (nodeMap.containsKey(key)) {
            node = nodeMap.get(key);
            node.value = value;
        } else {
            node = new Node(key, value);
            if (count < capacity) {
                count ++;
            } else {
                Node removed = removeLfuNode();
                nodeMap.remove(removed.key);
            }
            minFrequency = 1;  // new node above to be inserted
        }

        nodeMap.put(key, node);
        addFrequency(node);
    }

    // maintain frequencyMap, sameFreqMap, and minFrequency when adding a node's frequency
    private void addFrequency(Node node) {
        int oldCount = frequencyMap.getOrDefault(node.key, 0);
        int newCount = oldCount + 1;
        // 1. update new count
        frequencyMap.put(node.key, newCount);
        // 2. remove node from list of old count in sameFreqMap and update minFrequency
        LinkedList oldList = sameFreqMap.getOrDefault(oldCount, null);
        if (oldList == null || oldList.size == 0) {
            minFrequency = 1;
        } else {
            oldList.deleteNode(node);
            if (minFrequency == oldCount && oldList.size == 0) {
                minFrequency ++;  // == newCount (with non-empty list)
            }
        }
        // 3. add node to list of new count in sameFreqMap (after deleteNode call to avoid bug)
        LinkedList newList = sameFreqMap.getOrDefault(newCount, new LinkedList());
        newList.addToHead(node);
        sameFreqMap.put(newCount, newList);
    }

    // only call when inserting a new node and beyound capacity
    private Node removeLfuNode(){
        if (capacity == 0) { // lfuList should be empty
            return null;
        }
        LinkedList lfuList = sameFreqMap.get(minFrequency); // non-null
        Node lfuNode = lfuList.tail.prev;
        if (lfuList.size == 1) {
            sameFreqMap.remove(minFrequency);
        } else {
            lfuList.deleteNode(lfuNode);
        }
        frequencyMap.remove(lfuNode.key);
        return lfuNode;
    }

    private class Node {
        int key = -1;
        int value = -1;
        Node prev = null;
        Node next = null;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private class LinkedList {
        Node head;
        Node tail;
        int size = 0;

        public LinkedList(){
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        // always call after delelteNode if has two call both
        // head < node < tail
        void addToHead(Node node) {
            node.next = head.next;
            node.next.prev = node;
            node.prev = head;
            head.next = node;
            size++;
        }

        // node must be in the list
        // (must remove from old list and then add to other lists)
        // head < node < tail
        void deleteNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

}