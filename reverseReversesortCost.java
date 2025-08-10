// Given size n and cost c, find n distinct integers between 1 and n such that cost is exactly c
import java.io.*;
import java.util.*;
class reverseReversesortCost {
    static LinkedList <Integer> construct(int N, int C, int m) {              //Recursively builds the permutation by deciding position of minimum elememnt m
        if(N == 1) {
            LinkedList<Integer> base = new LinkedList<>();
            base.add(m);
            return base;
        }
        if (C - 1 >= N - 2 && C - 1 <= (N * (N - 1)) / 2 - 1) {             // If 1 cost is spent in getting m to the front, is the remaining cost atleast 
            LinkedList<Integer> arr = construct(N - 1, C - 1, m + 1);
            arr.addFirst(m);
            return arr;
        }

        int x = C - (N*(N+1)/2)+1;
        LinkedList<Integer> arr = construct(N-1, C-x, m+1);
        
        List<Integer> prefix = new ArrayList<>();
        Iterator<Integer> it = arr.iterator();
        for (int i = 0; i < x - 1 && it.hasNext(); i++) {
            prefix.add(it.next());
        }
        Collections.reverse(prefix);

        // Replace in arr with reversed prefix
        for (int i = 0; i < x - 1; i++) {
            arr.removeFirst();
        }
        for (int val : prefix) {
            arr.addFirst(val);
        }
        ListIterator<Integer> editor = arr.listIterator();
        for (int i = 1; i < x && editor.hasNext(); i++) {
            editor.next();
        }
        editor.add(m);

        return arr;
    }

    public static void main(String[] args) {
        try (Scanner fr = new Scanner(System.in)) {
            int t = fr.nextInt();
            for (int caseNum = 1; caseNum <= t; caseNum++) {
                int n = fr.nextInt();
                int c = fr.nextInt();

                System.out.print("Case #" + caseNum + ": ");
                if (c < n - 1 || c > (n * (n + 1)) / 2 - 1) {
                    System.out.println("IMPOSSIBLE");
                    continue;
                }

                LinkedList<Integer> ans = construct(n, c, 1);
                for (int num : ans) System.out.print(num + " ");
                System.out.println();
            }
        }
    }
        
}