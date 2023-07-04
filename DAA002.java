import java.util.Scanner;

public class DAA002 {
    
    public static int somadigitos(int n){
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
            int n = in.nextInt();
            int k = in.nextInt();
            while(true){
                n++;
                if (somadigitos(n) == k){break;}
            }
            System.out.println(n);
        }
        in.close();
    }
}
