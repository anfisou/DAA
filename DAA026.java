import java.util.Scanner;

public class DAA026{

    public static int fill(int i, int j, boolean[][] map, int a, int b){
        if(!map[i][j])return 0;
        int count=1;
        map[i][j]=false;
        if(i>0){
            count+=fill(i-1,j,map,a,b);
            if(j>0)count+=fill(i-1,j-1,map,a,b);
            if(j<b-1)count+=fill(i-1,j+1,map,a,b);
        }
        if(i<a-1){
            count+=fill(i+1,j,map,a,b);
            if(j>0)count+=fill(i+1,j-1,map,a,b);
            if(j<b-1)count+=fill(i+1,j+1,map,a,b);
        }
        if(j>0)count+=fill(i,j-1,map,a,b);
        if(j<b-1)count+=fill(i,j+1,map,a,b);
        return count;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        for(int k=0;k<n;k++){
            int a = in.nextInt();
            int b = in.nextInt();
            boolean[][] map = new boolean[a][b];
            for(int i=0;i<a;i++){
                String s = in.next();
                for(int j=0;j<b;j++){
                    if(s.charAt(j)=='#'){map[i][j]=true;}
                }
            }
            int max = 0;
            for(int i=0;i<a;i++){
                for(int j=0;j<b;j++){
                    if(map[i][j]){max=Math.max(max,fill(i,j,map,a,b));}
                }
            }
            System.out.println(max);
        }
        in.close();
    }
}