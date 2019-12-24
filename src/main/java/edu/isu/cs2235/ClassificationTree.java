package main.java.edu.isu.cs2235;

import main.java.edu.isu.cs2235.structures.BinaryTree;
import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import main.java.edu.isu.cs2235.traversals.BreadthFirstTraversal;
import main.java.edu.isu.cs2235.traversals.commands.EnumerationCommand;

import java.io.*;
import java.util.*;

/**
 * A very simple classification tree example using a BinaryTree and console
 * input.
 *
 * @author Isaac Griffith
 */
public class ClassificationTree {

    public static final String TREE_FILE_NAME = "tree.txt";

    private BinaryTree<Datum> tree;

    private EnumerationCommand enumCommand;

    /**
     * Constructs a new Animal tree class which manages an underlying animal
     * tree
     */
    public ClassificationTree() {
        tree = new LinkedBinaryTree<>();
        enumCommand = new EnumerationCommand();
        load();
    }

    /**
     * Main method which controls the identification and tree management loop.
     */

    public void identify1() {
        Scanner input = new Scanner(System.in);
        LinkedBinaryTree.BinaryTreeNode<Datum> node = (LinkedBinaryTree.BinaryTreeNode<Datum>) tree.root();

        String answer;  //variable for saving the input
        System.out.print("Is this animal " + node.getElement() + "? (Y/N) > ");
        answer = input.next().toUpperCase();     //save the input to a variable
        node = answer.equals("Y") ? node.getLeft() : node.getRight();   //assign node to the left or right based on the answer
        if(answer.equals("N") && node.getLeft()!= null && node.getRight() != null){

        }
    }


    public void identify() {
        Scanner input = new Scanner(System.in);
        LinkedBinaryTree.BinaryTreeNode<Datum> node = (LinkedBinaryTree.BinaryTreeNode<Datum>) tree.root();  //create node variable starting at the root
        List<String> properties = new ArrayList<>();
        String property;



        System.out.print("Is this animal " + node.getElement() + "? (Y/N) > ");
        String answer = input.next().toUpperCase();     //save the input to a variable
        node = answer.equals("Y") ? node.getLeft() : node.getRight();   //assign node to the left or right based on the answer
        property = answer.equals("Y") ? node.getParent().getElement().getPrompt() : "not " + node.getParent().getElement().getPrompt();
        properties.add(property);
        while(node.getRight() != null && node.getLeft() != null) {
            System.out.print("*Is this animal " + node.getElement() + "? (Y/N) > ");
            answer = input.next().toUpperCase();     //save the input to a variable
            node = answer.equals("Y") ? node.getLeft() : node.getRight();   //assign node to the left or right based on the answer
            property = answer.equals("Y") ? node.getParent().getElement().getPrompt() : "not " + node.getParent().getElement().getPrompt();
            properties.add(property);
        }
        System.out.print("Is this animal " + node.getElement() + "? (Y/N) > ");
        answer = input.next().toUpperCase();    //saves the input to a variable
//        node = answer.equals("Y") ? node.getLeft() : node.getRight();
        //enters loop only if the input is answered "N"
        if (answer.equals("N")) {
                //checks to see if the node is a leaf
                if (node.getRight() == null && node.getLeft() == null) {
                    LinkedBinaryTree.BinaryTreeNode<Datum> parent = (LinkedBinaryTree.BinaryTreeNode<Datum>) node.getParent(); //assign variable to the parent node
                    String savedAnswers = "";
                    int i = 0;
                    for(String pr : properties){
                        if(i == 0){
                            savedAnswers += pr;
                        } else {
                            savedAnswers += ", " + pr;
                        }
                        i++;
                    }
                    System.out.println("I do not know any " + savedAnswers
                            + " animals that are not " + node.getElement() + ".");
                    System.out.print("What is the new animal? > ");
                    String newAnimal = "a " + input.next();
                    System.out.print("What characteristic does " + newAnimal + " have that "
                            + node.getElement() + " does not? > ");
                    String newCharacteristic = input.next();
                    tree.remove(node.getElement(), parent);
                    if(parent.getLeft() == null) {
                        parent = (LinkedBinaryTree.BinaryTreeNode<Datum>) tree.addLeft(parent, new Datum(newCharacteristic));
                    } else {
                        parent = (LinkedBinaryTree.BinaryTreeNode<Datum>) tree.addRight(parent, new Datum(newCharacteristic));
                    }
                    enumCommand.execute(tree, parent);
                    tree.addLeft(parent, new Datum(newAnimal));
                    enumCommand.execute(tree, parent.getLeft());
                    tree.addRight(parent, node.getElement());
                }
        }
    }
    /**
     * Saves a tree to a file.
     */
    public void save() {
        System.out.println("saving data");
        System.out.println(tree.root().getElement().getPrompt());
        BreadthFirstTraversal<Datum> traversal = new BreadthFirstTraversal<>(tree);
        Iterator<Node<Datum>> it = traversal.traverse().iterator();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(TREE_FILE_NAME));
            while(it.hasNext()){
                LinkedBinaryTree.BinaryTreeNode<Datum> node = (LinkedBinaryTree.BinaryTreeNode<Datum>) it.next();
                LinkedBinaryTree.BinaryTreeNode<Datum> parent = (LinkedBinaryTree.BinaryTreeNode<Datum>) node.getParent();
                Datum datum = node.getElement();
                int parentID = parent != null ? parent.getElement().getNumber() : -1;
                int nodeID = datum.getNumber();
                char direction = parent != null && parent.getLeft() == node ? 'l' : 'r';
                String nodeName = datum.getPrompt();
                System.out.println("nodeName = " + nodeName );
                bw.write("" + parentID + ":" + nodeID + ":" + direction + ":" + nodeName);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads a tree from the given file, if an exception occurs during file
     * operations, a hardcoded basic tree will be loaded instead.
     */
    public void load() {

        System.out.println("loading data");
        Hashtable<Integer, Node<Datum>> nodes = new Hashtable<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(TREE_FILE_NAME));
            for(String line = br.readLine(); line != null; line = br.readLine()){
                String[] terms = line.split(":");
                int parentID = Integer.parseInt(terms[0]);
                int nodeID = Integer.parseInt(terms[1]);
                char nodeDirection = terms[2].charAt(0);
                String nodeName = terms[3];
                System.out.println("line : " + parentID + "; " + nodeID + "; " + nodeDirection + "; " + nodeName);
                Datum datum = new Datum(nodeName);
                Node<Datum> parent = nodes.get(parentID);
                Node<Datum> node;
                if(parentID == -1){
                    node = tree.setRoot(datum);
                } else {
                    node = nodeDirection == 'l' ? tree.addLeft(parent, datum) : tree.addRight(parent, datum);
                }
                nodes.put(nodeID, node);
                enumCommand.execute(tree, node);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
