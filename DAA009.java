import java.util.Scanner;
import java.util.Arrays;

class Valor implements Comparable<Valor>{
    char c;
    Integer num;
    Integer ordem;

    Valor(char ch){
        c = ch;
        num = 0;
        ordem = 0;
    }

    public int compareTo(Valor v){
        if (num.equals(v.num)){return ordem.compareTo(v.ordem);}
        return v.num.compareTo(num);
    }
}

public class DAA009{

    public static int find(char s, char[] lista){
        for(int i = 0;i<lista.length;i++){
            if(s == lista[i]){return i;}
        }
        return 0;
    } 
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String s= in.next();
        int o = 1;
        Valor[] lista = new Valor[26];
        char[] l = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
        for(int i = 0;i<26;i++){
            lista[i] = new Valor(l[i]);
        }
        for(int i = 0;i<s.length();i++){
            lista[find(s.charAt(i),l)].num++;
            if (lista[find(s.charAt(i),l)].num==1){
                lista[find(s.charAt(i),l)].ordem = o;
                o++;
            }
        }
        Arrays.sort(lista);
        for(int i = 0;i<o-1;i++){
            System.out.println(lista[i].c + " " + lista[i].num);
        }
        in.close();
    }
}