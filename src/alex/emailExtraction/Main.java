package alex.emailExtraction;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.IntStream;

public class Main {

    public static void main(String[] args) throws IOException {

        String file = Files.readString(Paths.get("text\\sample.txt"));
        String[] words = file.split(" ");
        int counter = 0;
        for (int i = 0; i < words.length; i++) {
            if (words[i].contains("@techsmiths.uk")) {
                counter++;
            }
        }
        System.out.println(counter);
    }
}
