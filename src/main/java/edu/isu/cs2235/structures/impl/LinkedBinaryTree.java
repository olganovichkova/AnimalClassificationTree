package main.java.edu.isu.cs2235.structures.impl;

import main.java.edu.isu.cs2235.structures.BinaryTree;
import main.java.edu.isu.cs2235.structures.Node;
import java.util.ArrayList;
import java.util.List;

public class LinkedBinaryTree<E>  implements BinaryTree<E> {
    public BinaryTreeNode root = null;
    private int size = 0;

    public BinaryTreeNode<E> createNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right)throws IllegalArgumentException{
        if(element == null){
            throw new IllegalArgumentException("");
        }
        if(parent == null){
            root = new BinaryTreeNode<>(element, null, left, right);
            return root;
        }
        size++;
        return new BinaryTreeNode<E>(element, parent, left, right);
    }

    public BinaryTreeNode<E>  root() {
        return root;
    }

    public BinaryTreeNode<E> setRoot(E element) throws IllegalArgumentException {
        if(element == null){
            root = null;
            size = 0;
            return root;
        }
        size = 1;
        root = new BinaryTreeNode<>(element, null, null, null);
        return root;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public BinaryTreeNode<E> insert(E element, Node<E> parent) throws IllegalArgumentException {
        if(parent == null || element == null){
            throw new IllegalArgumentException("cannot insert null element or to null node");
        }
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(parent);

        if(node.leftChild != null && node.rightChild != null)
            throw new IllegalArgumentException("cannot insert new element because node already has two children");

        if(node.leftChild == null){
            BinaryTreeNode<E> newLeft = createNode(element, node, null, null);
            node.setLeftChild(newLeft);
            return newLeft;
        }
        if(node.leftChild != null){
            BinaryTreeNode<E> newRight = createNode(element, node, null, null);
            node.setRightChild(newRight);
            return newRight;
        }
        return null;
    }

    public boolean remove(E element, Node<E> p)  throws IllegalArgumentException {
        if(p == null || element == null){
            return false;
        }
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(node.rightChild.getElement().equals(element)){
            node.rightChild.setLeftChild(null);
            node.rightChild.setRightChild(null);
            node.rightChild.setParent(node.rightChild);
            node.setRightChild(null);
            size--;
            return true;
        }
        if(node.leftChild.getElement().equals(element)){
            node.leftChild.setLeftChild(null);
            node.leftChild.setRightChild(null);
            node.leftChild.setParent(node.leftChild);
            node.setLeftChild(null);
            size--;
            return true;
        }
        return false;
    }

    public E set(Node<E> node, E element) throws IllegalArgumentException{
        if(node == null || element == null){
            throw new IllegalArgumentException("");
        }
        BinaryTreeNode<E> treeNode = (BinaryTreeNode<E>)validate(node);
        E tmp = treeNode.getElement();
        treeNode.setElement(element);
        return tmp;
    }

    @Override
    public Node<E> left(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.getLeft();
    }

    @Override
    public Node<E> right(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return node.getRight();
    }

    @Override
    public Node<E> sibling(Node<E> p) throws IllegalArgumentException {
        return null;
    }

    public Node<E> addLeft(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(element == null){
            throw new IllegalArgumentException("element is null");
        }
        if(node.getLeft() != null){
            throw new IllegalArgumentException("left child already exists");
        }
        BinaryTreeNode<E> newLeft = createNode(element, node, null, null);
        node.setLeftChild(newLeft);
        return newLeft;
    }

    @Override
    public Node<E> addRight(Node<E> p, E element) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(element == null){
            throw new IllegalArgumentException("element is null");
        }
        if(node.getRight() != null){
            throw new IllegalArgumentException("left child already exists");
        }
        BinaryTreeNode<E> newRight = createNode(element, node, null, null);
        node.setRightChild(newRight);
        return newRight;
    }

    public BinaryTreeNode<E> validate(Node<E> p) {
        if(!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Not an instance of BinaryTreeNode");
        }
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)p;
        if(node.getParent() == node){
            throw new IllegalArgumentException("p is no longer in a tree");
        }
        return node;
    }

    @Override
    public int depth(Node<E> node) throws IllegalArgumentException {
        if(node == null){
            throw new IllegalArgumentException("node is null");
        }
        if(isRoot(node)){
            return 0;
        }
        else
            return 1 + depth(parent(node));
    }

    @Override
    public int subTreeSize(Node<E> node) throws IllegalArgumentException {
        //recursively calculates the size of subtree at given root(size excludes the root)
        if(node == null){
            throw new IllegalArgumentException("node is null");
        }
        if(isExternal(node)){
            return 0;
        }
        else
            return  2 + subTreeSize(left(node)) + subTreeSize(right(node));
    }

    @Override
    public boolean isLastChild(Node<E> node) throws IllegalArgumentException {
        BinaryTreeNode<E> travNode = (BinaryTreeNode<E>)validate(node);
        if(isRoot(travNode)){
            return true;
        }
        return false;
    }

    public Node<E> parent(Node<E> p) {
        if(p == null)
            throw new IllegalArgumentException("cannot get a parent from null");
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        return p.getParent();
    }

    @Override
    public Iterable<Node<E>> children(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        List<Node<E>> snapshot = new ArrayList<>(2);
        if(left(p) != null){
            snapshot.add(left(p));
        }
        if(right(p) != null){
            snapshot.add(right(p));
        }
        return snapshot;
    }

    public int size() {
        return size;
    }

    @Override
    public int numChildren(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(node.rightChild == null && node.leftChild == null){
            return 0;
        }
        if(node.rightChild == null){
            return 1;
        }
        if(node.leftChild == null){
            return 1;
        }
        return 2;
    }

    @Override
    public boolean isInternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(node.rightChild != null && node.leftChild != null){
            return true;
        }
        return false;
    }

    @Override
    public boolean isExternal(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(node.leftChild == null & node.rightChild == null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isRoot(Node<E> p) throws IllegalArgumentException {
        BinaryTreeNode<E> node = (BinaryTreeNode<E>)validate(p);
        if(node == root){
            return true;
        }
        return false;
    }

    public static class BinaryTreeNode<E> implements Node<E> {
        private E nodeElement;
        private BinaryTreeNode<E> leftChild;
        private BinaryTreeNode<E> rightChild;
        private BinaryTreeNode<E> nodeParent;

        public BinaryTreeNode(E element, BinaryTreeNode<E> parent, BinaryTreeNode<E> left, BinaryTreeNode<E> right){
            nodeElement = element;
            nodeParent = parent;
            rightChild = right;
            leftChild = left;
        }

        public BinaryTreeNode<E> getLeft() {
            return leftChild;
        }

        public BinaryTreeNode<E> getRight() {
            return rightChild;
        }

        public void setRightChild(BinaryTreeNode<E> right) {
            rightChild = right;
        }

        public void setLeftChild(BinaryTreeNode<E> left) {
            leftChild = left;
        }

        public void setParent(BinaryTreeNode<E> parent) {
            nodeParent = parent;
        }

        @Override
        public E getElement() {
            return nodeElement;
        }

        @Override
        public void setElement(E element) throws IllegalArgumentException {
            if(element == null)
                throw new IllegalArgumentException("Cannot set null element in BinaryTreeNode");
            nodeElement = element;
        }

        @Override
        public Node<E> getParent() {
            return nodeParent;
        }
    }
}
