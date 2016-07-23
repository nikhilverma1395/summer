import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by Nikhil Verma on 6/10/2016.
 */
public class Java8_Streams {
    public static void main(String[] args) {
        new Java8_Streams().task();
    }

    private void task() {
        List<String> list = Arrays.asList("dsd", "sds", "sdsgsdg", "gsg", "sffs", "");
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7);
        List<String> list1 = list.parallelStream().filter(str -> str.length() > 3).collect(Collectors.toList());
        System.out.println(list1);
        Random random = new Random();
        random.ints().limit(10).map(x->Math.abs(x)).sorted().forEach(System.out::print);
        System.out.println(list2.stream().map(x -> x + 5).distinct().count());
    }

}
