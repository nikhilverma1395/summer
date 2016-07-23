

import java.awt.*;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by Nikhil Verma on 6/20/2016.
 */
public class IntervalTree {
    private Node ROOT;

    private class Node {

        Point data;
        Node left, right;
        int max;

        public Node(Point data) {
            this.data = data;
            max = data.y;
        }
    }

    public static void main(String... args) throws IOException {
        new IntervalTree().task();
    }

    private void task() {
        insert();
        //     inorder(ROOT);
        java.util.List<Point> overlaps = new ArrayList<>();
        Node pop = findInterval(ROOT, new Node(new Point(600, 700)), overlaps);

        //      Node pop = findValue(ROOT, 22, overlaps);
        if (overlaps.size() != 0) {
            for (Point pt : overlaps) System.out.print("\t" + pt.toString());
        } else System.out.println("No overlap");
    }

    private Node findValue(Node root, int value, List<Point> ov) {
        if (root == null) return null;
        if (overlap(root.data, value)) ov.add(root.data);
        if (root.left != null && value <= root.left.max) findValue(root.left, value, ov);
        return findValue(root.right, value, ov);

    }


    private Node findInterval(Node root, Node node, List<Point> overlaps) {
        if (root == null) return null;
        if (overlap(root.data, node.data)) overlaps.add(root.data);
        else if (root.left != null && node.data.x >= root.left.max) findInterval(root.right, node, overlaps);
        return findInterval(root.left, node, overlaps);
    }

    private boolean overlap(Point pt, int val) {
        return val >= pt.x && val <= pt.y;
    }

    private boolean overlap(Point one, Point two) {
        return two.x <= one.y && one.x <= two.y;
    }

    private void insert() {
        // int data[][] = new int[][]{{15, 20}, {10, 11}, {17, 22}, {5, 20}, {12, 25}, {30, 40}, {14, 25}};
        int data[][] = new int[][]{{17, 19}, {5, 8}, {4, 8}, {15, 18}, {7, 10}, {16, 22}, {21, 22}, {2, 780}, {1, 242}, {0, 222}, {13, 45}, {18, 90}};
        //Interval ints[] = {{15, 20}, {10, 30}, {17, 19},
        //   {5, 20}, {12, 15}, {30, 40}
        // };

        for (int i = 0; i < data.length; i++)
            ROOT = insert(data[i]);
    }

    private Node insert(int[] pt) {
        return insert(ROOT, new Node(new Point(pt[0], pt[1])));
    }

    private Node insert(Node root, Node node) {
        if (root == null) return node;
        else if (node.data.x < root.data.x)
            root.left = insert(root.left, node);
        else root.right = insert(root.right, node);
        if (root.max < node.max)
            root.max = node.max;
        return root;
    }

    private void inorder(Node root) {
        if (root == null) return;
        inorder(root.left);
        System.out.println("[" + root.data.x + "," + root.data.y + "] - " + root.max);
        inorder(root.right);
    }
}
