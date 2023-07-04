import java.util.Scanner;
import java.util.LinkedList;

class Node {
    public LinkedList<Integer> adj; // Lista de adjacencias
    public boolean visited;         // Valor booleano que indica se foi visitado numa pesquisa
    public int distance;            // Distancia ao no origem da pesquisa

    Node() {
        adj = new LinkedList<Integer>();
    }
}

// Classe que representa um grafo
class Graph {
    int n;           // Numero de nos do grafo
    Node nodes[];    // Array para conter os nos

    Graph(int n) {
        this.n = n;
        nodes  = new Node[n+1]; // +1 se nos comecam em 1 ao inves de 0
        for (int i=1; i<=n; i++)
            nodes[i] = new Node();
    }

    public void addLink(int a, int b) {
        nodes[a].adj.add(b);
        nodes[b].adj.add(a);
    }

    // Algoritmo de pesquisa em largura
    public void bfs(int v) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int i=1; i<=n; i++) nodes[i].visited = false;

        q.add(v);
        nodes[v].visited = true;
        nodes[v].distance = 0;

        while (q.size() > 0) {
            int u = q.removeFirst();
            //System.out.println(u + " [dist=" + nodes[u].distance + "]");
            for (int w : nodes[u].adj)
                if (!nodes[w].visited) {
                    q.add(w);
                    nodes[w].visited  = true;
                    nodes[w].distance = nodes[u].distance + 1; 
                }	    
        }
    }
}

public class DAA030 {

    public static void dijkstra(int no, int[][] dist, boolean[][] adj, boolean[] visited){
        for(int i=1;i<visited.length;i++){
            dist[no][i] = 10000;
            visited[i] = false;
        }
        dist[no][no] = 0;
        for(int i=0;i<visited.length-1;i++){
            int min = 100000;
            int min_no = -1;
            for(int j=1;j<visited.length;j++){
                if(!visited[j] && dist[no][j]<min){min=dist[no][j];min_no=j;}
            }
            visited[min_no]=true;
            for(int j=1;j<visited.length;j++){
                if(adj[min_no][j] && !visited[j] && dist[no][min_no]+1<dist[no][j]){dist[no][j]=dist[no][min_no]+1;}
            }
        }
        return;
    }

    public static void bfs(int no, int[][] dist, boolean[] visited, boolean[][] adj){
        LinkedList<Integer> q = new LinkedList<Integer>();
        for(int i=1;i<visited.length;i++){
            dist[no][i] = 10000;
            visited[i] = false;
        }
        q.add(no);
        dist[no][no] = 0;
        visited[no] = true;
        while (q.size() > 0) {
            int u = q.removeFirst();
            for (int i=1;i<visited.length;i++){
                if (!visited[i] && adj[u][i]) {
                    q.add(i);
                    visited[i]  = true;
                    dist[no][i] = dist[no][u] + 1; 
                }	    
            }
        }
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Graph g = new Graph(n);
        int   e = in.nextInt();
        for (int i=0; i<e; i++) 
            g.addLink(in.nextInt(), in.nextInt());	
        int[][] dist = new int[n+1][n+1];
        for(int k=1;k<=n;k++){
            g.bfs(k);
            for(int i=1;i<n+1;i++){
                dist[k][i] = g.nodes[i].distance;
            }
        }
        int diametro = -1;
        int raio = 1000000;
        for(int i=1;i<n+1;i++){
            int exc = 0;
            for(int j=1;j<n+1;j++){
                if(dist[i][j]>diametro){diametro=dist[i][j];}
                if(dist[i][j]>exc){exc = dist[i][j];}
            }
            if(exc<raio){raio=exc;}
        }
        System.out.println(diametro);
        System.out.println(raio);
        String s = "";
        for(int i=1;i<n+1;i++){
            int exc = 0;
            for(int j=1;j<n+1;j++){
                if(dist[i][j]>exc){exc = dist[i][j];}
            }
            if(exc==raio){s+=i+" ";}
        }
        System.out.println(s.substring(0, s.length()-1));
        s = "";
        for(int i=1;i<n+1;i++){
            int max = 0;
            for(int j=1;j<n+1;j++){
                if(dist[i][j]>max){max=dist[i][j];}
            }
            if(max==diametro){s+=i+" ";}
        }
        System.out.println(s.substring(0, s.length()-1));
        in.close();        
    }


}
