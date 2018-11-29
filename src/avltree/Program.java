/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package avltree;

/**
 * Obs: Os teste de inserção / deleção estão sendo feitos sem o balanceamento pois fui escrevendo a 
 * medida que ia implementando, e implementei primeiramente a arvore binária sem balanceamento.
 * @author philipe
 */
public class Program {
    /// Teste de inserção na arvore binaria padrao
    static void testInsertion() {
        AVLTree tree = new AVLTree(false);
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);
        
        assert(tree.getRoot().value == 10);
        assert(tree.getRoot().getLeft().value == 5);
        assert(tree.getRoot().getRight().value == 20);
        assert(tree.getRoot().getLeft().getLeft().value == 2);
        assert(tree.min() == 2);
    }
    /// Teste de busca
    static void testFind() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);

        assert(tree.find(10).value == 10);
        assert(tree.find(20).value == 20);
        assert(tree.find(100).value == 100);
        assert(tree.find(200) == null);
    }
    /// Teste de deleção na arvore binaria padrao
    static void testDeletion() {
        AVLTree tree = new AVLTree(false);
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);
        tree.insert(38);
        
        tree.remove(2);
        tree.remove(20);
        tree.remove(10);
        tree.remove(40);
        
        assert(tree.getRoot().value == 30);
        assert(tree.getRoot().getLeft().value == 5);
        assert(tree.getRoot().getRight().value == 100);
        assert(tree.getRoot().getRight().getLeft().value == 38);
    }
    /// Teste do left rotation
    static void testLeftRotation() {
        AVLTree tree = new AVLTree(false);
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);
        tree.insert(37);
        
        Node nod = tree.find(30);
        tree.leftRotation(nod);
        assert(nod.getRight().value == 37);
        assert(nod.getParent().value == 40);
        assert(nod.getParent().getRight().value == 100);
    }
    /// Teste do calculo de altura
    static void testHeight() {
        AVLTree tree = new AVLTree(false);
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);
        
        int h = tree.getRoot().getHeight();
        
        assert(tree.getRoot().getHeight() == 5);
        assert(tree.getRoot().getRight().getHeight() == 4);
        assert(tree.getRoot().getLeft().getHeight() == 2);
    }
    /// Teste da inserção / deleção com balanceamento
    static void testDeletionInsertionBalanced() {
        AVLTree tree = new AVLTree();
        tree.insert(10);
        tree.insert(5);
        tree.insert(20);
        tree.insert(2);
        tree.insert(30);
        tree.insert(40);
        tree.insert(100);
        tree.insert(37);
        tree.insert(1);
        
        assert(tree.getRoot().getLeft().value == 2);
        assert(tree.getRoot().getLeft().getRight().value == 5);
        assert(tree.getRoot().getLeft().getLeft().value == 1);
        assert(tree.getRoot().getRight().value == 30);
        assert(tree.getRoot().getRight().getLeft().value == 20);
        assert(tree.getRoot().getRight().getRight().value == 40);
        assert(tree.getRoot().getRight().getRight().getLeft().value == 37);
        assert(tree.getRoot().getRight().getRight().getRight().value == 100);
        
        tree.remove(37);
        tree.insert(101);
        assert(tree.getRoot().getRight().getRight().value == 100);
        assert(tree.getRoot().getRight().getRight().getLeft().value == 40);
        assert(tree.getRoot().getRight().getRight().getRight().value == 101);
        
        
    }
    
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        testInsertion();
        testFind();
        testDeletion();
        testHeight();
        testLeftRotation();
        testDeletionInsertionBalanced();
        
        System.out.println("Todos os testes concluidos");
    }
}
