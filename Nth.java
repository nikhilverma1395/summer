import java.util.*;

/**
 * Created by Nikhil Verma on 7/4/2016.
 */
public class Nth {
    private int targetV;

    private class Node {
        private int data;
        private Node left, right;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }

    private class GraphNode {
        private HashMap<Integer, ArrayList<Integer>> edgeMap;

        GraphNode() {
            edgeMap = new HashMap<>();
        }

        private void addEdge(int v, int w) {
            if (edgeMap.containsKey(v)) {
                if (!edgeMap.get(v).contains(w))
                    edgeMap.get(v).add(w);
            } else {
                edgeMap.put(v, new ArrayList<>());
                edgeMap.get(v).add(w);
            }
        }
    }

    public static void main(String[] args) {
        new Nth().job1();
    }

    private void job1() {
        Node root = new Node(20);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.left.left = new Node(50);
        root.left.right.left.left.left = new Node(150);
        root.left.right.left.left.left.left = new Node(1250);
        root.left.right.left.left.left.right = new Node(1251);
        root.left.right.left.left.right = new Node(160);
        root.left.right.left.right = new Node(60);
        root.left.right.right = new Node(14);
        root.right = new Node(22);
        GraphNode graphNode = new GraphNode();
        generateGraph(null, null, root, graphNode, 10);
        check(graphNode);
        printN(graphNode, 4);
    }

    private void printN(GraphNode graphNode, int k) {
        Set<Integer> items = new HashSet<>();
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(targetV);
        HashMap<Integer, Boolean> hashMap = new HashMap<>();
        hashMap.put(targetV, true);
        for (int e = 0; e < k; e++) {
            items.clear();
            Queue<Integer> back = new ArrayDeque<>();
            back.addAll(queue);
            int xq = back.size();
            queue.clear();
            for (int i = 0; i < xq; i++) {
                int polled = back.poll();
                ArrayList<Integer> list = graphNode.edgeMap.get(polled);
                list.forEach((x) -> {
                    if (!hashMap.containsKey(x)) {
                        queue.add(x);
                        hashMap.put(x, true);
                    }
                });
            }
            items.addAll(queue);
        }

        System.out.println(Arrays.toString(items.toArray()));
    }

    private void check(GraphNode graphNode) {
        for (Map.Entry<Integer, ArrayList<Integer>> pop : graphNode.edgeMap.entrySet()) {
            System.out.println(pop.getKey() + "\t\t\t" + Arrays.toString(pop.getValue().toArray()));
        }
    }

    private void generateGraph(Node parent, Node root, Node child, GraphNode graphNode, int target) {
        if (child == null && root != null && parent != null)
            graphNode.addEdge(root.data, parent.data);
        if (child == null) return;
        if (root != null) graphNode.addEdge(root.data, child.data);
        if (parent != null && root != null) graphNode.addEdge(root.data, parent.data);
        if (child.data == target) targetV = child.data;
        generateGraph(root, child, child.left, graphNode, target);
        generateGraph(root, child, child.right, graphNode, target);
    }

}