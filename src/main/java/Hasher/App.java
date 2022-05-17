package Hasher;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to hasher!");
        System.out.println("Press '1' to hash something.");
        System.out.println("Press '2' to generate statistics.");

        String selection = scan.nextLine();
        if (selection.equals("1")) {
            System.out.println("Enter an input to hash: ");
            String input = scan.nextLine();

            Hasher hasher = new Hasher();
            int hash = hasher.hash(input, 256);
            char casted = (char) hash;

            System.out.println("(HashCode of input: " + input.hashCode() + ")");
            System.out.println("Here is your result!");
            System.out.println("Hash: " + hash);
            System.out.println("As char: " + casted);
            System.out.println("As bytevalue: " + Integer.toBinaryString(hash));
        } else if (selection.equals("2")) {
            System.out.println("Enter path of where to save generated files:");
            String path = scan.nextLine();

            StatisticalGenerator generator;
            generator = new StatisticalGenerator(path, new Hasher());

            generator.generate();
        } else {
            System.out.println("No valid choise was made...");
        }
        System.out.println("Closing application...");

         scan.close();
    }
}
