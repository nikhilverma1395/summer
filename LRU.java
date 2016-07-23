import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by Nikhil Verma on 7/7/2016.
 */
public class LRU {
    private static final byte SIZE = 4;

    public static void main(String[] args) {
        new LRU().job();
    }


    private void job() {
        List<Integer> list = new ArrayList<>();
        int[] pages = {1, 2, 3, 4, 1, 2, 5, 1, 2, 3, 4, 5};
        int[] block = new int[SIZE];
        Arrays.fill(block, -1);
        int index;
        for (int i = 1; i <= pages.length; i++) {
            index = i % SIZE;
            if (index == 0) index = SIZE - 1;
            else index--;

            if (indexOf(block, pages[i - 1]) == -1) {
                if (i <= SIZE) {
                    block[index] = pages[i - 1];
                    list.add(pages[i - 1]);
                } else {
                    block[indexOf(block, list.get(0))] = pages[i - 1];
                    list.remove(0);
                    list.add(pages[i - 1]);
                }
            } else {
                list.remove(new Integer(pages[i - 1]));
                list.add(pages[i - 1]);
            }
            System.out.println(pages[i - 1] + "\t" + Arrays.toString(block) + "\t" + Arrays.toString(list.toArray()));
        }
        System.out.println(Arrays.toString(block));
    }


    private int indexOf(int[] arr, int val) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == val)
                return i;
        return -1;
    }
}
