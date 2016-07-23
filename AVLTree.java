import java.util.Scanner;

/**
 * Created by Nikhil Verma on 6/6/2016.
 */
public class AVLTree {

    private Node ROOT;

    public class Node {
        private int data;
        private Node left, right;
        private int height;

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public Node() {
        }

        public Node(int data) {
            this.data = data;
            left = right = null;
            height = 0;
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
        AVLTree bst = new AVLTree();
        char ch;
        do {
            System.out.println("\nAVL  Tree Operations\n");
            System.out.println("1. insert ");
            System.out.println("2. delete");
            System.out.println("3. search");
            System.out.println("4. count nodes");
            System.out.println("5. check empty");
            System.out.println("6. Check Pre-order");

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
                case 6:
                    bst.preorder();
                    break;
                case 7:
                    BTreePrinter.printNode(bst.ROOT);
                    break;
                default:
                    System.out.println("Wrong Entry \n ");
                    break;
            }
            System.out.println("\nDo you want to continue (Type y or n) \n");
            ch = scan.next().charAt(0);
        } while (ch == 'Y' || ch == 'y');
    }

    private void preorder() {
        preorder(ROOT);
    }

    private boolean search(int val) {
        return search(ROOT, val);
    }

    private int countNodes() {
        return countNodes(ROOT);
    }

    private void preorder(Node r) {
        if (r != null) {
            System.out.print(r.getData() + " ");
            preorder(r.getLeft());
            preorder(r.getRight());
        }
    }

    private void insert(int value) {
        ROOT = insert(ROOT, value);
    }

    Node insert(Node node, int key) {

        if (node == null) return (new Node(key));

        if (key < node.getData()) node.left = insert(node.left, key);
        else node.right = insert(node.right, key);
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        int balance = getBalance(node);
        if (balance > 1 && key < node.left.getData()) return rightRotate(node);

        if (balance < -1 && key > node.right.getData()) return leftRotate(node);
        if (balance > 1 && key > node.left.getData()) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key < node.right.getData()) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }


    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int height(Node node) {
        return node == null ? -1 : node.getHeight();
    }

    private int countNodes(Node root) {
        if (root == null) return 0;
        return 1 + countNodes(root.getLeft()) + countNodes(root.getRight());
    }

    private boolean search(Node root, int k) {
        if (root == null) return false;
        if (k < root.getData())
            return search(root.getLeft(), k);
        else
            return k == root.getData() || search(root.getRight(), k);
    }

    private void delete(int value) {
        if (isEmpty())
            System.out.println("Tree Empty");
        else if (!search(value))
            System.out.println("Sorry " + value + " is not present");
        else
            ROOT = delete(ROOT, value);
    }


    int getBalance(Node N) {
        if (N == null) {
            return 0;
        }
        return height(N.left) - height(N.right);
    }

    Node delete(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.getData()) root.left = delete(root.left, key);
        else if (key > root.getData()) root.right = delete(root.right, key);
        else if ((root.left == null) || (root.right == null)) {
            Node temp = null;
            if (temp == root.left)
                temp = root.right;
            else
                temp = root.left;

            if (temp == null)
                root = null;
            else
                root = temp;

        } else {
            Node temp = minValueNode(root.right);
            root.setData(temp.getData());
            root.right = delete(root.right, temp.getData());
        }

        if (root == null) return root;

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private boolean isEmpty() {
        return null == ROOT;
    }

}

