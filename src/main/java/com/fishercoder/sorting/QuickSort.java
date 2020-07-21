package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class QuickSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public int[] quickSort(int[] array) {
        long start = System.currentTimeMillis();
        quickSort(array, 0, array.length - 1);
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("It took " + elapsedTime + " milliseconds to finish quick sort on this data set of size: " + array.length);
        return array;
    }

    private void quickSort(int[] array, int begin, int end) {
        if (begin < end) {
            int partitionIndex = partition(array, begin, end);
            quickSort(array, begin, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, end);
        }
    }

    private int partition(int[] array, int begin, int end) {
        int pivot = array[end];
        int i = begin - 1;
        for (int j = begin; j < end; j++) {
            if (array[j] <= pivot) {
                i++;
                swap(array, i, j);
            }
        }

        swap(array, i + 1, end);
        return i + 1;
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String... args) {
        QuickSort quickSort = new QuickSort();
        int[] input = new int[]{2, 8, 7, 1, 3, 5, 6, 3};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = quickSort.quickSort(input);
        CommonUtils.printArray("After sorting:", sorted);
    }
}
