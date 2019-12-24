package main.java.edu.isu.cs2235.traversals.commands;

import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.Tree;

/**
 *
 * @author fate
 */
public abstract class TraversalCommand<E> {

    public abstract void execute(Tree<E> tree, Node<E> node);
}
