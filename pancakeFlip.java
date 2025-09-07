import java.util.*;
// Make all pancakes be in the + state using a pancake flipper that has you flip >1 pancakes at once, always
public class pancakeFlip {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();

        for(int i = 0; i < T; i++) {
            String s = new String();
            s = sc.next();
            int K = sc.nextInt();
            char [] pancakes = s.toCharArray();
            int N = s.length();
            int flipcount = 0 , activeFlips = 0; // Number of flips required and number of flips that actually changed the state of the pancake 
            int [] flipEffect = new int[N + 1]; // to mark how far the effect of a flip action goes
            Arrays.fill(flipEffect, 0);
            boolean isPossible = true;

            for (int j = 0; j <= N - K; j++) {
                activeFlips += flipEffect[j]; // Total number of flips that has affected pancake j

                boolean isFlipped = (activeFlips % 2 == 1);
                char originalState = pancakes[j];
                char currentState = originalState;
                if (isFlipped) {
                    currentState = (originalState == '+') ? '-' : '+';
                }

                if (currentState == '-') {
                    flipcount++;
                    activeFlips++;    // Changes state of all pancakes from j to j+k-1
                    if (j + K <= N) {
                        flipEffect[j + K]--; // Marks everything from j+k to be unaffected by this flip
                    }
                }
            }

            // Flips cannot be performed in this region as <K pancakes are left
            for (int j = N - K + 1; j < N; j++) {
                activeFlips += flipEffect[j];
                
                // Update the state of pancakes here, due to the flips made earlier.
                boolean isFlipped = (activeFlips % 2 == 1);
                char originalState = pancakes[j];
                char currentState = originalState;
                if (isFlipped) {
                    currentState = (originalState == '+') ? '-' : '+';
                }

                if (currentState == '-') {  // If any of these remaining pancakes are on the wrong side, flipping them is impossible
                    isPossible = false;
                    break; 
                }
            }
            
            System.out.print("Case #" + (i + 1) + ": ");
            if (isPossible) {
                System.out.println(flipcount);
            } else {
                System.out.println("IMPOSSIBLE");
            }
        }
    }
}
