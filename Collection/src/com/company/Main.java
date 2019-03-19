package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author Taras Maslyuk
 *
 */
public class Main {
    static Random random = new Random();

    public static void main(String[] args) throws IOException {
        String[] str = {"first", "gelicopter", "insrument", "sweetness", "balalaika", "play", "wooman", "actor", "light", "app", "tree"};
        List<String> textCreate = new ArrayList<>();
        List<String> textSort = new ArrayList<>();
        for (int i = 0; i <= 1000; i++) {
            int num = random.nextInt(10);
            textCreate.add(str[num]);
        }
        addToFile("sampleFile.txt", textCreate);
        String content = new String(Files.readAllBytes(Paths.get("sampleFile.txt")), "UTF-8");
        String[] buffer = content.split(" ");
        for (int i = 0; i < buffer.length; i++) {
            textSort.add(buffer[i]);
        }
        Collections.sort(textSort);
        counterWords(textSort);
        Collections.sort(textSort, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return a.length() - b.length();
            }
        });
        addToFile("sortFile.txt", textSort);
    }

    /**
     * @author Taras Maslyuk
     * @param  path - the path to the file
     *
     */
    public static void addToFile(String path, List<String> list) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (int i = 0; i < list.size(); i++) {
            writer.write(list.get(i) + " ");
        }
        writer.close();
    }

    /**
     * @author Taras Maslyuk
     * @param  str - list of words to count
     */
    public static void counterWords(List<String> str) {
        Integer values = 0;
        Map<String, Integer> counter = new HashMap<>();
        for (String word : str) {
            if (!word.isEmpty()) {
                Integer count = counter.get(word);
                if (count == null) {
                    count = 0;
                }
                counter.put(word, ++count);
            }
        }
        for (String word : counter.keySet()) {
            System.out.println(word + ": " + counter.get(word));
        }
        int maxValueInMap = (Collections.max(counter.values()));
        for (Map.Entry<String, Integer> entry : counter.entrySet()) {
            if (entry.getValue() == maxValueInMap) {
                System.out.println("Word with max repeats: " + entry.getKey() + "     Repeats: " + maxValueInMap);
            }
        }
    }
}


