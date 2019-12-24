package main.java.edu.isu.cs2235.traversals;

import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.Tree;
import main.java.edu.isu.cs2235.structures.impl.LinkedBinaryTree;
import main.java.edu.isu.cs2235.traversals.commands.TraversalCommand;

import java.util.Collections;
import java.util.List;

/**
 * A recursive implementation of the inorder tree traversal algorithm.
 *
 * @author Isaac Griffith
 * @param <E> The type of data in the tree to be traversed
 */
public class InOrderTraversal<E> extends DepthFirstTraversal<E> {

    /**
     * Constructs a new InOrder tree traversal for the given tree.
     *
     * @param tree Tree to be traversed.
     * @throws IllegalArgumentException If the type of tree to be traversed is
     * not a subtype of AbstractBinaryTree
     */
    public InOrderTraversal(Tree<E> tree) throws IllegalArgumentException {
        super(tree);
        if (!(tree instanceof LinkedBinaryTree)) {
            throw new IllegalArgumentException();
        }
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
        snapshot.add(p);
       if(bTree.right(p) != null){
           subtree(bTree.right(p), snapshot);
       }
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
