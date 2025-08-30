// Find the subset with maximum value of (mean-median)
// Subset = {x[i-k], ..., x[i-1],  x[i],  x[n-k], ..., x[n-1]}
import java.util.*;
public class simpleSkewness {
    public static void main(String[] args) {
        int N;
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        ArrayList<Long> v = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            v.add(sc.nextLong());
        }
        Collections.sort(v);

        //prefix sums array
        ArrayList<Long> ps = new ArrayList<>();
        ps.add(0L);
        for(int i = 0; i < N; i++) {
            long sum = ps.get(i) + v.get(i);
            ps.add(sum);
        }

        double maxSkewness = -1, mean = 0, currmean = 0;
        int optMedInd = 0, optguess = 0;
        for(int guess = 0; guess < N; guess++) {
            int bestMedianIndForGuess = 0;
            // v[guess] is assumed median
            int maxPossibleMedInd = Math.min(guess, N-1-guess);
            // initializing binary search
            int l = 0, r = maxPossibleMedInd, mid;
            while(r-l > 0) {
                mid = (l+r)/2;
                currmean = findMean(ps, v, guess, mid);
                if(mid + 1 > maxPossibleMedInd) {
                    r = mid-1;
                    continue;
                }
                mean = findMean(ps, v, guess, mid+1);

                if(mean > currmean) { // we are in the increasing part of the bitonic property graph
                    bestMedianIndForGuess = mid + 1;
                    l = mid +1;
                }
                else {
                    r = mid -1;
                }
            }
            double finalMeanForGuess = findMean(ps, v, guess, bestMedianIndForGuess);
            double currentSkewness = finalMeanForGuess - v.get(guess);
                if (currentSkewness > maxSkewness) {
                maxSkewness = currentSkewness;
                optguess = guess;
                optMedInd = bestMedianIndForGuess;
            }

        }

        // Printing out the results
        System.out.println(optMedInd*2 +1);
        for(int i = 0; i < optMedInd; i++) {
            System.out.print(v.get(N - 1 - i) + " ");
        }
        for (int i = 0; i <= optMedInd; i++) {
        System.out.print(v.get(optguess - i) + " ");
        }   
    }

    // Calculate the mean for a given subset defined by a median and size K
    public static double findMean(ArrayList<Long> ps, ArrayList<Long> v, int guess, int MedianInd) {
    if (MedianInd == 0) {
        return v.get(guess);
    }
    int N = v.size();
    long leftAndMedianSum = ps.get(guess + 1) - ps.get(guess - MedianInd);
    long rightSum = ps.get(N) - ps.get(N - MedianInd); 
    
    long totalSum = leftAndMedianSum + rightSum;
    return totalSum / (2.0*MedianInd + 1);
}
}