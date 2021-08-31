/*
Id успешного решения на контесте 52385053
-- ПРИНЦИП РАБОТЫ --
Для реализации задачи я создал класс TreeHeap. Данный класс хранит данные в массиве. Добавление и получение приоритетного элемента происходит по принципу кучи. При добавлении нового элемента происходит вставка его в конец массива и просеивание кучи вверх. При получении самого верхнего элемента происходит его удаление сверху кучи, перестановка последнего элемента на первую позицию и просеивание кучи вниз.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
При количестве элементов N высота дерева будет log N.  Для каждой операции вставки и получения самого приоритетного элемента сложность будет O(log n). Таким образом сложность всего алгоритма будет O(n * log n).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n). Память будет использована для хранения элементов в массиве кучи.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TreeHeapSort {

    public static void main(String[] args) throws IOException {
        TreeHeap<ModelUser> treeHeap = getTreeHeap();

        StringBuilder out = new StringBuilder();
        ModelUser user;
        while ((user = treeHeap.getBest()) != null) {
            out.append(user.getName()).append("\n");
        }
        System.out.println(out.toString().trim());
    }

    private static TreeHeap<ModelUser> getTreeHeap() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int count = Integer.parseInt(reader.readLine());
        TreeHeap<ModelUser> treeHeap = new TreeHeap<>(count);
        while (count > 0) {
            String[] data = reader.readLine().split(" ");
            String name = data[0];
            int result = Integer.parseInt(data[1]);
            int penalty = Integer.parseInt(data[2]);
            ModelUser user = new ModelUser(name, result, penalty);
            treeHeap.addItem(user);
            count--;
        }
        return treeHeap;
    }
}

class TreeHeap<T extends Comparable<T>> {

    T[] items;
    int index = 0;

    public TreeHeap(int size) {
        items = (T[]) new Comparable[size + 1];
        items[index++] = null;
    }

    public void addItem(T item) {
        if (index == items.length) {
            throw new IndexOutOfBoundsException();
        }
        items[index] = item;
        siftUp();
        index++;
    }

    public T getBest() {
        if (items.length < 2) {
            return null;
        }
        swap(1, --index);
        T item = items[index];
        items[index] = null;
        siftDown();
        return item;
    }

    private void siftUp() {
        int pos = index;
        while (pos > 1) {
            int parentIndex = pos / 2;
            T item = items[pos];
            T parent = items[parentIndex];
            if (item.compareTo(parent) > 0) {
                swap(pos, parentIndex);
                pos = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown() {
        int pos = 1;
        while (pos * 2 < index) {
            int indexLeft = pos * 2;
            int indexRight = indexLeft + 1;

            int indexLargest;
            if (indexRight < index && (items[indexRight].compareTo(items[indexLeft]) > 0)) {
                indexLargest = indexRight;
            } else {
                indexLargest = indexLeft;
            }

            T item = items[pos];
            T child = items[indexLargest];
            if (item.compareTo(child) < 0) {
                swap(pos, indexLargest);
                pos = indexLargest;
            } else {
                break;
            }
        }
    }

    private void swap(int i, int j) {
        T temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }
}

class ModelUser implements Comparable<ModelUser> {

    private String name;
    private int result;
    private int penalty;

    public ModelUser(String name, int result, int penalty) {
        this.name = name;
        this.result = result;
        this.penalty = penalty;
    }

    public String getName() {
        return name;
    }

    public int getResult() {
        return result;
    }

    public int getPenalty() {
        return penalty;
    }

    @Override
    public int compareTo(ModelUser user) {
        if (this.getResult() != user.getResult()) {
            return this.getResult() - user.getResult();
        } else if (this.getPenalty() != user.getPenalty()) {
            return user.getPenalty() - this.getPenalty();
        } else {
            return user.getName().compareTo(this.getName());
        }
    }
}
