package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class MergeSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public static int[] mergeSort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int n = array.length;
        int mid = n / 2;
        int[] left = new int[mid];
        int[] right = new int[n - mid];
        for (int i = 0; i < mid; i++) {
            left[i] = array[i];
        }
        for (int i = mid; i < n; i++) {
            right[i - mid] = array[i];
        }
        mergeSort(left);
        mergeSort(right);

        merge(array, left, right, mid, n - mid);
        return array;
    }

    private static void merge(int[] array, int[] left, int[] right, int leftIndex, int rightIndex) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftIndex && j < rightIndex) {
            if (left[i] <= right[j]) {
                array[k++] = left[i++];
            } else {
                array[k++] = right[j++];
            }
        }
        while (i < leftIndex) {
            array[k++] = left[i++];
        }
        while (j < rightIndex) {
            array[k++] = right[j++];
        }
    }

    public static void main(String... args) {
        int[] input = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = mergeSort(input);
        CommonUtils.printArray("After sorting:", sorted);
    }
}
