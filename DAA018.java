import java.util.Scanner;

public class DAA018{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] moedas = new int[n];
        for(int i = 0;i<n;i++){
            moedas[i] = in.nextInt();
        }
        int p = in.nextInt();
        int[] perguntas = new int[p];
        int max = 0;
        for(int i = 0;i<p;i++){
            perguntas[i] = in.nextInt();
            if(perguntas[i]>max){max=perguntas[i];}
        }
        int[] lista = new int[max+1];
        for(int i=0;i<n;i++){
            lista[moedas[i]] = 1;
        }
        for(int i = moedas[0]+1;i<=max;i++){
            if(lista[i]!=1){lista[i] = lista[i-moedas[0]]+1;}
            for(int j=1;moedas[j]<i && j<n;j++){
                if(lista[i-moedas[j]]+1<lista[i]){
                    lista[i] = lista[i-moedas[j]]+1;
                }
                if(j==n-1){break;}
            }
        }
        for(int i=0;i<p;i++){
            System.out.print(perguntas[i]+": ["+lista[perguntas[i]]+"]");
            int k = perguntas[i];
            while(k!=0){
                for(int j=0;j<n;j++){
                    if(lista[k]==lista[k-moedas[j]]+1){k-=moedas[j];System.out.print(" "+moedas[j]);break;}
                }
            }
            System.out.println();
        }
        in.close();
    }
}