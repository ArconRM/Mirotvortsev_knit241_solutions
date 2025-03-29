package org.knit.solutions.task18;

import java.util.ArrayList;
import java.util.List;

public class NullDuplicator {
    public void duplicateNulls(int[] arr) {
        List<Integer> numbersToReplace = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            int currentElement = arr[i];

            if (!numbersToReplace.isEmpty()) {
                arr[i] = numbersToReplace.remove(0);
                numbersToReplace.add(currentElement);
            }
            if (currentElement == 0) {
                if (numbersToReplace.isEmpty() || numbersToReplace.get(numbersToReplace.size() - 1) == 0) {
                    numbersToReplace.add(0);
                } else {
                    numbersToReplace.add(0, 0);
                }
            }
        }
    }

    public void printArray(int[] arr) {
        for (int element : arr) {
            System.out.print(element + " ");
        }
        System.out.println();
    }
}
