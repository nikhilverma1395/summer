
import java.util.*;
//http://www.geeksforgeeks.org/count-total-set-bits-in-all-numbers-from-1-to-n/

/**
 * Created by Nikhil Verma on 7/16/2016.
 */
public class BitsSet {
    public static void main(String[] args) {
        BitsSet dns = new BitsSet();
        dns.job();


    }

    private void job() {
        getBits(new Scanner(System.in).nextInt());
    }

    private void getBits(int val) {

        int sum = 0;
        for (int i = 1; i <= val; i++)
            sum += bits(i);
        System.out.println(sum);
    }

    private int bits(int num) {
        if (num <= 0) return 0;
        return (num % 2 == 0 ? 0 : 1) + bits(num / 2);
    }
}