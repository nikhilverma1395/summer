import java.util.Scanner;

/**
 * Created by Nikhil Verma on 7/2/2016.
 */
public class NDistGivenNode {
    private Node root;

    public static void main(String[] args) {
        new NDistGivenNode().job();
    }

    private void job1() {
        Node root = new Node(20);
        root.left = new Node(8);
        root.left.left = new Node(4);
        root.left.right = new Node(12);
        root.left.right.left = new Node(10);
        root.left.right.right = new Node(14);
        root.right = new Node(22);
        System.out.println(depth(new Node(12), root, 0));
    }

    private class Node {
        private int data;
        private Node left, right;
        private int level = 0;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
        }
    }

    private void job() {
        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        int[] arr = new int[num];
        for (int i = 0; i < num; i++)
            arr[i] = scanner.nextInt();

        for (int i = 0; i < num; i++)
            root = insert(root, arr[i]);

    }

    private int depth(Node start, Node root, int depth) {
        if (root == null) return -1;
        else if (root.data == start.data) return depth;
        else {

            int left = depth(start, root.left, depth + 1);
            int right = depth(start, root.right, depth + 1);
            return left > right ? left : right;
        }
    }

    private Node insert(Node node, int val) {
        if (root == null) node = new Node(val);
        else if (val < root.data) node.left = insert(root.left, val);
        else node.right = insert(root.right, val);
        return node;
    }
}
