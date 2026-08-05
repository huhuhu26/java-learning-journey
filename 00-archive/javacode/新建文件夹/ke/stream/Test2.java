import java.util.ArrayList;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Test2 {
    public static void main(String[] args){
        ArrayList<String> list = new ArrayList<>();
        list.add("zhangshan,23");
        list.add("lisi,24");
        list.add("wangwu,25");
//        list.stream()
//                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
//                .collect(Collectors.toMap(new Function<String, String>() {
//                    @Override
//                    public String apply(String s) {
//                        return s.split(",")[0];
//                    }
//                }, new Function<String, Integer>() {
//                    @Override
//                    public Integer apply(String s) {
//                        return Integer.parseInt(s.split(",")[1]);
//                    }
//                }));
        Map<String, Integer> map = list.stream()
                .filter(s -> Integer.parseInt(s.split(",")[1]) >= 24)
                .collect(Collectors.toMap(
                        s -> s.split(",")[0],
                        s -> Integer.parseInt(s.split(",")[1])
                ));
        System.out.println(map);
    }
}
