import java.util.Random;
import java.util.UUID;

/**
 * Created by Nikhil Verma on 7/16/2016.
 */
public class RandomLL {

    static Node head;


    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    void printrandom(Node node) {
        Node curr = node;
        int result = node.data;
        int n;
        for (n = 2; curr != null; n++) {
            if (new Random().nextInt() % n == 0) {
                result = curr.data;
            }
            curr = curr.next;
        }
        System.out.println("Randomly selected key is " + result);
    }

    // Driver program to test above functions
    public static void main(String[] args) {

        RandomLL list = new RandomLL();
        list.head = new Node(5);
        list.head.next = new Node(20);
        list.head.next.next = new Node(4);
        list.head.next.next.next = new Node(3);
        list.head.next.next.next.next = new Node(30);

        list.printrandom(head);

    }

}
