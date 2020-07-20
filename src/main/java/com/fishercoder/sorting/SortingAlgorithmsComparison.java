package com.fishercoder.sorting;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SortingAlgorithmsComparison {
    private static final String DIRECTORY = "src/main/java/com/fishercoder/sorting/datasets/";
    private static final String DELIMITER = ",";
    private static final String NEW_LINE = "\n";

    private static final String RANDOM = "randomDataSet.csv";
    private static final String INORDER = "inorderDataSet.csv";
    private static final String REVERSEORDER = "reverseOrderDataSet.csv";
    private static final String PARTIALLYINEORDER = "partiallyOrderDataSet.csv";
    private static final String[] dataTypes = new String[]{RANDOM, INORDER, REVERSEORDER, PARTIALLYINEORDER};

    private static final String oneK = "1000";
    private static final String twoK = "2000";
    private static final String tenK = "10000";
    private static final String twentyK = "20000";
    private static final String fiftyK = "50000";
    private static final String eightyK = "80000";
    private static final String oneHundredK = "100000";
    private static final String twoHundredK = "200000";
    private static final String[] dataSizes = new String[]{oneK, twoK, tenK, twentyK, fiftyK, eightyK, oneHundredK, twoHundredK};

    private static final String INSERTION = "insertion sort";
    private static final String MERGE = "merge sort";
    private static final String HEAP = "heap sort";
    private static final String QUICK = "quick sort";
    private static final String[] sortingAlgos = new String[]{INSERTION, MERGE, HEAP, QUICK};
    private static final String PERIOD = ".";

    private static InsertionSort insertionSort;
    private static MergeSort mergeSort;
    private static QuickSort quickSort;
    private static HeapSort heapSort;

    public static void main(String... args) throws IOException {
        SortingAlgorithmsComparison sortingAlgorithmsComparison = new SortingAlgorithmsComparison();

        sortingAlgorithmsComparison.initSort();

        for (String dataType : dataTypes) {
            if (dataType.equals(RANDOM)) {
                for (String dataSize : dataSizes) {
                    //TODO: fix for data size greater than ten k
                    if (dataSize.equals(tenK)) {
                        int[] inputArray = sortingAlgorithmsComparison.getInputArray(Integer.parseInt(dataSize), DIRECTORY + dataType);
                        System.out.println("For " + dataType + ", sorting algos performance below:");
                        for (String sortingAlgo : sortingAlgos) {
                            if (sortingAlgo.equals(INSERTION)) {
                                insertionSort.insertionSort(inputArray);
                            } else if (sortingAlgo.equals(MERGE)) {
                                mergeSort.mergeSortEntry(inputArray);
                            } else if (sortingAlgo.equals(HEAP)) {
                                heapSort.heapSort(inputArray);
                            } else if (sortingAlgo.equals(QUICK)) {
                                quickSort.quickSort(inputArray);
                            }
                        }
                        System.out.println();
                    }
                }
            }
        }
    }

    private void initSort() {
        insertionSort = new InsertionSort();
        mergeSort = new MergeSort();
        quickSort = new QuickSort();
        heapSort = new HeapSort();
    }

    public void generateRandomDataSet(int size, String fileName) throws IOException {
        System.out.println("Start generating random data set now, size: " + size);
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName + PERIOD + size));
        Random random = new Random();
        while (size-- > 0) {
            writer.write(random.nextInt(Integer.MAX_VALUE) + "");
            writer.write(DELIMITER);
            if (size % 20 == 0) {
                writer.write(NEW_LINE);
            }
        }
        writer.close();
    }

    private int[] getInputArray(int size, String fileName) throws IOException {
        File file = new File(fileName + PERIOD + size);
        if (!file.isFile()) {
            System.out.println("Asking file doesn't exist, will generate now.");
            generateRandomDataSet(size, fileName);
        }
        BufferedReader br = null;
        String line = "";
        List<String> list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName + PERIOD + size));
            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(DELIMITER);
                for (String num : numbers) {
                    list.add(num);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        int[] result = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            result[i] = Integer.parseInt(list.get(i));
        }
        return result;
    }
}
