import java.util.Scanner;
import java.util.LinkedList;

public class DAA029 {

    public static void dfs(int no, boolean[][] adj, boolean[] visited, LinkedList<Character> ordem){
        visited[no] = true;
        for(int i=0;i<26;i++){
            if(adj[no][i] && !visited[i]){
                dfs(i,adj,visited,ordem);
            }
        }
        ordem.addFirst(Character.toString((char)no+'A').charAt(0));
    }

    public static boolean is_inicio(int n, boolean[][] adj){
        for(int i=0;i<26;i++){
            if(adj[i][n]){return false;}
        }
        for(int i=0;i<26;i++){
            if(adj[n][i]){return true;}
        }
        return false;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        boolean[][] adj = new boolean[26][26];
        String prev = in.next();
        for(int i=1;i<n;i++){
            String next = in.next();
            for(int j=0;j<prev.length() && j<next.length();j++){
                if(prev.charAt(j)!=next.charAt(j)){
                    adj[(int)prev.charAt(j)-'A'][(int)next.charAt(j)-'A'] = true;
                    break;
                }
            }
            prev = next;
        }
        boolean[] visited = new boolean[26];
        LinkedList<Character> ordem = new LinkedList<>();
        int inicio = -1;
        for(int i=0;i<26;i++){
            if(is_inicio(i,adj)){inicio = i;}
        }
        dfs(inicio,adj,visited,ordem);
        for (Character c : ordem){
            System.out.print(c);
        }
        System.out.println();
        in.close();
    }
}
