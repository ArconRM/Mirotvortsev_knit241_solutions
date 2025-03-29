import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.knit.solutions.task18.NullDuplicator;
import org.knit.solutions.task19.MergingSorter;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;

public class MergingSorterTest {
    private MergingSorter mergingSorter;

    @Before
    public void setUp() {
        mergingSorter = new MergingSorter();  // Создание нового экземпляра перед каждым тестом
    }

    @Test
    public void testMerge() {
        int[] nums1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0};
        int[] nums2 = new int[]{2, 5, 6, 6};
        int[] numsCorrect = new int[]{1, 2, 2, 3, 4, 5, 6, 6};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);

        nums1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0};
        nums2 = new int[]{-6, -6, -5, -2};
        numsCorrect = new int[]{-6, -6, -5, -2, 1, 2, 3, 4};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);

        nums1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0};
        nums2 = new int[]{-6, -6, -5, 2};
        numsCorrect = new int[]{-6, -6, -5, 1, 2, 2, 3, 4};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);

        nums1 = new int[]{1, 2, 3, 4, 0, 0, 0, 0};
        nums2 = new int[]{-6, 6, 7, 8};
        numsCorrect = new int[]{-6, 1, 2, 3, 4, 6, 7, 8};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);

        nums1 = new int[]{1, 2, 3};
        nums2 = new int[]{};
        numsCorrect = new int[]{1, 2, 3};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);

        nums1 = new int[]{};
        nums2 = new int[]{};
        numsCorrect = new int[]{};
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);

        assertArrayEquals(nums1, numsCorrect);
    }

    @Test
    public void testPerformance() {
        int[] values = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int n = 200_000_000;
        int m = n / 2;

        int[] nums1 = new int[n];
        for (int i = 0; i < n / 10; i++) {
            for (int j = 0; j < values.length; j++) {
                nums1[i * 10 + j] = values[j];
            }
        }

        int[] nums2 = new int[m];
        for (int i = 0; i < m / 10; i++) {
            for (int j = 0; j < values.length; j++) {
                nums2[i * 10 + j] = values[j];
            }
        }

        long time = System.currentTimeMillis();
        mergingSorter.mergeWithSorting(nums1, nums2, nums1.length, nums2.length);
        long elapsedTime = System.currentTimeMillis() - time;
        System.out.println("Занятое время: " + elapsedTime);

        assertFalse("Тест на производительность не пройден", elapsedTime > 200);
    }

    @After
    public void tearDown() {
        mergingSorter = null;  // Очистка ресурсов
    }
}
