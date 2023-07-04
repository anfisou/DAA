import java.util.Scanner;

public class DAA015 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] lista = new int[n];
        boolean[] popped = new boolean[n];
        for(int i = 0;i<n;i++){
            lista[i] = in.nextInt();
        }
        int pop = 0;
        int flechas=0;
        while(pop<n){
            flechas++;
            int j;
            int temp=0;
            for(j = 0;j<n;j++){
                if(!popped[j]){temp=lista[j]-1;popped[j]=true;pop++;break;}
            }
            for(int i = j+1;i<n;i++){
                if(lista[i]==temp && !popped[i]){temp--;popped[i]=true;pop++;}
            }
        }
        System.out.println(flechas);
        in.close();
    }
}
