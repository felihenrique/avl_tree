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
public class Node {
    int value;
    Node parent;
    Node left;
    Node right;
    /**
     * Instancia um novo Nó
     * @param value O valor do nó.
     */
    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
        parent = null;
    }
    
    int calculateFb() {
        int heightLeft = left == null ? 0 : left.getHeight();
        int heightRight = right == null ? 0 : right.getHeight();
        return heightRight - heightLeft;
    }
    /**
     * Retorna o nó pai
     * @return 
     */
    public Node getParent() {
        return this.parent;
    }
    /**
     * Retorna o fator de balanço da árvore
     * @return 
     */
    public int getFb() {
        return calculateFb();
    }
    
    void removeChild(Node nod) {
        if(left != null && left.equals(nod)) {
            left = null;
        }
        else if(right != null && right.equals(nod)) {
            right = null;
        }
    }
    
    private int calculateHeight(Node nod) {
        if(nod == null) {
            return 0;
        }
        int heightLeft = calculateHeight(nod.left);
        int heightRight = calculateHeight(nod.right);
        return Math.max(heightLeft, heightRight) + 1;
    }
    /**
     * Retorna a altura da subarvore definida pelo nó.
     * @return 
     */
    public int getHeight() {
        return calculateHeight(this);
    }
    
    void insert(Node nod) {
        if(nod.value > value) {
            if(right == null) {
                right = nod;
                nod.parent = this;
            }
            else {
                right.insert(nod);
            }
        }
        else if(nod.value < value) {
            if(left == null) {
                left = nod;
                nod.parent = this;
            }
            else {
                left.insert(nod);
            }
        }
    }
    /**
     * Verificar se um nó é igual a outro
     * @param other
     * @return 
     */
    public boolean equals(Node other) {
        return other.value == value;
    }
    /**
     * Retorna o nó da esquerda.
     * @return 
     */
    public Node getLeft() {
        return left;
    }

    /**
     * Retorna se se é folha
     * @return 
     */
    public boolean isLeaf() {
        return left == null && right == null;
    }
    /**
     * Retorna o nó da direita.
     * @return 
     */
    public Node getRight() {
        return right;
    }
    /**
     * Para fins de debug...
     * @return 
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
