import java.util.Scanner;
import java.util.LinkedList;

public class DAA031 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        int l = in.nextInt();
        int c = in.nextInt();
        int[][] map = new int[l][c];
        LinkedList<Integer> q = new LinkedList<>();
        int count = 0;
        for(int i=0;i<l;i++){
            String s = in.next();
            for(int j=0;j<c;j++){
                if(s.charAt(j)=='#'){map[i][j]=1;q.add(i*c+j);}
                if(s.charAt(j)=='A'){map[i][j]=2;count++;}
            }
        }
        int a = count;
        int amin = 10000;
        int amax = 0;
        for(int i=1;a!=0;i++){
            int s = q.size();
            for(int j=0;j<s;j++){
                int k = q.removeFirst();
                int ltemp = k/c;
                int ctemp = k%c;
                if(ltemp>0){if(map[ltemp-1][ctemp]!=1){q.add((ltemp-1)*c+ctemp);if(map[ltemp-1][ctemp]==2){a--;}map[ltemp-1][ctemp]=1;}}
                if(ltemp<l-1){if(map[ltemp+1][ctemp]!=1){q.add((ltemp+1)*c+ctemp);if(map[ltemp+1][ctemp]==2){a--;}map[ltemp+1][ctemp]=1;}}
                if(ctemp>0){if(map[ltemp][ctemp-1]!=1){q.add(ltemp*c+ctemp-1);if(map[ltemp][ctemp-1]==2){a--;}map[ltemp][ctemp-1]=1;}}
                if(ctemp<c-1){if(map[ltemp][ctemp+1]!=1){q.add(ltemp*c+ctemp+1);if(map[ltemp][ctemp+1]==2){a--;}map[ltemp][ctemp+1]=1;}}
                if(a!=count && i<amin){amin=i;}
            }
            amax=i;
        }
        System.out.println(amin+" "+amax);
        in.close();
    }
}
