import java.util.*;
public class troubleSort {
    public static void main(String[] args) {
        int T;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of test cases: ");
        T = sc.nextInt();
        for(int i = 0; i<T; i++) {
            System.out.println("Enter the size of array");
            int N = sc.nextInt();
            int [] a = new int[N];
            System.out.println("Enter the array elements: ");
            for(i = 0; i < N; i++) {
                a[i] = sc.nextInt();
            }

            // Create two arrays to store even and odd indexed elements
            int[] even = new int[N/2 + (N%2)]; 
            int[] odd = new int[N/2 + (N%2)];
            
            // Split elements into even and odd indexed arrays
            int evenIndex = 0;
            int oddIndex = 0;
            for(int j = 0; j < N; j++) {
                if(j % 2 == 0) {
                    even[evenIndex++] = a[j];
                } else {
                    odd[oddIndex++] = a[j];
                }
            }
            
            // Sort both arrays
            Arrays.sort(even);
            Arrays.sort(odd);
            
            // Merge back into original array
            evenIndex = 0;
            oddIndex = 0;
            for(int j = 0; j < N; j++) {
                if(j % 2 == 0) {
                    a[j] = even[evenIndex++];
                } else {
                    a[j] = odd[oddIndex++];
                }
            }

            System.out.println("Array after trouble sort:");
            for(int j = 0; j < N; j++) {
                System.out.print(a[j] + " ");
            }
            System.out.println();

            // Check if the combined array is sorted or not
            int flag = -1;
            for(int k = 0; k < N; k++){
                if(k < N-1){
                // This identifies the first pair that is out of order
                if(a[k] > a[k+1]){ 
                    flag = k;
                    // When such a pair is found we break out of this loop
                    break; 
                }
            }
        }
        if(flag == -1) {
            System.out.println("OK");
        }
        else {
            System.out.println("Not ok, breaks at" + flag);
        }

        }
  
    }
}
