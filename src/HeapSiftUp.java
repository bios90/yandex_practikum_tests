import java.util.Map;

public class HeapSiftUp {

    public static int siftUp(int[] heap, int idx) {

        int pos = idx;
        while (pos > 1) {
            int num = heap[pos];
            int posParent = pos / 2;
            if (heap[posParent] > num) {
                break;
            } else {
                swap(heap, pos, posParent);
                pos = posParent;
            }
        }
        return pos;
    }

    private static void test() {
        int[] sample = {-1, 12, 6, 8, 3, 15, 7};
        assert siftUp(sample, 5) == 1;
    }

    private static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
