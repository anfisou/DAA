import java.util.Scanner;
import java.util.Arrays;


class Encomenda implements Comparable<Encomenda>{
    Double racio;
    Integer ordem;

    Encomenda(Double r, int o){
        racio = r;
        ordem = o;
    }

    public int compareTo(Encomenda s){
        if (racio.equals(s.racio)){return ordem.compareTo(s.ordem);}
        return (s.racio).compareTo(racio);
    }
}


public class DAA014 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Encomenda[] lista= new Encomenda[n];
        for(int i = 0;i<n;i++){
            double p = in.nextDouble();
            double m = in.nextDouble();
            lista[i] = new Encomenda(m/p, i+1);
        }
        Arrays.sort(lista);
        for(int i = 0;i<n;i++){
            System.out.print(lista[i].ordem);
            if(i<n-1){System.out.print(" ");}
        }
        System.out.println();
        in.close();
    }
}
