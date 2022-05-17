package Hasher;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class StatisticalGenerator {
    int numberOfStrings = 2000;
    int randomStringMaxLength = 500;
    private final String path;
    private final Hasher hasher;

    public StatisticalGenerator(String storagePath, Hasher hasher) {
        this.path = storagePath;
        this.hasher = hasher;
    }

    public void generate() {
        generateRandomStatistics();
        generateSmallVariationStatistics();
    }

    private void writeToFile(int[] buckets, String filename) {
        try {
            StringBuilder text = new StringBuilder();
            text.append("Buckets, Pigeons; \n");

            for (int i = 0; i < buckets.length; i++) {
                text.append(i).append(", ");
                text.append(buckets[i]).append("; \n");
            }

            String output = text.toString();
            // System.out.println(text.toString());

            File file = new File(this.path + "/" + filename + ".txt");

            PrintWriter printer = new PrintWriter(file);

            printer.print(output);
            printer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateRandomStatistics() {
        ArrayList<String> randomStrings = createRandomStrings();
        int[] allHashes = new int[randomStrings.size()];

        // Hash all strings
        for (int i = 0; i < randomStrings.size(); i++) {
            int hash = this.hasher.hash(randomStrings.get(i), 256);

            allHashes[i] = hash;
        }

        // Map all results
        int[] buckets = new int[256];
        for (int i = 0; i < buckets.length; i++) {
            for (int hash : allHashes) {
                if (hash == i) {
                    buckets[i] += 1;
                    // System.out.println(buckets[i]);
                }
            }
        }

        writeToFile(buckets, "randomStrings");
    }

    private ArrayList<String> createRandomStrings() {
        ArrayList<String> randomStrings = new ArrayList<>();

        // Build for each string...
        for (int i = 0; i < this.numberOfStrings; i++) {
            int randomLength = (int) (Math.random() * this.randomStringMaxLength);
            StringBuilder currentString = new StringBuilder();

            // Build characters for the string...
            for (int j = 0; j < randomLength; j++) {
                int randomChar = (int) (Math.random() * 255);
                char c = (char) randomChar;
                currentString.append(c);
            }
            randomStrings.add(currentString.toString());
        }

        return randomStrings;
    }

    private void generateSmallVariationStatistics() {
        ArrayList<String> variedStrings = createVariedStrings();

        int[] allHashes = new int[variedStrings.size()];

        // Hash all strings
        for (int i = 0; i < variedStrings.size(); i++) {
            int hash = this.hasher.hash(variedStrings.get(i), 256);

            allHashes[i] = hash;
        }

        // Map all results
        int[] buckets = new int[256];
        for (int i = 0; i < buckets.length; i++) {
            for (int hash : allHashes) {
                if (hash == i) {
                    buckets[i] += 1;
                    System.out.println(buckets[i]);
                }
            }
        }

        writeToFile(buckets, "variedStrings");
    }

    private ArrayList<String> createVariedStrings() {
        ArrayList<String> variedStrings = new ArrayList<>();
        String main = "abcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxyabcdefghijklmnopqrstuvwxy";
        String variation = "thisisavariedstringy";
        char[] variedCharacters = variation.toCharArray();

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 100; j++) {
                char[] mainCharacters = main.toCharArray();

                char c = variedCharacters[i];
                mainCharacters[j] = c;

                variedStrings.add(String.copyValueOf(mainCharacters));
            }
        }

        return variedStrings;
    }
}
