import java.util.TreeMap;

class Set{
    int n;
    int[] pai;
    int[] rank;
    int sets;

    Set(int n){
        this.n = n;
        sets = n;
        pai = new int[n];
        rank = new int[n];
        for(int i=0;i<n;i++){
            pai[i] = -1;
            rank[i] = 0;
        }
    }

    public int find(int x){
        if(pai[x]==-1)return x;
        pai[x] = find(pai[x]);
        return pai[x];
    }

    public void union(int a, int b){
        int paia = find(a);
        int paib = find(b);
        if(paia==paib){return;}
        sets--;
        if (rank[paia]>rank[paib]){pai[paib]=paia;return;}
        if (rank[paib]>rank[paia]){pai[paia]=paib;return;}
        pai[paia]=paib;
        rank[paib]++;
        return;
    }



}

public class DAA039 {
    public static void main(String args[]){
        FastScanner in = new FastScanner(System.in);
        int n = in.nextInt();
        Integer p = in.nextInt();
        Set s = new Set(p);
        TreeMap<String,Integer> nomes = new TreeMap<>();
        for(int i=0;i<n;i++){
            int a = in.nextInt();
            for(int j=0;j<a;j++){
                String st = in.next();
                nomes.put(st,p-1);
                p--;
                if(j>0){
                    int k = nomes.get(st);
                    s.union(k+1,k);
                }
            }
        }
        int t = in.nextInt();
        for(int i=0;i<t;i++){
            String st = in.next();
            String f = in.next();
            String se = in.next();
            if(st.equals("P")){
                if(s.find(nomes.get(f))==s.find(nomes.get(se))){
                    System.out.println("sim");
                }
                else{System.out.println("nao");}
            }
            else{
                s.union(nomes.get(f), nomes.get(se));
                System.out.println(s.sets);
            }
        }
    }
}
