import org.omg.CORBA.NO_IMPLEMENT;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Nikhil Verma on 6/5/2016.
 */
public class BSTfull {
    private Node ROOT;

    private class Node {
        private int data;
        private Node left, right;

        public Node() {
        }

        public Node(int data) {
            this.data = data;
            left = right = null;
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        BSTfull bst = new BSTfull();
        char ch;
        do {
            System.out.println("\nBinary Search Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. count nodes");
            System.out.println("5. check empty");

            int choice = scan.nextInt();
            switch (choice) {
                case 1:
                    int op = scan.nextInt();
                    while (--op != 0)
                        bst.insert(scan.nextInt());
                    bst.preorder(bst.ROOT);
                    break;
                case 2:
                    System.out.println("Enter integer element to delete");
                    bst.delete(scan.nextInt());
                    break;
                case 3:
                    System.out.println("Enter integer element to search");
                    System.out.println("Search result : " + bst.search(scan.nextInt()));
                    break;
                case 4:
                    System.out.println("Nodes = " + bst.countNodes());
                    break;
                case 5:
                    System.out.println("Empty status = " + bst.isEmpty());
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    private boolean isEmpty() {
        return null == ROOT;
    }

    private boolean search(int k) {
        return ROOT.getData() == k || search(ROOT, k);
    }

    private boolean search(Node root, int k) {
        if (root == null) return false;
        if (k < root.getData())
            return search(root.getLeft(), k);
        else
            return k == root.getData() || search(root.getRight(), k);
    }

    private int countNodes() {
        if (ROOT != null)
            return countNodes(ROOT);
        else return 0;
    }

    private int countNodes(Node root) {
        if (root == null) return 0;
        System.out.print(" " + root.getData());
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }

    private void delete(int value) {
        if (isEmpty())
            System.out.println("Tree Empty");
        else if (!search(value))
            System.out.println("Sorry " + value + " is not present");
        else ROOT = delete(ROOT, value);
    }

    private Node delete(Node root, int value) {
        if (root.getData() == value) {
            Node left = root.getLeft();
            Node right = root.getRight();
            if (left == null && right == null) return null;
            else if (left == null) return right;
            else if (right == null) return left;
            else {
                Node temp = right;
                while (temp.getLeft() != null)
                    temp = temp.getLeft();
                temp.setLeft(left);
                return right;
            }
        } else if (value < root.getData()) {
            root.setLeft(delete(root.getLeft(), value));
        } else if (value > root.getData()) {
            root.setRight(delete(root.getRight(), value));
        }
        return root;
    }

    private void insert(int value) {
        ROOT = insert(ROOT, value);
    }

    private Node insert(Node root, int value) {
        if (root == null) root = new Node(value);
        else {
            if (value <= root.getData()) root.setLeft(insert(root.getLeft(), value));
            else root.setRight(insert(root.getRight(), value));
        }
        return root;
    }

    private void preorder(Node r) {
        if (r != null) {
            System.out.print(r.getData() + " ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }
}
