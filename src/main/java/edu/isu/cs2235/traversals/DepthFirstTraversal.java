package main.java.edu.isu.cs2235.traversals;

import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * An implementation of TreeTraversal algorithms using a Template Design
 * Pattern.
 *
 * @author Isaac Griffith
 * @param <E> The type of data stored in the tree.
 */
public abstract class DepthFirstTraversal<E> extends AbstractTraversal<E> {

    /**
     * Constructs a new TreeTraversal template for the given tree.
     *
     * @param tree Tree to traverse with this template.
     * @throws IllegalArgumentException If provided tree is null
     */
    public DepthFirstTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
    }


    /**
     * Method which initiates the traversal of a tree from the provided start
     * node. This method returns an iterable container of nodes represting a
     * resulting traversal of the subtree.
     *
     * @param start The node at which to start the traversal
     * @return An iterable container of nodes representing the traversal of a
     * tree.
     */
    public Iterable<Node<E>> subTreeTraverse(Node<E> start) {
        if(start == null){
            throw new IllegalArgumentException();
        }
        List<Node<E>> list = new ArrayList<>();
        subtree(start, list);
        return list;
    }

    public abstract void subtree(Node<E> p, final List<Node<E>> snapshot) throws IllegalArgumentException;

}

