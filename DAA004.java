import java.util.Scanner;

public class DAA004 {

    public static long checkdigit(long n, int pos){
        for(int i = 0;i<pos;i++){
            n /= 10;
        }
        return n%10;
    }
    
    public static int somadigitos(long n){
        int count = 0;
        while(n!=0){
            count += n%10;
            n /= 10;
        }
        return count;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for(int i = 0;i<t;i++){
            long n = in.nextLong()+1;
            int k = in.nextInt();
            int falta = k - somadigitos(n);
            if (falta<0){
                int j = 0;
                while(true){
                    if (falta >= 0){break;}
                    long soma = 10-checkdigit(n, j);
                    n += soma * Math.pow(10,j);
                    j++;
                    falta = k - somadigitos(n);
                }
            }
                int j = 0;
            while(true){
                if (falta==0){break;}
                long soma = Math.min(falta,9-checkdigit(n, j));
                n += soma * Math.pow(10,j);
                falta -= soma;
                j++;
            }
            System.out.println(n);
        }
        in.close();
    }
}