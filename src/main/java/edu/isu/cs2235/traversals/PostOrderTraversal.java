package main.java.edu.isu.cs2235.traversals;

import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.Tree;
import main.java.edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import main.java.edu.isu.cs2235.traversals.commands.TraversalCommand;

import java.util.Collections;
import java.util.List;

/**
 * A recursive implementation of the PostOrder depthfirst traversal of a tree.
 *
 * @author Isaac Griffith
 * @param <E> The type of data stored in the tree to be traversed.
 */
public class PostOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new post order traversal for the provided tree
     *
     * @param tree The tree to traverse
     */
    public PostOrderTraversal(Tree<E> tree) {
        super(tree);
    }

    /**
     * {@inheritDoc}
     */

    public void subtree(Node<E> p, List<Node<E>> snapshot) {
        if(snapshot == null) {
            throw new IllegalArgumentException();
        }
        if(p == null){
            throw new IllegalArgumentException();
        }

        LinkedBinaryTree<E> bTree = (LinkedBinaryTree) this.tree;

        if(bTree.left(p) != null){
            subtree(bTree.left(p), snapshot);
        }
        if(bTree.right(p) != null){
            subtree(bTree.right(p), snapshot);
        }
        snapshot.add(p);
    }

    @Override
    public Iterable<Node<E>> traverse() {
        if(tree.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        return traverseFrom(tree.root());
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        return subTreeTraverse(node);
    }
}
