// 代码生成时间: 2025-09-29 19:29:25
 * This class provides a service to sort an array of integers using a sorting algorithm.
 * It demonstrates the use of the STRUTS framework in a Java application.
 */
package com.example.sorting;

import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import java.util.Arrays;
import java.util.Comparator;

public class SortingService {

    /**
     * Sorts an array of integers using a specified sorting algorithm.
     * 
     * @param numbers The array of integers to sort.
     * @param algorithm The sorting algorithm to use.
     * @return The sorted array of integers.
     */
    public int[] sort(int[] numbers, String algorithm) {
        if (numbers == null || numbers.length == 0) {
            throw new IllegalArgumentException("The input array cannot be null or empty.");
        }

        switch (algorithm.toLowerCase()) {
            case "bubble":
                return bubbleSort(numbers);
            case "quick":
                return quickSort(numbers, 0, numbers.length - 1);
            default:
                throw new IllegalArgumentException("Unsupported sorting algorithm: " + algorithm);
        }
    }

    /**
     * Performs a bubble sort on the provided array.
     * 
     * @param numbers The array to sort.
     * @return The sorted array.
     */
    private int[] bubbleSort(int[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers;
    }

    /**
     * Performs a quick sort on the provided array.
     * 
     * @param numbers The array to sort.
     * @param low The starting index of the array segment to sort.
     * @param high The ending index of the array segment to sort.
     * @return The sorted array.
     */
    private int[] quickSort(int[] numbers, int low, int high) {
        if (low < high) {
            int pivot = partition(numbers, low, high);
            quickSort(numbers, low, pivot - 1);
            quickSort(numbers, pivot + 1, high);
        }
        return numbers;
    }

    /**
     * Partitions the array around a pivot element.
     * 
     * @param numbers The array to partition.
     * @param low The starting index of the array segment to partition.
     * @param high The ending index of the array segment to partition.
     * @return The index of the pivot element.
     */
    private int partition(int[] numbers, int low, int high) {
        int pivot = numbers[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (numbers[j] < pivot) {
                i++;
                int temp = numbers[i];
                numbers[i] = numbers[j];
                numbers[j] = temp;
            }
        }
        int temp = numbers[i + 1];
        numbers[i + 1] = numbers[high];
        numbers[high] = temp;
        return i + 1;
    }
}
