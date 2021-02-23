package matieral.tree;

class NodeNext {
    public int val;
    public NodeNext left;
    public NodeNext right;
    public NodeNext next;

    public NodeNext() {}

    public NodeNext(int _val) {
        val = _val;
    }

    public NodeNext(int _val, NodeNext _left, NodeNext _right, NodeNext _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
};


public class NextRightPointer {
    public NodeNext connect(NodeNext root) {
        if (root == null) return root;

        NodeNext leftMost = root;
        while (leftMost.left != null) {
            NodeNext head = leftMost;
            while (head != null) {
                head.left.next = head.right;
                if (head.next != null) {
                    head.right.next = head.next.left;
                }
                head = head.next;
            }
            leftMost = leftMost.left;
        }
        return root;
    }

    public NodeNext connect2(NodeNext root) {
        if (root == null) {
            return root;
        }

        NodeNext leftMost = root;

        while (leftMost != null) {
            NodeNext curr = leftMost;
            leftMost = null;
            NodeNext prev = null;

            while (curr != null) {
                if (curr.left != null) {
                    if (prev == null) leftMost = curr.left;
                    else prev.next = curr.left;
                    prev = curr.left;
                }
                if (curr.right != null) {
                    if (prev == null) leftMost = curr.right;
                    else prev.next = curr.right;
                    prev = curr.right;
                }

                curr = curr.next;
            }
        }
        return root;

    }
}
