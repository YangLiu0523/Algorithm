package matieral.tree;

import java.util.*;

/**
 * Reference: https://www.educative.io/edpresso/what-is-morris-traversal
 *
 * An interesting related question: https://leetcode.com/problems/recover-binary-search-tree/
 * Time Complexity: O(2n)
 *
 * lastHandled is different from the predecessor, 1 和 5 就没有出现在predecessor里面，因为left == null 没有必要做这件事
 */

class TreeNode {
    int data;
    TreeNode left_node, right_node;

    TreeNode(int item)
    {
        data = item;
        left_node =  null;
        right_node = null;
    }
}

public class MorrisInorderTraversal {
    TreeNode root;

    List<Integer> inorder = new ArrayList<>();

    void Morris(TreeNode root)
    {
        TreeNode curr, predecessor, lastVisited = null;


        if (root == null)
            return;

        curr = root;
        while (curr != null) {
            if (curr.left_node == null) {
                inorder.add(curr.data);
                lastVisited = curr;
                System.out.println("last visited: " + lastVisited.data);
                curr = curr.right_node;
            }
            else {
                /* Find the previous (prev) of curr */
                predecessor = curr.left_node;
                while (predecessor.right_node != null && predecessor.right_node != curr)
                    predecessor = predecessor.right_node;

                System.out.println(curr.data + "'s predesessor is " + predecessor.data);

                /* Make curr as right child of its prev */
                if (predecessor.right_node == null) {
                    predecessor.right_node = curr;
                    curr = curr.left_node;
                }
                else {  /* fix the right child of prev*/
                    predecessor.right_node = null;

                    inorder.add(curr.data);
                    lastVisited = curr;
                    System.out.println("last visited: " + lastVisited.data);

                    curr = curr.right_node;
                }

            }

        }
    }

    public static void main(String args[])
    {
        MorrisInorderTraversal tree = new MorrisInorderTraversal();
        tree.root = new TreeNode(4);
        tree.root.left_node = new TreeNode(2);
        tree.root.right_node = new TreeNode(5);
        tree.root.left_node.left_node = new TreeNode(1);
        tree.root.left_node.right_node = new TreeNode(3);

        tree.Morris(tree.root);
        System.out.println(tree.inorder);
    }
}