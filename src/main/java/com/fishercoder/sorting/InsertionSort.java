package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class InsertionSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/sort-an-array/
     */
    public int[] insertionSort(int[] array) {
        long start = System.currentTimeMillis();
        int k = 0;
        for (int j = 1; j < array.length; j++) {
            int key = array[j];
            int i = j - 1;
            while (i >= 0 && array[i] > key) {
                array[i + 1] = array[i];
                i--;
                k++;
            }
            k++;
            array[i + 1] = key;
        }
        long end = System.currentTimeMillis();
        long elapsedTime = end - start;
        System.out.println("It took " + elapsedTime + " milliseconds to finish insertion sort on this data set of size: " + array.length);
        return array;
    }

    public static void main(String... args) {
        InsertionSort insertionSort = new InsertionSort();
        int[] input = new int[]{3, 1, 4, 5, 2};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = insertionSort.insertionSort(input);
        CommonUtils.printArray("After sorting:", sorted);
        input = new int[]{5, 4, 3, 2, 1};
        insertionSort.insertionSort(input);
        input = new int[]{1, 2, 3, 4, 5};
        insertionSort.insertionSort(input);
    }
}
