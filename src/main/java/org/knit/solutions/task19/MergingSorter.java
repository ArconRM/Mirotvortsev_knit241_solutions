package org.knit.solutions.task19;

public class MergingSorter {
    public void mergeWithSorting(int[] nums1, int[] nums2, int n, int m) {
        // Для прохода по первому
        int i = m - 1;
        // Для прохода по второму
        int j = i;
        // Для записи в первом массиве
        int k = n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }

    public void printArray(int[] arr) {
        for (int element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
