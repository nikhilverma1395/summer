import java.io.IOException;
import java.util.Arrays;

/**
 * Created by Nikhil Verma on 7/2/2016.
 */
public class SegmentTree {
    //Range Minimum
    private class Node {
        int queryX = -1;
        int queryY = -1;
        int val = Integer.MAX_VALUE;
        Node left, right;

        public Node() {
        }

        public Node(int x, int y) {
            this.queryX = x;
            this.queryY = y;
        }

        public Node(int x, int y, int val) {
            this.queryX = x;
            this.queryY = y;
            this.val = val;
        }
    }

    public static void main(String... args) throws IOException {
        new SegmentTree().task();
    }

    private void task() {//0,5 17 0-16
        int[] arr = {2, 3, 23, 23, 5, 34, 2, 53, 463, 3, 6, 3, 32, 6, 7, 3, 43, 63, 42, 4, -1, 3, 23, 2, 32, -2, 45, 4, 5, -45};
        Node root = makeSegTree(arr, 0, arr.length - 1);
        root.val = Math.min(root.left.val, root.right.val);
        root.queryX = 0;
        root.queryY = arr.length - 1;

        int sop = performQuery(root, 0, 3);//get Min in a range
                System.out.println(sop);
    }

    private boolean fullOverlap(int parX, int parY, int chX, int chY) {
        return chX >= parX && chX <= parY && chY >= parX && chY <= parY;
    }

    private boolean noOverlap(int parX, int parY, int chX, int chY) {
        return chX < parX && chY < parX || chX > parY && chY > parY;
    }


    private int performQuery(Node root, int x, int y) {
        if (root == null) return Integer.MAX_VALUE;
        else if (noOverlap(x, y, root.queryX, root.queryY)) return Integer.MAX_VALUE;
        else if (fullOverlap(x, y, root.queryX, root.queryY)) return root.val;
        return Math.min(performQuery(root.left, x, y), performQuery(root.right, x, y));

    }


    private Node makeSegTree(int[] arr, int begin, int end) {
        if (end - begin == 1) {
            Node node = new Node(begin, end);
            node.val = Math.min(arr[begin], arr[end]);
            Node nodeLeft = new Node(begin, begin);
            nodeLeft.val = arr[begin];
            Node nodeRight = new Node(end, end);
            nodeRight.val = arr[end];
            node.left = nodeLeft;
            node.right = nodeRight;

            return node;
        } else if (end == begin) {
            Node node = new Node(begin, begin);
            node.val = arr[begin];
            return node;
        }

        int mid = begin + (end - begin) / 2;
        Node node = new Node(begin, end);
        node.left = makeSegTree(arr, begin, mid);
        node.right = makeSegTree(arr, mid + 1, end);
        node.val = Math.min(node.left.val, node.right.val);
        return node;

    }

}
