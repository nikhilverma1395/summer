import java.util.Arrays;

/**
 * Created by Nikhil Verma on 7/16/2016.
 */
public class Sierpinski {

    public static void main(String... args) {
        new Sierpinski().job();
    }

    private void job() {
        int n = 5;
        int mid = 63 / 2;
        char arr[][] = new char[32][63];
        for (int i = 0; i < 32; i++) {
            Arrays.fill(arr[i], '_');
            for (int j = 0; j < 63; j++) {
                if (j >= mid - i && j <= mid + i)
                    arr[i][j] = '1';
            }
        }
        print(arr, n, 0, 0, 32, 63);
        print(arr, n, 0, 0, 32 / 2, 63);
        print(arr, n, 32 / 2, 0, 32, 63 / 2);
        print(arr, n, 32 / 2, 63 / 2, 32, 63);
        printD(arr);
    }

    private void printD(char[][] arr) {
        for (int i = 0; i < 32; i++) {
            for (int j = 0; j < 63; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private void print(char arr[][], int x, int xStart, int yStart, int r, int c) {
        for (int i = xStart; i < r; i++) {
            for (int j = yStart; j < c; j++) {
                if (arr[i][j] == '1' && arr[r - i - 1][j] == '1')
                    arr[r - i - 1][j] = '_';

            }
        }

    }

}
