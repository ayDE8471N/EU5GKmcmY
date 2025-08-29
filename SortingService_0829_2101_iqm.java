// 代码生成时间: 2025-08-29 21:01:21
public class SortingService {

    /**
     * Sorts an array of integers using the bubble sort algorithm.
     *
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public int[] bubbleSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        for (int i = 0; i < arr.length; i++) {
            for (int j = 1; j < (arr.length - i); j++) {
                if (arr[j - 1] > arr[j]) {
                    // Swap elements if they are in the wrong order
                    int temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * Sorts an array of integers using the selection sort algorithm.
     *
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public int[] selectionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap elements to place the smallest at the beginning
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }

    /**
     * Sorts an array of integers using the insertion sort algorithm.
     *
     * @param arr The array to be sorted.
     * @return The sorted array.
     */
    public int[] insertionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Input array cannot be null.");
        }

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    // Additional sorting methods can be added here
}
