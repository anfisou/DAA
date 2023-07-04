import java.util.Scanner;

public class DAA019{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int o = in.nextInt();
        for(int i = 0;i<o;i++){
            String c = in.next();
            int n = c.length();
            int[][] minimo = new int[n][n];
            for(int k = 0;k<n;k++){
                minimo[k][k] = 0;
            }
            for(int j = 1;j<n;j++){
                for(int h = 0;j+h<n;h++){
                    if(c.charAt(j+h)==c.charAt(h)){minimo[j+h][h]=minimo[j+h-1][h+1];}
                    else{
                        int min = Math.min(minimo[j+h-1][h],minimo[j+h][h+1]);
                        minimo[j+h][h]=min+1;
                    }
                }
            }
            System.out.println(minimo[n-1][0]);
        }
        in.close();
    }
}
