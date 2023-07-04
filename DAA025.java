import java.util.Scanner;


public class DAA025{
    static void dfs(int v, boolean[] visited, boolean[][] adj,int n) {
        //System.out.print(v + " ");
        visited[v] = true;
        for (int i=1; i<=n; i++)
            if (adj[v][i] && !visited[i])
            dfs(i,visited,adj,n);
        }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
	    boolean[][] adj     = new boolean[n+1][n+1];
	    boolean[] visited = new boolean[n+1];	
	    int edges = in.nextInt();	
	    for (int i=0; i<edges; i++) {
	        int a = in.nextInt();
	        int b = in.nextInt();
	        adj[a][b] = adj[b][a] = true;
	    }
        int count=0;
        for(int i=1;i<n+1;i++){
            if(!visited[i]){
                dfs(i,visited,adj,n);
                count++;
            }
        }
        System.out.println(count);
        in.close();
    }
}