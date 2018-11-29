/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 *
 * @author philipe
 */
public class AVLTree {
    protected Node root;
    protected boolean balanced;
    public AVLTree(boolean balanced) {
        this.balanced = balanced;
    }
    
    public AVLTree() {
        this.balanced = true;
    }
    /**
     * Busca por um valor na arvore
     * @param value
     * @return null ou o nó achado
     */
    public Node find(int value) {
        return findNode(root, value);
    }
    /**
     * Retorna a raiz da arvore
     * @return 
     */
    public Node getRoot() {
        return root;
    }
    /**
     * Insere um novo valor na arvore
     * @param value 
     */
    public void insert(int value) {
        Node nod = new Node(value);
        if(root == null) {
            root = nod;
        }
        else {
            root.insert(nod);
        }
        if(balanced && !nod.equals(root)) {
            rebalanceUp(nod.parent);
        }
    }
    /**
     * Remove um valor da arvore
     * @param value 
     */
    public void remove(int value) {
        Node nod = find(value);
        if(nod == null) {
            return;
        }
        if(nod.equals(root)) {
            removeRoot();
            return;
        }
        removeAux(nod);
    }
    /**
     * Retorna o menor valor da arvore
     * @return 
     */
    public int min() {
        Node nod = minSubTree(root, root);
        return nod.value;
    }
    
    private void removeRoot() {
        if(root.isLeaf()) {
            root = null;
        }
        else if(root.left != null && root.right == null) {
            root.left.parent = null;
            root = root.left;
        }
        else if(root.right != null && root.left == null) {
            root.right.parent = null;
            root = root.right;
        }
        else {
            Node minNode = minSubTree(root.right, root.right);
            removeAux(minNode);
            root.value = minNode.value;
        }
    }
    
    private void removeAux(Node nod) {
        if(nod.isLeaf()) {
            nod.parent.removeChild(nod);
        }
        else if(nod.left == null && nod.right != null) {
            nod.parent.removeChild(nod);
            nod.parent.insert(nod.right);
        }
        else if(nod.right == null && nod.left != null) {
            nod.parent.removeChild(nod);
            nod.parent.insert(nod.left);
        }
        else {
            Node minNode = minSubTree(nod.right, nod.right);
            removeAux(minNode);
            nod.value = minNode.value;
        }
        if(balanced) {
           rebalanceUp(nod.parent);
        }
    }
    
    private Node minSubTree(Node pivot, Node actualMin) {
        if(pivot == null) {
            return actualMin;
        }
        Node m = pivot.value < actualMin.value ? pivot : actualMin;
        Node minLeft = minSubTree(pivot.left, m);
        Node minRight = minSubTree(pivot.right, m);
        
        return minLeft.value < minRight.value ? minLeft : minRight;
    }
    
    private void rebalanceUp(Node nod) {
        Node actual = nod;
        while(!actual.equals(root)) {
            int fb = actual.getFb(); 
            if(fb < -1) {
                rightRotation(actual);
            }
            else if(fb > 1) {
                leftRotation(actual);
            }
            actual = actual.parent;
        }
    }
    
    private Node findNode(Node actual, int value) {
        if(actual == null) {
            return null;
        }
        else if(actual.value == value) {
            return actual;
        }
        return findNode(value < actual.value ? actual.left : actual.right, value);
    }
    
    void leftRotation(Node nod) {
        nod.right.parent = nod.parent;
        nod.parent.right = nod.right;
        Node leftChild = nod.right.left;
        nod.parent = nod.right;
        nod.right.left = nod;
        nod.right = leftChild;
    }
    
    void rightRotation(Node nod) {
        nod.left.parent = nod.parent;
        nod.parent.left = nod.left;
        Node rightChild = nod.left.right;
        nod.parent = nod.left;
        nod.left.right = nod;
        nod.left = rightChild;
    }    
}
