
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by Nikhil Verma on 7/13/2016.
 */
public class IdenticalTrees {

    private class Node {
        int data;
        Node left, right;

        public Node(int item) {
            data = item;
            left = right = null;
        }
    }

    public static void main(String[] args) {
        new IdenticalTrees().job();
    }

    private void job() {
        IdenticalTrees tree = new IdenticalTrees();
        Node root1, root2;


        root1 = new Node(1);
        root1.left = new Node(2);
        root1.right = new Node(3);
        root1.left.left = new Node(4);
        root1.left.right = new Node(5);
        root1.left.right.left = new Node(51);

        root2 = new Node(1);
        root2.left = new Node(2);
        root2.right = new Node(3);
        root2.left.left = new Node(4);
        root2.left.right = new Node(5);
        root2.left.right.left = new Node(511);

        if (identicalTrees(root1, root2)) {
            System.out.println("Both trees are identical");
        } else {
            System.out.println("Trees are not identical");
        }
        if (identicalTreesViaQueue(root1, root2)) {
            System.out.println("Both trees are identical");
        } else {
            System.out.println("Trees are not identical");
        }
    }

    private boolean identicalTreesViaQueue(Node root1, Node root2) {
        Queue<Node> queue1 = new ArrayDeque<>();
        Queue<Node> queue2 = new ArrayDeque<>();
        queue1.add(root1);
        queue2.add(root2);
        while (!queue1.isEmpty() && !queue2.isEmpty()) {
            Node n1 = queue1.poll();
            Node n2 = queue2.poll();
            if (n1.data != n2.data) return false;
            if (n1.left != null && n2.left != null) {
                queue1.add(n1.left);
                queue2.add(n2.left);
            } else if (n1.left != null || n2.left != null)
                return false;
            if (n1.right != null && n2.right != null) {
                queue1.add(n1.right);
                queue2.add(n2.right);
            } else if (n1.right != null || n2.right != null)
                return false;

        }
        return true;
    }

    private boolean identicalTrees(Node root1, Node root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 != null && root2 != null)
            if (root1.data == root2.data && identicalTrees(root1.left, root2.left) && identicalTrees(root1.right, root2.right))
                return true;
        return false;
    }

}
