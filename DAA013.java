import java.util.Scanner;
import java.util.Arrays;

class Segmento implements Comparable<Segmento>{
    Integer inicio;
    int fim;

    Segmento(int i, int f){
        inicio=i;
        fim = f;
    }

    public int compareTo(Segmento s){
        return inicio.compareTo(s.inicio);
    }
}
public class DAA013 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int m = in.nextInt();
        int n = in.nextInt();
        Segmento[] lista = new Segmento[n];
        for(int i = 0;i<n;i++){
            int ini = in.nextInt();
            int f = in.nextInt();
            lista[i]= new Segmento(ini, f);
        }
        Arrays.sort(lista);
        int end = 0;
        int usados = 0;
        while(end<m){
            int max = 0;
            for(int i = 0;i<n && lista[i].inicio<=end;i++){
                if(lista[i].fim>max){max=lista[i].fim;}
            }
            end = max;
            usados++;
        }
        System.out.println(usados);
        in.close();
    }
}
