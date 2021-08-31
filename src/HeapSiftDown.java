public class HeapSiftDown {

    public static void main(String[] args) {
        //        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        int[] sample = {-1, 20, 17, 19, 5, 18, 13, 13, 12, 14, 15, 16, 10, 10, 10, 10, 8};
        int result = siftDown(sample, 4);
        Helper.printArray(sample);
        System.out.println(result);
    }

    private static int siftDown(int[] heap, int idx){
        int leftPos = idx * 2;
        int rightPos = leftPos+1;

        if (leftPos >= heap.length) {
            return idx;
        }
        int indexLargest;
        if(rightPos < heap.length && heap[rightPos] > heap[leftPos]){
            indexLargest = rightPos;
        } else {
            indexLargest = leftPos;
        }
        if(heap[idx] < heap[indexLargest]){
            swap(heap,idx,indexLargest);
            return siftDown(heap,indexLargest);
        } else {
            return idx;
        }
    }

//    public static int siftDown(int[] heap, int idx) {
//        int pos = idx;
//        while (true) {
//            int leftPos = pos * 2;
//            int rightPos = leftPos + 1;
//            if (leftPos > heap.length) {
//                break;
//            }
//            int indexLargest;
//            if (rightPos < heap.length && heap[rightPos] > heap[leftPos]) {
//                indexLargest = rightPos;
//            } else {
//                indexLargest = leftPos;
//            }
//            if (heap[pos] < heap[indexLargest]) {
//                swap(heap, pos, indexLargest);
//                pos = indexLargest;
//            } else {
//                break;
//            }
//        }
//
//        return pos;
//    }

    private static void test() {
        int[] sample = {-1, 12, 1, 8, 3, 4, 7};
        assert siftDown(sample, 2) == 5;
    }

    private static void swap(int[] heap, int i, int j) {
        int temp = heap[i];
        heap[i] = heap[j];
        heap[j] = temp;
    }
}
