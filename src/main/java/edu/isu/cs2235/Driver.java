package main.java.edu.isu.cs2235;

import java.util.Hashtable;
import java.util.Scanner;

/**
 * Driver for the classification program
 *
 * @author Isaac Griffith
 */
public class Driver {

    /**
     * Runs the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClassificationTree tree = new ClassificationTree();
        String more = "Y";
        while (more.equals("Y")) {
            System.out.print("Do you have another animal to identify? (Y/N) > ");
            Scanner in = new Scanner(System.in);
            more = in.next().toUpperCase();

            if (more.equals("Y")) {
                tree.identify();
            }
        }
        tree.save();
    }

    public static void main1(String[] args) {
        Hashtable<String, String> hash = new Hashtable<>();
        hash.put("dog", "this is a dog");
        hash.put("cat", "this is a cat");
        hash.put("mouse", "this is a mouse");
        String value = hash.get("mouse");
        System.out.println(value);

        Hashtable<Integer, String> hash2= new Hashtable<>();
        hash2.put(1, "this is one");
        hash2.put(1000000, "this is one million");
        System.out.println(hash2.get(1000001));
    }
}
