// Exemplo de implementacao de algoritmo de Edmonds-Karp
// usando lista de adjacencias + matriz de capacidades
// (implementacao com vector)

import java.util.*;

// Classe para no da fila do BFS: pares (no, capacidade)
class NodeQ {
    public int node;
    public int flow;

    NodeQ(int n, int f) {
        node = n;
        flow = f;
    }
}

// Classe que representa um grafo
class Graph1 {
    int n;                       // Numero de nos do grafo
    Vector<Vector<Integer>> adj; // Lista de adjacencias
    int cap[][];                 // Matriz de capacidades

    Graph1(int n) {
        this.n = n;
        adj = new Vector<>();
        for (int i=0; i<=n; i++) adj.add(new Vector<Integer>());
        cap = new int[n+1][n+1]; // +1 se os nos comecam em 1 ao inves de 0
    }
  
    void addLink(int a, int b, int c) {
        // adjacencias do grafo nao dirigido, porque podemos ter de andar no sentido
        // contrario ao procurarmos caminhos de aumento
        adj.get(a).add(b);
        adj.get(b).add(a);
        cap[a][b] = c;
    }
    
    // BFS para encontrar caminho de aumento
    // devolve valor do fluxo nesse caminho
    int bfs(int s, int t, int[] parent) {
        for (int i=1; i<=n; i++) parent[i] = -1;

        parent[s] = -2;         
        Queue<NodeQ> q = new LinkedList<>(); // fila do BFS
        // inicializar com no origem e capacidade infinita
        q.add(new NodeQ(s, Integer.MAX_VALUE));

        while (!q.isEmpty()) {
            // returar primeiro no da fila
            int cur = q.peek().node;
            int flow = q.peek().flow;
            q.poll();

            // percorrer nos adjacentes ao no atual (cur)
            for (int next : adj.get(cur)) {
                // se o vizinho ainda nao foi visitado (parent==-1)
                // e a aresta respetiva ainda tem capacidade para passar fluxo
                if (parent[next] == -1 && cap[cur][next]>0) {
                    parent[next] = cur;                        // atualizar pai
                    int new_flow = Math.min(flow, cap[cur][next]); // atualizar fluxo
                    if (next == t) return new_flow;            // chegamos ao final?
                    q.add(new NodeQ(next, new_flow));          // adicionar a fila
                }
            }
        }
    
        return 0;
    }

    // Algoritmo de Edmonds-Karp para fluxo maximo entre s e t
    // devolve valor do fluxo maximo (cap[][] fica com grafo residual)
    int maxFlow(int s, int t) {
        int flow = 0;                // fluxo a calcular
        int[] parent = new int[n+1]; // array de pais (permite reconstruir caminho)
    
        while (true) {
            int new_flow = bfs(s, t, parent); // fluxo de um caminho de aumento
            if (new_flow == 0) break;         // se nao existir, terminar

            // imprimir fluxo e caminho de aumento
            //System.out.print("Caminho de aumento: fluxo " + new_flow + " | " + t);
	    
            flow += new_flow;  // aumentar fluxo total com fluxo deste caminho
            int cur = t;
            while (cur != s) { // percorrer caminho de aumento e alterar arestas
                int prev = parent[cur];	
                cap[prev][cur] -= 1;
                cap[cur][prev] -= 1;
                cur = prev;
                //System.out.print(" <- " + cur); // imprimir proximo no do caminho
            }
            //System.out.println();
        }
	
        return flow;
    }       
}


public class DAA041{
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        int n = in.nextInt();
        Graph1 g = new Graph1(n);
        int e = in.nextInt();
        for (int i=0; i<e; i++) {
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            g.addLink(a, b, 1);
            g.addLink(b, a, 1);
        }

        // Execucao exemplo usando 1 como no origem a 4 como o destino
        int flow = g.maxFlow(0, n-1);
        System.out.println(flow);
        in.close();
    }    
}