package main.java.edu.isu.cs2235.traversals;

import main.java.edu.isu.cs2235.structures.Tree;
import main.java.edu.isu.cs2235.traversals.commands.TraversalCommand;


/**
 * The abstract tree traversal super class.
 *
 * @author Isaac Griffith
 */
public abstract class AbstractTraversal<E> implements TreeTraversal<E> {

    protected Tree<E> tree;
    protected TraversalCommand command;

    /**
     * Constructs an AbstractTraversal for the given tree.
     *
     * @param tree Tree to traverse
     * @throws IllegalArgumentException if the provided tree is null.
     */
    public AbstractTraversal(Tree<E> tree) throws IllegalArgumentException {
        if (tree == null) {
            throw new IllegalArgumentException();
        }
        this.tree = tree;
        this.command = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(TraversalCommand command) {
        this.command = command;
    }
}
