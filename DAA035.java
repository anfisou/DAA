import java.util.*;

class Edge {
    int to;     // No destino
    int weight; // Peso da aresta
    
    Edge(int t, int w) {
        to = t;
        weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public int distance;         // Distancia ao no origem da pesquisa
    
    Node() {
        adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public int cost;
    public int node;

    NodeQ(int c, int n) {
        cost = c;
        node = n;
    }

    @Override
    public int compareTo(NodeQ nq) { 
        if (cost < nq.cost) return -1; 
        if (cost > nq.cost) return +1;
        if (node < nq.node) return -1; 
        if (node > nq.node) return +1;
        return 0; 
    } 
}

// Classe que representa um grafo
class Graph {
    int n;          // Numero de nos do grafo
    Node[] nodes;   // Array para conter os nos
    
    Graph(int n) {
        this.n = n;
        nodes = new Node[n];  // +1 se os nos comecam em 1 ao inves de 0
        for (int i=0; i<n; i++)
            nodes[i] = new Node();
    }
    
    void addLink(int a, int b, int c) {
        nodes[a].adj.add(new Edge(b, c));
    }

    // Algoritmo de Dijkstra
    void dijkstra(int s) {
	
        //Inicializar nos como nao visitados e com distancia infinita
        for (int i=0; i<n; i++) {
            nodes[i].distance = Integer.MAX_VALUE;
            nodes[i].visited  = false;
        }
	
        // Inicializar "fila" com no origem
        nodes[s].distance = 0;
        TreeSet<NodeQ> q = new TreeSet<>();
        q.add(new NodeQ(0, s)); // Criar um par (dist=0, no=s)

        // Ciclo principal do Dijkstra
        while (!q.isEmpty()) {
      
            // Retirar no com menor distancia (o "primeiro" do set, que e uma BST)
            NodeQ nq = q.pollFirst();
            int  u = nq.node;
            nodes[u].visited = true;
            //System.out.println(u + " [dist=" + nodes[u].distance + "]");
	    
            // Relaxar arestas do no retirado
            for (Edge e : nodes[u].adj) {
                int v = e.to;
                int cost = e.weight;
                if (!nodes[v].visited && nodes[u].distance + cost < nodes[v].distance) {
                    q.remove(new NodeQ(nodes[v].distance, v)); // Apagar do set
                    nodes[v].distance = nodes[u].distance + cost;
                    q.add(new NodeQ(nodes[v].distance, v));    // Inserir com nova (e menor) distancia
                }
            }
        }
    }

    public void bellman_ford(int s){
        for (int i=0; i<n; i++) {
            nodes[i].distance = Integer.MAX_VALUE/2;
        }
        nodes[s].distance=0;
        for(int i=0;i<nodes.length-1;i++){
            for(int u=0;u<nodes.length;u++){
                for (Edge e : nodes[u].adj){
                    int v = e.to;
                    int cost = e.weight;
                    if(nodes[u].distance + cost < nodes[v].distance){nodes[v].distance = nodes[u].distance + cost;}
                }
            }
        }
    }

    public boolean has_negative_cycle(){
        for(int u=0;u<nodes.length;u++){
            for (Edge e : nodes[u].adj){
                int v = e.to;
                int cost = e.weight;
                if(nodes[u].distance + cost < nodes[v].distance){return true;}
            }
        }
        return false;
    }
}

public class DAA035 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[][] connected = new boolean[n][n];
        for(int j=0;j<n;j++){
            connected[j][j] = true;
            in.next();
            int k = in.nextInt();
            for(int i=0;i<k;i++){
                String c = in.next();
                connected[j][(int)c.charAt(0)-'A'] = true;
            }
        }
        for(int k=0;k<n;k++){
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(connected[i][k] && connected[k][j]){connected[i][j]=true;}
                }
            }
        }
        System.out.print(" ");
        for(int i=0;i<n;i++){
            System.out.print(" "+Character.toString((char)i+'A').charAt(0));
        }
        System.out.println();
        for(int i=0;i<n;i++){
            System.out.print(Character.toString((char)i+'A').charAt(0));
            for(int j=0;j<n;j++){
                if(connected[i][j]){System.out.print(" 1");}
                else{System.out.print(" 0");}
            }
            System.out.println();
        }
        in.close();
    }
}

