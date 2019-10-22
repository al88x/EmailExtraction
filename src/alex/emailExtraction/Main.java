package alex.emailExtraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        String file = Files.readString(Paths.get("text\\sample.txt"));
        String[] words = file.split(" ");
        int counter = 0;
        String pattern = "[a-zA-Z.'_%+-]+@[a-zA-Z.'_%+-]+.(?:[a-zA-Z]{2,3}|[a-zA-Z]{2}.[a-zA-Z]{2})";

        for (String word : words) {
            if (Pattern.matches(pattern, word)) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
