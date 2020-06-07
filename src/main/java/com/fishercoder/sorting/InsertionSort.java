package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class InsertionSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public static int[] insertionSort(int[] array) {
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
        System.out.println("Did " + k + " comparisons to finish sorting this array of size: " + array.length);
        return array;
    }

    public static void main(String... args) {
        int[] input = new int[]{3, 1, 4, 5, 2};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = insertionSort(input);
        CommonUtils.printArray("After sorting:", sorted);
        input = new int[]{5, 4, 3, 2, 1};
        insertionSort(input);
        input = new int[]{1, 2, 3, 4, 5};
        insertionSort(input);
    }
}
