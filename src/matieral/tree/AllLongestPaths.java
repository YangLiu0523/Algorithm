package matieral.tree;
import matieral.common_use.Node;
import java.util.*;

/**
 * calculate for every node in the tree the maximum length of a path that begins at the node
 */
class Info {
    int nodeVal;
    int length;
    public Info(int nodeVal, int length) {
        this.nodeVal = nodeVal;
        this.length = length;
    }

    @Override
    public String toString(){
        return "from: " + nodeVal + " length : " + length;
    }
}

public class AllLongestPaths {
    public Map<Integer, Integer> calculate(Node root) {
        // Select arbitrary root
        // calculate for every node x the maximum length of a path that goes through a child of x, O(n)
        Map<Integer, Info[]> longestPathDown = new HashMap<>();
        collectPathFromParentToChild(longestPathDown, root);

        // calculate for every node x the maximum length through its parent p, O(n)
        Map<Integer, Info[]> longestPath = new HashMap<>();
        longestPath.put(root.val, longestPathDown.get(root.val));

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (Node child : node.children) {
                Info fromDaddy  = longestPath.get(node.val)[0].nodeVal == child.val ?
                        new Info(node.val, longestPath.get(node.val)[1].length + 1) :
                        new Info(node.val, longestPath.get(node.val)[0].length + 1);
                Info fromChild = longestPathDown.get(child.val)[0];

                Info[] infos = fromDaddy.length > longestPathDown.get(child.val)[0].length ? new Info[]{fromDaddy, fromChild} : new Info[]{fromChild, fromDaddy};
                longestPath.put(child.val, infos);
                queue.offer(child);
            }
        }

        Map<Integer, Integer> ret = new HashMap<>();
        for(Map.Entry<Integer, Info[]> entry : longestPath.entrySet()) {
            ret.put(entry.getKey(), entry.getValue()[0].length);
        }
        return ret;
    }

    private int collectPathFromParentToChild(Map<Integer, Info[]> rec, Node root) {
        rec.putIfAbsent(root.val, new Info[]{new Info(-1, 0), new Info(-1, 0)});
        if (root.children.isEmpty()) {
            return 0;
        }

        for (Node child : root.children) {
            int curr = collectPathFromParentToChild(rec, child) + 1;
            if (curr > rec.get(root.val)[0].length) {
                rec.get(root.val)[0] = new Info(child.val, curr);
            }
            else if (curr > rec.get(root.val)[1].length) {
                rec.get(root.val)[1] = new Info(child.val, curr);
            }
        }
        return rec.get(root.val)[0].length;
    }

    public static void main(String[] args) {
        AllLongestPaths test = new AllLongestPaths();

        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);

        node1.children.addAll(new ArrayList<>(Arrays.asList(node2, node3, node4)));
        node2.children.addAll(new ArrayList<>(Arrays.asList(node5, node6)));

        // Expect: {1=2, 2=2, 3=3, 4=3, 5=3, 6=3}
        Map<Integer, Integer> res = test.calculate(node1);
        System.out.println(res);
    }
}
