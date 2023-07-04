import java.util.*;

class Edge {
    int to;     // No destino
    Double weight; // Peso da aresta
    
    Edge(int t, Double w) {
        to = t;
        weight = w;
    }
}

// Classe que representa um no
class Node {
    public LinkedList<Edge> adj; // Lista de adjacencias
    public boolean visited;      // No ja foi visitado?
    public Double distance;         // Distancia ao no origem da pesquisa
    
    Node() {
        adj = new LinkedList<>();
    } 
};

// Classe que representa um no para ficar na fila de prioridade
class NodeQ implements Comparable<NodeQ> {
    public Double cost;
    public int node;

    NodeQ(Double c, int n) {
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
    
    void addLink(int a, int b, Double c) {
        nodes[a].adj.add(new Edge(b, c));
    }

    // Algoritmo de Dijkstra
    void dijkstra(int s) {
	
        //Inicializar nos como nao visitados e com distancia infinita
        for (int i=0; i<n; i++) {
            nodes[i].distance = Double.MAX_VALUE;
            nodes[i].visited  = false;
        }
	
        // Inicializar "fila" com no origem
        nodes[s].distance = 0.0;
        TreeSet<NodeQ> q = new TreeSet<>();
        q.add(new NodeQ(0.0, s)); // Criar um par (dist=0, no=s)

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
                Double cost = e.weight;
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
            nodes[i].distance = Double.MAX_VALUE/2;
        }
        nodes[s].distance=0.0;
        for(int i=0;i<nodes.length-1;i++){
            for(int u=0;u<nodes.length;u++){
                for (Edge e : nodes[u].adj){
                    int v = e.to;
                    Double cost = e.weight;
                    if(nodes[u].distance + cost < nodes[v].distance){nodes[v].distance = nodes[u].distance + cost;}
                }
            }
        }
    }

    public boolean has_negative_cycle(){
        for(int u=0;u<nodes.length;u++){
            for (Edge e : nodes[u].adj){
                int v = e.to;
                Double cost = e.weight;
                if(nodes[u].distance + cost < nodes[v].distance){return true;}
            }
        }
        return false;
    }

    public double prim(int s){
        for(int v=0;v<n;v++){
            nodes[v].distance=Double.MAX_VALUE;
            nodes[v].visited = false;
        }
        nodes[s].distance=0.0;
        TreeSet<NodeQ> tree = new TreeSet<>();
        tree.add(new NodeQ(0.0, s));
        Double totaldistance = 0.0;
        while(!tree.isEmpty()){
            NodeQ u = tree.pollFirst();
            int n = u.node;
            totaldistance+= nodes[n].distance;
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

public class DAA037{
    public static void main(String args[]){
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        Graph g = new Graph(n);
        int[] points = new int[2*n]; 
        for(int i=0;i<n;i++){
            points[2*i] = in.nextInt();
            points[2*i+1] = in.nextInt();
        }
        for(int i=0;i<n;i++){
            for(int j=0;j<i;j++){
                Double distance = Math.sqrt((points[2*i]-points[2*j])*(points[2*i]-points[2*j])+ (points[2*i+1]-points[2*j+1])*(points[2*i+1]-points[2*j+1]));
                g.addLink(i, j, distance);
                g.addLink(j, i, distance);
            }
        }
        System.out.println(Math.round(g.prim(0)*1000)/1000.0);
    }
}