import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Nikhil Verma on 6/8/2016.
 */
public class KDTree {
    private static int DIMENSION = 0;
    private Node ROOT;

    private class Node {
        int DATA[] = new int[DIMENSION];
        Node LEFT;
        Node RIGHT;

        public Node(int data[]) {
            this.DATA = data;
            LEFT = null;
            RIGHT = null;
        }

        public int[] getDATA() {
            return DATA;
        }

        public void setDATA(int[] DATA) {
            this.DATA = DATA;
        }

        public Node getLEFT() {
            return LEFT;
        }

        public void setLEFT(Node LEFT) {
            this.LEFT = LEFT;
        }

        public Node getRIGHT() {
            return RIGHT;
        }

        public void setRIGHT(Node RIGHT) {
            this.RIGHT = RIGHT;
        }

    }

    public static void main(String[] args) {
        new KDTree().task();
    }

    private void task() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Dimension");
        DIMENSION = scan.nextInt();
        System.out.println("No.of entries:");
        int n = scan.nextInt();
        System.out.println("Enter entries");
        int data[][] = new int[n][DIMENSION];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < DIMENSION; j++) {
                data[i][j] = scan.nextInt();
            }
            ROOT = insertRec(ROOT, data[i]);
        }
        System.out.println("Search value");
        int[] pt = new int[]{5, 25};
        System.out.println(Arrays.toString(pt) + "\t" + search(ROOT, pt));
        pt = new int[]{34, 23};
        System.out.println(Arrays.toString(pt) + "\t" + search(ROOT, pt));
        pt = new int[]{12, 15};
        System.out.println(Arrays.toString(pt) + "\t" + search(ROOT, pt));
        System.out.println(getMin(ROOT, 0).getDATA()[0]);
        System.out.println(getMin(ROOT, 1).getDATA()[1]);
        pt = new int[]{5,25};
        delete(ROOT, pt);
        System.out.println(getMin(ROOT, 0).getDATA()[0]);

    }

    private void delete(Node root, int[] pt) {
        delete(root, pt, 0);
    }

    private Node delete(Node node, int[] data, int depth) {
        if (node == null) return null;
        int cd = depth % DIMENSION;
        if (arePointsSame(node.getDATA(), data)) {
            if (node.getRIGHT() != null) {
                Node rMin = getMin(node.getRIGHT(), cd);
                node.setDATA(rMin.getDATA());
                node.setRIGHT(delete(node.getRIGHT(), rMin.getDATA(), depth + 1));
            } else if (node.getLEFT() != null) {
                Node lMin = getMin(node.getLEFT(), cd);
                node.setDATA(lMin.getDATA());
                node.setRIGHT(delete(node.getLEFT(), lMin.getDATA(), depth + 1));
            } else {
                node = null;
                return null;
            }
        }
        if (data[cd] < node.getDATA()[cd])
            node.LEFT = delete(node.LEFT, data, depth + 1);
        else
            node.RIGHT = delete(node.RIGHT, data, depth + 1);
        return node;
    }

    private Node insertRec(Node root, int[] data) {
        return insertRec(root, data, 0);
    }

    private boolean search(Node root, int[] pt) {
        return search(root, pt, 0);
    }

    private boolean search(Node node, int[] pt, int depth) {
        if (node == null)
            return false;
        else if (arePointsSame(node.getDATA(), pt)) return true;
        else {
            int cd = depth % DIMENSION;
            if (pt[cd] < node.getDATA()[cd])
                return search(node.getLEFT(), pt, depth + 1);
            return search(node.getRIGHT(), pt, depth + 1);
        }
    }

    private boolean arePointsSame(int pt1[], int pt2[]) {
        for (int i = 0; i < DIMENSION; ++i)
            if (pt1[i] != pt2[i])
                return false;
        return true;
    }

    private Node insertRec(Node root, int[] data, int depth) {
        if (root == null) return new Node(data);
        int dp = depth % DIMENSION;
        if (data[dp] < root.getDATA()[dp])
            root.setLEFT(insertRec(root.getLEFT(), data, depth + 1));
        else
            root.setRIGHT(insertRec(root.getRIGHT(), data, depth + 1));
        return root;
    }

    private Node getMin(Node root, int d) {
        return root == null ? null : getMin(root, d, 0);
    }

    private Node getMin(Node node, int d, int depth) {
        if (node == null) return null;
        int cd = depth % DIMENSION;
        if (cd == d)
            return node.getLEFT() == null ? node : getMin(node.getLEFT(), d, depth + 1);
        return minNode(node, getMin(node.getLEFT(), d, depth + 1), getMin(node.getRIGHT(), d, depth + 1), d);
    }

    private Node minNode(Node x, Node y, Node z, int d) {
        Node res = x;
        if (y != null && y.getDATA()[d] < res.getDATA()[d])
            res = y;
        if (z != null && z.getDATA()[d] < res.getDATA()[d])
            res = z;
        return res;
    }
}
