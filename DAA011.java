import java.util.Scanner;

public class DAA011 {
    public static int soma(int[] l){
        int sum = 0;
        for(int i = 0;i<l.length;i++){
            sum+=l[i];
        }
        return sum;
    }
    public static void indireta(int q, int[] lista){
        int a = 1;
        int b = soma(lista);
        while(a!=b){
            if (a==b-1){if(tenta(q,a,lista)){b=a;}
                        else{a=b;}
                        break;}
            int m = (a+b)/2;
            if(tenta(q,m,lista)){b=m;}
            else{a=m;}
    }
        System.out.println(a);
    }

    public static boolean tenta(int q, int max,int[] lista){
        int sum = 0;
        for(int i = 0;i<lista.length;i++){
            if (lista[i]>max){return false;}
            if (sum+lista[i]>max){sum = lista[i];q--;}
            else{sum+=lista[i];}
            if (q<0){return false;}
        }
        return true;
    }
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] lista = new int[n];
        for(int i = 0;i<n;i++){
            lista[i] = in.nextInt();
        }
        int p = in.nextInt();
        for(int i = 0;i<p;i++){
            int q = in.nextInt();
            indireta(q-1,lista);
        }
        in.close();
    }
}
