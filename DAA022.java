// Codigo inicial para o problema [DAA 022] Arvores Red-Black
// Pedro Ribeiro (DCC/FCUP)

import java.util.Scanner;

// Estrutura para representar um no da arvore
class Node {
    boolean isBlack;  // No preto?
    boolean isNull;   // No nulo?
    int value;        // Valor
    Node left, right; // Filhos

    Node(int v) {
	isNull  = (v==0);
	isBlack = (v>=0);
	value   = Math.abs(v);
    }
}

public class DAA022 {

    // Ler input em preorder
    static Node readPreOrder(Scanner in) {
	int v = in.nextInt();
	Node aux = new Node(v);
	if (v!=0) {
	    aux.left  = readPreOrder(in);
	    aux.right = readPreOrder(in);
	}
	return aux;
    }

    // Menor valor da arvore
    static int minimum(Node t) {
	if (t.isNull) return Integer.MAX_VALUE;
	int minLeft  = minimum(t.left);
	int minRight = minimum(t.right);
	return Math.min(t.value, Math.min(minLeft, minRight));
    }

    // Maior valor da arvore
    static int maximum(Node t) {
	if (t.isNull) return Integer.MIN_VALUE;
	int minLeft  = maximum(t.left);
	int minRight = maximum(t.right);
	return Math.max(t.value, Math.max(minLeft, minRight));
    }

    // Quantidade de nos (internos)
    static int size(Node t) {
	if (t.isNull) return 0;
	return 1 + size(t.left) + size(t.right);
    }

    static public boolean isTree(Node t){
        if(t.isNull){return true;}
        if(maximum(t.left)>t.value || minimum(t.right)<t.value){return false;}
        return isTree(t.left) && isTree(t.right);
    }

    static public boolean blackroot(Node t){
        return t.isBlack;
    }

    static public boolean blackleafs(Node t){
        if (t.isNull){return t.isBlack;}
        return (blackleafs(t.left) && blackleafs(t.right));
    }

    static public boolean redProperty(Node t){
        if(t.isNull){return true;}
        if(!t.isBlack){
            if(!t.left.isBlack || !t.right.isBlack){return false;}
        }
        return redProperty(t.left) && redProperty(t.right);
    }

    static public boolean blackProperty(Node t){
        if (t.isNull){return true;}
        return blackProperty(t.left) && blackProperty(t.right) && countBlackNodes(t.left)==countBlackNodes(t.right);
    }

    static public int countBlackNodes(Node t){
        if(t.isNull){return 0;}
        int count = 0;
        if (t.isBlack){count++;}
        return count+countBlackNodes(t.left);
    }

    // ---------------------------------------------------
    
    public static void main(String args[]) {
	Scanner in = new Scanner(System.in);
	
	int n =  in.nextInt();
	for (int i=0; i<n; i++) {
	    Node root = readPreOrder(in);
	    //System.out.printf("Tree with %d nodes (min=%d, max=%d)\n", size(root), minimum(root), maximum(root));
        if(isTree(root) && blackroot(root) && blackleafs(root) && redProperty(root) && blackProperty(root)){System.out.println("SIM");}
        else{System.out.println("NAO");}
	}	
    }
}
