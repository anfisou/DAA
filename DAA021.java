import java.util.Scanner;
import java.util.TreeMap;

public class DAA021{

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int a = in.nextInt();
        int r = in.nextInt();
        TreeMap<Integer,Integer> map = new TreeMap<>();
        for(int i=0;i<a+r;i++){
            String o = in.next();
            if (o.equals("BAK")){
                int p = in.nextInt();
                if(map.containsKey(p)){
                    map.put(p,map.remove(p)+1);
                }
                else{
                    map.put(p,1);
                }
            }
            else if(o.equals("MIN")){
                System.out.println(map.firstKey());
                if(map.get(map.firstKey()).equals(1)){
                    map.remove(map.firstKey());
                }
                else{
                    map.put(map.firstKey(),map.remove(map.firstKey())-1);
                }
            }
            else{
                System.out.println(map.lastKey());
                if(map.get(map.lastKey()).equals(1)){
                    map.remove(map.lastKey());
                }
                else{
                    map.put(map.lastKey(),map.remove(map.lastKey())-1);
                }
            }
        }
        in.close();
    }
}