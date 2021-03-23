package arrays;

public class BinarySearch {

    private int binarySearchLeft(int i, int n, int target, int[] arr) {
        // [i, n]
        int left = i;
        int right = n;
        while(left < right) {
            int mid = (left + right) / 2;
            int current = arr[mid];
            if(current >= target) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int binarySearchRight(int i, int n, int target, int[] arr) {
        // [i, n]
        int left = i;
        int right = n;
        while(left < right) {
            int mid = (left + right) / 2 + 1;
            int current = arr[mid];
            if(current > target) right = mid - 1;
            else left = mid;
        }
        return left;
    }
}
