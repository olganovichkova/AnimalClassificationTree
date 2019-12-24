package main.java.edu.isu.cs2235.traversals;

import main.java.edu.isu.cs2235.structures.Node;
import main.java.edu.isu.cs2235.structures.Tree;
import main.java.edu.isu.cs2235.traversals.LinkedQueue;
import main.java.edu.isu.cs2235.traversals.commands.TraversalCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


/**
 * An implementation of the Iterative BreadthFirstSearch algorithm.
 *
 * @author Isaac Griffith
 */
public class BreadthFirstTraversal<E> extends main.java.edu.isu.cs2235.traversals.AbstractTraversal<E> {

    /**
     * Constructs a new BreadthFirst Traversal for the given tree.
     *
     * @param tree The tree to traverse
     */
    public BreadthFirstTraversal(Tree<E> tree) {
        super(tree);
    }

    /**
     * {@inheritDoc}
     */
//    1 -
//    2, 3 - 1
//    3, 4, 5 - 1, 2
//    4, 5, 6, 7 - 1, 2, 3
//    5, 6, 7 - 1, 2, 3, 4
//    6,7 - 1, 2, 3, 4, 5
//    7 - 1, 2, 3, 4, 5, 6
//    - 1, 2, 3, 4, 5, 6, 7



    @Override
    public Iterable<Node<E>> traverse() {
        if(tree.isEmpty()){
            return Collections.EMPTY_LIST;
        }
       return traverseFrom(tree.root());
    }

    @Override
    public Iterable<Node<E>> traverseFrom(Node<E> node) {
        if(node == null){
            throw new IllegalArgumentException("node is null");
        }
        LinkedQueue<Node<E>> queue = new LinkedQueue<>();
        List<Node<E>> list = new ArrayList<>();

        queue.offer(node);
        while(!queue.isEmpty()){
            Node<E> curr = queue.poll();
            list.add(curr);
            Iterator<Node<E>> it = tree.children(curr).iterator();
            while(it.hasNext()){
                queue.offer(it.next());
            }

        }
        return list;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setCommand(TraversalCommand cmd) {
        this.command = cmd;
    }
}
