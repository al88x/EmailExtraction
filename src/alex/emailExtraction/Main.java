package alex.emailExtraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    static final String PATTERN = "[a-zA-Z.'_%+-]+@([a-zA-Z.'_%+-]+\\.(?:[a-zA-Z]{2,3}|[a-zA-Z]{2}\\.[a-zA-Z]{2}))";

    public static void main(String[] args) throws IOException {

        String file = Files.readString(Paths.get("text\\sample.txt"));
        String[] words = file.split("(?: |\\r\\n)");
        int counter = 0;
        Map<String, Integer> domainCounterMap = new HashMap<>();
        Pattern pattern = Pattern.compile(PATTERN);
        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                String domain = matcher.group(1);
                if (domainCounterMap.containsKey(domain)) {
                    Integer counts = domainCounterMap.get(domain);
                    domainCounterMap.replace(domain, ++counts);
                } else {
                    domainCounterMap.put(domain, 1);
                }
                counter++;
            }
        }
        System.out.println(counter);
        System.out.println(domainCounterMap.entrySet());
    }
}
