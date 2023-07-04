import java.util.Scanner;

public class DAA027 {

    static boolean dfs(int v, boolean[] visited, boolean[][] adj,int n, int[] color, int cor) {
        //System.out.print(v + " ");
        visited[v] = true;
        color[v] = cor;
        for (int i=1; i<=n; i++)
            if(adj[v][i]){
                if (!visited[i]){if (!dfs(i,visited,adj,n,color,3-cor)) return false;}
                else{if(color[v]==color[i])return false;}
            }
        return true;
    }
        

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int c = in.nextInt();
        for(int k=0;k<c;k++){
            int n = in.nextInt();
	        boolean[][] adj     = new boolean[n+1][n+1];
	        int[] color = new int[n+1];	
            boolean[] visited = new boolean[n+1];
	        int edges = in.nextInt();	
	        for (int i=0; i<edges; i++) {
	            int a = in.nextInt();
	            int b = in.nextInt();
	            adj[a][b] = adj[b][a] = true;
	        }
            if(dfs(1,visited,adj,n,color,1)){System.out.println("sim");}
            else{System.out.println("nao");}
        }
        in.close();
    }
}
