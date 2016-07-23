import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Nikhil Verma on 7/2/2016.
 */
public class SquareRoot {
    public static void main(String... args) throws IOException {
        new SquareRoot().job();
    }

    private void job() {
        int num = new Scanner(System.in).nextInt();
        //    int ans = getSquareRoot(num);
        //    System.out.println(ans);
        System.out.println(babyLonian(num));
    }

    private double babyLonian(int num) {
        double x = num;
        double y = 1;
        double e = 0.00001;
        while ((x - y) > e) {
            x = (x + y) / 2;
            y = num / x;
        }
        return x;
    }


    private int getSquareRoXot(int num) {
        if (num == 0 || num == 1)
            return num;
        int i = 1;
        while (i * i < num) {
            i++;
        }
        return i;
    }
}
