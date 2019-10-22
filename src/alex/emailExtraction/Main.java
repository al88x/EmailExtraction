package alex.emailExtraction;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {

    static final String PATTERN = "[a-zA-Z.'_%+-]+@([a-zA-Z.'_%+-]+\\.(?:[a-zA-Z]{2,3}|[a-zA-Z]{2}\\.[a-zA-Z]{2}))";
    public static final String TEXT_SAMPLE_TXT = "text\\sample.txt";
    public static final String SPLIT_WORDS = "(?: |\\r\\n)";

    public static void main(String[] args) throws IOException {
        Options rules = new Options(args);
        String file = Files.readString(Paths.get(TEXT_SAMPLE_TXT));
        String[] words = file.split(SPLIT_WORDS);
        Map<String, Integer> domainCounterMap = new HashMap<>();
        Pattern pattern = Pattern.compile(PATTERN);

        for (String word : words) {
            Matcher matcher = pattern.matcher(word);
            if (matcher.matches()) {
                String domain = matcher.group(1);
                if (rules.isDomainInIgnoreList(domain)) {
                    continue;
                }
                if (domainCounterMap.containsKey(domain)) {
                    Integer counts = domainCounterMap.get(domain);
                    domainCounterMap.replace(domain, ++counts);
                } else {
                    domainCounterMap.put(domain, 1);
                }
            }
        }
        domainCounterMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .filter(stringIntegerEntry -> stringIntegerEntry.getValue() > rules.getMinimumFrequency())
                .limit(10)
                .forEach(System.out::println);
    }


}
