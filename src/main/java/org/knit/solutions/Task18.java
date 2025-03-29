package org.knit.solutions;

import org.apache.commons.lang3.ArrayUtils;
import org.knit.TaskDescription;
import org.knit.solutions.task18.NullDuplicator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Условие задачи:

Дан массив целых чисел arr фиксированной длины. Необходимо продублировать каждое вхождение нуля, сдвигая остальные элементы вправо.

Примечание:

Элементы, выходящие за пределы исходного массива, не записываются.
Изменения нужно выполнить на месте (in-place), не возвращая новый массив.
Пример 1:

Вход: arr = [1,0,2,3,0,4,5,0]
Выход: [1,0,0,2,3,0,0,4]
Объяснение:

Ноль после 1 дублируется → [1,0,0,2,3,0,4,5]
Ноль после 3 дублируется → [1,0,0,2,3,0,0,4] (последний 0 выходит за границы и отбрасывается)
Пример 2:

Вход: arr = [1,2,3]
Выход: [1,2,3]
Объяснение: В массиве нет нулей, поэтому он остаётся без изменений.

Ограничения:

1 <= arr.length <= 10^4
0 <= arr[i] <= 9
Напишите решение, а затем напишите JUnit тесты для проверки решения

Тесты должны покрывать

--Обычные случаи (с нулями и без).

--Краевые случаи (пустые массивы, все нули, нули на границах).

--Производительность (большие массивы). // просто зафиксировать время выполнения по производительности эмпирическим путем
*/

@TaskDescription(taskNumber = 18, taskDescription = "Задача № 18")
public class Task18 implements Solution {

    @Override
    public void execute() {
        NullDuplicator nullDuplicator = new NullDuplicator();

        System.out.println("Пример 1: ");
        int[] arr = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        nullDuplicator.printArray(arr);
        nullDuplicator.duplicateNulls(arr);
        nullDuplicator.printArray(arr);

        System.out.println("\nПример 2: ");
        arr = new int[]{1,2,3};
        nullDuplicator.printArray(arr);
        nullDuplicator.duplicateNulls(arr);
        nullDuplicator.printArray(arr);
    }
}
