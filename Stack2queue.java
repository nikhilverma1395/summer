import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by Nikhil Verma on 6/29/2016.
 */
public class Stack2queue {

    public static void main(String... args) throws IOException {
        new Stack2queue().task();
    }

    private void task() {
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
        queue1.add(1);
        queue1.add(2);
        queue1.add(3);
        queue1.add(4);
        queue1.add(5);
        queue1.add(6);
        queue1.add(7);

        pop_meth1(queue1, queue2);//pop1
        pop_meth1(queue1, queue2);//pop2
        pop_meth1(queue1, queue2);//pop3
        method2();
    }

    private void method2() {
        System.out.println("\n\nMethod 2\n\n");
        Queue<Integer> queue1 = new LinkedList<>();
        Queue<Integer> queue2 = new LinkedList<>();
    }



    private void pop_meth1(Queue<Integer> queue1, Queue<Integer> queue2) {
        if (!queue1.isEmpty()) {
            while (queue1.size() > 1)
                queue2.add(queue1.poll());
            System.out.println(queue1.peek());
        }
        Queue<Integer> q = new LinkedList<>();
        q.addAll(queue2);
        queue2.clear();//piece of crap, takes references and messes everything up!
        queue1.clear();
        queue1.addAll(q);
    }

}
