import java.util.Scanner;

public class DAA003 {
    
    public static int find(char s, char[] lista){
        for(int i = 0;i<lista.length;i++){
            if(s == lista[i]){return i;}
        }
        return 0;
    } 
    public static int id(String s, char[] lista){
        if (Character.isLetter(s.charAt(0)) && Character.isDigit(s.charAt(6))){ //XX-NN-NN
            int t = find(s.charAt(0), lista) * 23 + find(s.charAt(1),lista);
            return t * 10000 + Character.getNumericValue(s.charAt(3)) * 1000 + Character.getNumericValue(s.charAt(4)) * 100 + Character.getNumericValue(s.charAt(6)) * 10 + Character.getNumericValue(s.charAt(7));
        }
        if (Character.isLetter(s.charAt(6)) && Character.isDigit(s.charAt(0))){ //NN-NN-XX
            int t = find(s.charAt(6), lista) * 23 + find(s.charAt(7),lista);
            return id("ZZ-99-99",lista) +  t * 10000 + Character.getNumericValue(s.charAt(0)) * 1000 + Character.getNumericValue(s.charAt(1)) * 100 + Character.getNumericValue(s.charAt(3)) * 10 + Character.getNumericValue(s.charAt(4)) + 1;
        }
        if (Character.isLetter(s.charAt(3))){ //NN-XX-NN
            int t = find(s.charAt(3), lista) * 23 + find(s.charAt(4),lista);
            return id("99-99-ZZ",lista) +  t * 10000 + Character.getNumericValue(s.charAt(0)) * 1000 + Character.getNumericValue(s.charAt(1)) * 100 + Character.getNumericValue(s.charAt(6)) * 10 + Character.getNumericValue(s.charAt(7)) + 1;
        }
        else{ // XX-NN-XX
            int t = find(s.charAt(0), lista) * 23*23*23 + find(s.charAt(1),lista)*23*23 + find(s.charAt(6), lista) * 23 + find(s.charAt(7),lista);
            return id("99-ZZ-99",lista) + t * 100 + Character.getNumericValue(s.charAt(3)) * 10 + Character.getNumericValue(s.charAt(4)) + 1;
        }
}

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        char[] lista = {'A','B','C','D','E','F','G','H','I','J','L','M','N','O','P','Q','R','S','T','U','V','X','Z'};
        for(int i = 0;i<t;i++){
            String a = in.next();
            String b = in.next();
            System.out.println(Math.abs(id(a,lista)-id(b,lista)));
        }
        in.close();
    }
}