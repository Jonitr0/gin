import static org.junit.Assert.*;

public class BubblesortTest {
    @org.junit.Test
    public void testBubblesort() throws Exception {
        int[] array={5, 1, 4, 9, 0, 8, 6,2,3,7,10,15,14,13};
        assertEquals(4,BubbleSort.bubbleSort(array));
        assertEquals(4,BubbleSort.bubbleSort(array));
        assertEquals(4,BubbleSort.bubbleSort(array));
        assertEquals(4,BubbleSort.bubbleSort(array));
    }
}
