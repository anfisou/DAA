import java.util.TreeMap;

public class DAA023{
    public static void coloca(String s, int tamanho, int posicao, TreeMap<String,Integer> map){
        if (tamanho==0){return;}
        if (posicao==25){return;}
        for(int i=posicao+1;i<26;i++){
            String nova = s + ((char)('a'+i));
            if(tamanho==1){map.put(nova,map.size()+1);}
            coloca(nova, tamanho-1,i, map);
        }
    }
    public static void main(String[] args){
        FastScanner in = new FastScanner(System.in);
        TreeMap<String,Integer> map = new TreeMap<>();
        for(int i=1;i<6;i++){
            coloca("",i,-1,map);
        }
        int n = in.nextInt();
        for(int i=0;i<n;i++){
            String s = in.next();
            if(!map.containsKey(s)){FastPrint.out.println(0);}
            else{FastPrint.out.println(map.get(s));}
        }
        FastPrint.out.close();
    }
}
