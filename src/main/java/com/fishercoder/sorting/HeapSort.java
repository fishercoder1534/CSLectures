package com.fishercoder.sorting;

import com.fishercoder.common.utils.CommonUtils;

public class HeapSort {
    /**
     * To verify its correctness of sorting,
     * I've used this: https://leetcode.com/problems/largest-perimeter-triangle/
     */
    public int[] heapSort(int[] array) {
        buildMaxHeap(array);
        int heapSize = array.length - 1;
        for (int i = heapSize; i > 0; i--) {
            swap(array, 0, i);
            heapSize--;
            maxHeapify(array, 0, heapSize);
        }
        return array;
    }

    private void buildMaxHeap(int[] array) {
        for (int i = array.length / 2 - 1; i >= 0; i--) {
            maxHeapify(array, i, array.length - 1);
        }
    }

    private void maxHeapify(int[] array, int parentIndex, int indexLimit) {
        int leftChildIndex = 2 * parentIndex + 1;
        int rightChildIndex = 2 * parentIndex + 2;
        int maxIndex = parentIndex;
        if (leftChildIndex <= indexLimit && array[leftChildIndex] > array[parentIndex]) {
            maxIndex = leftChildIndex;
        }

        if (rightChildIndex <= indexLimit && array[rightChildIndex] > array[maxIndex]) {
            maxIndex = rightChildIndex;
        }

        if (maxIndex != parentIndex) {
            swap(array, parentIndex, maxIndex);
            maxHeapify(array, maxIndex, indexLimit);
        }
    }

    private void swap(int[] array, int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    public static void main(String... args) {
        HeapSort heapSort = new HeapSort();
        int[] input = new int[]{3, 1, 4, 5, 2};
        CommonUtils.printArray("Before sorting: ", input);
        int[] sorted = heapSort.heapSort(input);
        CommonUtils.printArray("After sorting:", sorted);
        input = new int[]{5, 4, 3, 2, 1};
        heapSort.heapSort(input);
        input = new int[]{1, 2, 3, 4, 5};
        heapSort.heapSort(input);
    }

}
