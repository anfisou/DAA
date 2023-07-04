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

    public int prim(int s, int a){
        for(int v=0;v<n;v++){
            nodes[v].distance=Integer.MAX_VALUE;
            nodes[v].visited = false;
        }
        for(int v=0;v<a;v++){
            for(Edge e:nodes[v].adj){
                if(e.weight<nodes[e.to].distance){nodes[e.to].distance=e.weight;}
            }   
        }
        nodes[s].distance=0;
        TreeSet<NodeQ> tree = new TreeSet<>();
        tree.add(new NodeQ(0, s));
        for(int v =a;v<n;v++){tree.add(new NodeQ(nodes[v].distance,v));}
        int totaldistance = 0;
        while(!tree.isEmpty()){
            NodeQ u = tree.pollFirst();
            int n = u.node;
            if(n>=a){totaldistance+= nodes[n].distance;}
            nodes[n].visited=true;
            for(Edge e:nodes[n].adj){
                if(!nodes[e.to].visited && e.weight<nodes[e.to].distance){
                    tree.remove(new NodeQ(nodes[e.to].distance,e.to));
                    nodes[e.to].distance=e.weight;
                    tree.add(new NodeQ(e.weight, e.to));
                }
            }
        }
        return totaldistance;
    }
}

public class DAA038{
    public static void main(String args[]){
        FastScanner in = new FastScanner(System.in);
        int a = in.nextInt();
        int b = in.nextInt();
        int c = in.nextInt();
        Graph g = new Graph(a+b);
        for(int i=0;i<c;i++){
            int d = in.nextInt()-1;
            int e = in.nextInt()-1;
            int f = in.nextInt();
            g.addLink(d, e, f);
            g.addLink(e, d, f);
        }
        System.out.println(g.prim(0,a));
        int[] distancias = new int[b];
        for(int v=a;v<a+b;v++){
            distancias[v-a]=g.nodes[v].distance;
        }
        Arrays.sort(distancias);
        String s="";
        for(int i=0;i<distancias.length;i++){s+=distancias[i]+" ";}
        s = s.substring(0, s.length()-1);
        System.out.println(s);
    }
}
