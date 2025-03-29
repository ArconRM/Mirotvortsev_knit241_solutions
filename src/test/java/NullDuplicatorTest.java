import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.knit.solutions.task18.NullDuplicator;

import static org.junit.Assert.*;

public class NullDuplicatorTest {
    private NullDuplicator nullDuplicator;

    @Before
    public void setUp() {
        nullDuplicator = new NullDuplicator();  // Создание нового экземпляра перед каждым тестом
    }

    @Test
    public void testDups() {
        int[] arr1 = new int[]{1, 0, 2, 3, 0, 4, 5, 0};
        nullDuplicator.duplicateNulls(arr1);
        int[] arr1Correct = new int[]{1, 0, 0, 2, 3, 0, 0, 4};

        int[] arr2 = new int[]{0, 0, 2, 3, 0, 4, 5};
        nullDuplicator.duplicateNulls(arr2);
        int[] arr2Correct = new int[]{0, 0, 0, 0, 2, 3, 0};

        int[] arr3 = new int[]{1, 1, 2, 3, 4};
        nullDuplicator.duplicateNulls(arr3);
        int[] arr3Correct = new int[]{1, 1, 2, 3, 4};

        int[] arr4 = new int[]{0, 1, 2, 0};
        nullDuplicator.duplicateNulls(arr4);
        int[] arr4Correct = new int[]{0, 0, 1, 2};

        int[] arr5 = new int[]{0, 0, 0, 0};
        nullDuplicator.duplicateNulls(arr5);
        int[] arr5Correct = new int[]{0, 0, 0, 0};

        assertArrayEquals(arr1, arr1Correct);
        assertArrayEquals(arr2, arr2Correct);
        assertArrayEquals(arr3, arr3Correct);
        assertArrayEquals(arr4, arr4Correct);
        assertArrayEquals(arr5, arr5Correct);
    }

    @Test
    public void testPerformance() {
        int[] arr = new int[1_000_000];
        int[] values = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        for (int i = 0; i < 100_000; i++) {
            for (int j = 0; j < values.length; j++) {
                arr[i * 10 + j] = values[j];
            }
        }

        long time = System.currentTimeMillis();
        nullDuplicator.duplicateNulls(arr);
        long elapsedTime = System.currentTimeMillis() - time;
        System.out.println("Занятое время: " + elapsedTime);

        assertFalse("Тест на производительность не пройден", elapsedTime > 7800);
    }

    @After
    public void tearDown() {
        nullDuplicator = null;  // Очистка ресурсов
    }
}
