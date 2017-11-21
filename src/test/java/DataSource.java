import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataSource {
    public static List<User> getUsers() {
        return IntStream.rangeClosed(1, 10).
                mapToObj(value ->
                    new User("Sally " + value, "Smitt " + value, value)
                ).collect(Collectors.toList());
    }
}
