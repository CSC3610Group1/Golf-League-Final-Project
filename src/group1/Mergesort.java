package group1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Class CSC3610
 * Programmer: T.J. Stankus
 * Date: 10/28/2015
 * Purpose: mergesort algorithm that takes a generic list and comparator
 */
public final class Mergesort {

    private Mergesort() {
    }

    public static <T extends Comparable<T>> List<T> mergeSort(List<T> list) {
        return mergeSort(list, T::compareTo);
    }

    public static <T> List<T> mergeSort(List<T> list, Comparator<T> sortBy) {
        return mergeSortImpl(list, sortBy, 0, list.size() - 1);
    }

    private static <T> List<T> mergeSortImpl(List<T> list, Comparator<T> sortBy,
                                             int startIndex, int endIndex) {
        if (endIndex == startIndex) {
            return list;
        }
        //Divide till you breakdown your list to single element
        else if (startIndex < endIndex && (endIndex - startIndex) >= 1) {
            int mid = (endIndex + startIndex) / 2;
            mergeSortImpl(list, sortBy, startIndex, mid);
            mergeSortImpl(list, sortBy, mid + 1, endIndex);

            //merging Sorted array produce above into one sorted array
            merge(list, sortBy, startIndex, mid, endIndex);
        }

        return list;
    }

    private static <T> List<T> merge(List<T> list, Comparator<T> sortBy,
                                     int startIndex, int midIndex, int endIndex) {

        //Below is the mergedarray that will be sorted array Array[i-midIndex] , Array[(midIndex+1)-endIndex]
        List<T> mergedSortedArray = new ArrayList<T>() {
        };

        int leftIndex = startIndex;
        int rightIndex = midIndex + 1;

        while (leftIndex <= midIndex && rightIndex <= endIndex) {
            if (sortBy.compare(list.get(leftIndex), list.get(rightIndex)) < 0) {
                mergedSortedArray.add(list.get(leftIndex));
                leftIndex++;
            } else {
                mergedSortedArray.add(list.get(rightIndex));
                rightIndex++;
            }
        }

        //Either of below while loop will execute
        while (leftIndex <= midIndex) {
            mergedSortedArray.add(list.get(leftIndex));
            leftIndex++;
        }

        while (rightIndex <= endIndex) {
            mergedSortedArray.add(list.get(rightIndex));
            rightIndex++;
        }

        int i = 0;
        int j = startIndex;
        //Setting sorted array to original one
        while (i < mergedSortedArray.size()) {
            list.set(j, mergedSortedArray.get(i++));
            j++;
        }
        return mergedSortedArray;
    }


}

