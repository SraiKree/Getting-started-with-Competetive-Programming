// Compute the total cost of reverse sorting an array, where cost of one reverse operation is the length of the subarray being reversed.
class reversesortCost {
    public static void main(String[] args) {
        int[] arr = {7,6,5,4,3,2,1};
        reverseSort(arr);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }

    public static void reverseSort(int[] arr) {
        int cost = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int m = i;
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[m]) {
                    m = j;
                }
            }
            arr = reverse(i, m, arr);
            cost += (m-i+1);
        }
        System.out.println("The total cost incurred is: " + cost);
    }


public static int[] reverse(int start, int end, int[] a) {
    while (start < end) {
        int temp = a[start];
        a[start] = a[end];
        a[end] = temp;
        start++;
        end--;
    }
    return a;
}
}
       