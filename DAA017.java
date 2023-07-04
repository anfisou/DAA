import java.util.Scanner;


public class DAA017{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        long[][] lista = new long[n][n];
        for(int i = 0;i<n;i++){
            for(int j=0;j<n-i;j++){
                lista[i][j] = 1;
            }
        }
        int d = in.nextInt();
        for(int i = 0;i<d;i++){
            int a = in.nextInt()-1;
            int b = in.nextInt()-1;
            lista[a][b] = 0;
        }
        for(int i = 1;i<n;i++){
            for(int j = 0;j<n-i;j++){
                if(lista[i][j]!=0){
                    lista[i][j] = lista[i-1][j] + lista[i-1][j+1];
                }
            }
        }
        System.out.println(lista[n-1][0]);
        in.close();
    }
}
