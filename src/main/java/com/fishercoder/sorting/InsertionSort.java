package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class InsertionSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public static int[] insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
        return array;
    }

    public static void main(String... args) {
        int[] input = new int[]{5, 2, 4, 6, 1, 3};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = insertionSort(input);
        CommonUtils.printArray("After sorting:", sorted);

    }
}
