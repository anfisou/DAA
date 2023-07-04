import java.util.Scanner;
import java.util.Arrays;

public class DAA010 {

    public static void binaria(int q, int[]somas, int a, int b){
        if (a==b){
            if(a==somas.length-1){System.out.println(somas[a]);return;}
            if (q-somas[a]>somas[a+1]-q){System.out.println(somas[a+1]);return;}
            if (q-somas[a]<somas[a+1]-q){System.out.println(somas[a]);return;}
            System.out.println(somas[a]+" "+somas[a+1]);return;
        }
        if (b-a == 1){binaria(q,somas,a,a);return;}
        int m = (a+b)/2;
        if (somas[m]==q){System.out.println(somas[m]);return;}
        if (somas[m]>q){binaria(q,somas,a,m);return;}
        else{binaria(q,somas,m,b);return;}
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] lista = new int[n];
        for(int i = 0;i<n;i++){
            lista[i] = in.nextInt();
        }
        int[] somas = new int[n*(n-1)/2];
        int p = 0;
        for(int i = 0;i<n;i++){
            for(int j=i+1;j<n;j++){
                somas[p] = lista[i] + lista[j];
                p++;
            }
        }
        Arrays.sort(somas);
        int q = in.nextInt();
        for(int i = 0;i<q;i++){
            int qi = in.nextInt();
            binaria(qi, somas,0,n*(n-1)/2);
        }
        in.close();
    }
}
