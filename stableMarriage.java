// Match women with the uncommitted man of highest possible priority
import java.util.*;

public class stableMarriage {
    public static void main(String[] args) {
        int T, N, person;
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        for(int i = 0; i<T; i++) {
        N = sc.nextInt();

        int [] womensPartner = new int[N];
        int [] mensPartner = new int[N];
        Arrays.fill(womensPartner,-1);
        Arrays.fill(mensPartner,-1);
        Queue<Integer> singleMen = new LinkedList<Integer>();
        for(int j = 0; j<N; j++) {
            singleMen.offer(j);
        }
        Queue<Integer>[] menPreferences = new LinkedList[N];
        for (int j = 0; j < N; j++) {
            menPreferences[j] = new LinkedList<>();
        }
        int[][] womenRanks = new int[N][N];
        for (int j = 0; j < N; j++) {
            sc.nextInt(); 
            for (int rank = 0; rank < N; rank++) {
                int man = sc.nextInt() - 1; 
                womenRanks[j][man] = rank;
            }
        }
        for (int j = 0; j < N; j++) {
            sc.nextInt(); 
            for (int k = 0; k < N; k++) {
                int woman = sc.nextInt() - 1; 
                menPreferences[j].offer(woman);
            }
        }


        while(!singleMen.isEmpty()) {
            int man = singleMen.poll();
            int woman = menPreferences[man].poll();

            int currentpartner = womensPartner[woman];
            if(currentpartner == -1) {
                womensPartner[woman] = man;
                mensPartner[man] = woman; 
            }
            else {
                if(womenRanks[woman][man] < womenRanks[woman][currentpartner]) {
                    mensPartner[currentpartner] = -1;
                    singleMen.offer(currentpartner);
                    womensPartner[woman] = man;
                    mensPartner[man] = woman; 

                }
                else{
                    singleMen.offer(man);
                }
            }
        }
        for (int j = 0; j < N; j++) {
                System.out.println((j + 1) + " " + (mensPartner[j] + 1));
            }
    }
}
}
