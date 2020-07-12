package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class MergeSort {

    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public static int[] mergeSort(int[] array) {
        int n = array.length;
        if (n < 2) {
            return array;
        }
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

    private static void merge(int[] array, int[] left, int[] right, int leftBoundary, int rightBoundary) {
        int leftIndex = 0;
        int rightIndex = 0;
        int arrayIndex = 0;
        while (leftIndex < leftBoundary && rightIndex < rightBoundary) {
            if (left[leftIndex] < right[rightIndex]) {
                array[arrayIndex++] = left[leftIndex++];
            } else {
                array[arrayIndex++] = right[rightIndex++];
            }
        }
        while (leftIndex < leftBoundary) {
            array[arrayIndex++] = left[leftIndex++];
        }
        while (rightIndex < rightBoundary) {
            array[arrayIndex++] = right[rightIndex++];
        }
    }

    public static void main(String... args) {
        int[] input = new int[]{6, 5, 3, 1, 8, 7, 2, 4};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = mergeSort(input);
        CommonUtils.printArray("After sorting:", sorted);
    }
}
