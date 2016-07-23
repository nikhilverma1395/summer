import com.sun.scenario.effect.impl.sw.sse.SSEBlend_ADDPeer;

import java.util.Arrays;

/**
 * Created by Nikhil Verma on 6/14/2016.
 */
public class Dijkstra {
    private static final int V = 9;
    static int[][] graph;

    public static void main(String[] args) {
        graph = new int[][]{
                {0, 4, 0, 0, 0, 0, 0, 8, 0},
                {4, 0, 8, 0, 0, 0, 0, 11, 0},
                {0, 8, 0, 7, 0, 4, 0, 0, 2},
                {0, 0, 7, 0, 9, 14, 0, 0, 0},
                {0, 0, 0, 9, 0, 10, 0, 0, 0},
                {0, 0, 4, 0, 10, 0, 2, 0, 0},
                {0, 0, 0, 14, 0, 2, 0, 1, 6},
                {8, 11, 0, 0, 0, 0, 1, 0, 7},
                {0, 0, 2, 0, 0, 0, 6, 7, 0}
        };
        for (int i = 0; i < V; i++)
            for (int j = 0; j < V; j++)
                if (graph[i][j] == 0) graph[i][j] = Integer.MAX_VALUE;
        Dijkstra t = new Dijkstra();
        t.dijkstra(graph);
    }

    private void dijkstra(int[][] graph) {
        dijkstra(0);
        System.out.print(Arrays.toString(dst));
    }

    int[] dst = new int[V];
    boolean[] isVisited = new boolean[V];
    int presum = 0;
    int ou = 0;

    private void dijkstra(int src) {
        if (++ou == V) return;
        Data small = smallestIndex(graph[src]);
        dst[src] = small.value + presum;
        presum += small.value;
        isVisited[src] = true;
        dijkstra(small.index);
    }

    private class Data {
        int index = 0, value = 0;

        public Data(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }

    public Data smallestIndex(int[] arr1) {
        int index = 0;
        int min = arr1[index];
        for (int i = 1; i < arr1.length; i++) {
            if (arr1[i] < min) {
                min = arr1[i];
                index = i;
            }
        }
        return new Data(index, min);
    }
}
