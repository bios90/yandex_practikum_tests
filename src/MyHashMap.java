/*
Id успешного решения на контесте 52292856
-- ПРИНЦИП РАБОТЫ --
Задача выполнена с помощью собственного класса - хеш таблицы, а так же собственного класса - Entity. Внутри класса находится массив с размера 1000009. В каждой ячейке может лежать или null или голова связного списка. Для записи берется остаток от деления хеша передаваемого ключа. По этому значению выбирается ячейка для записи. Если ячейка пуста то новый Entity становится головой связного списка. Если ячейка не пуста то мы идем по списку, если находим ключ у которого совпали значения equals то производим перезапись этого Entity. Если entity по equals не был найден то происходит запись в конец списка. Поиск и и удаление происходит по такому же принципу, только в случае с удалением производим удаление из связного списка.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность алгоритма будет O(n), так как она будет зависеть только от количества поступивших команд. Все остальные операции будут выполнены за O(1).

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n). Память будет использована для хранения команд и хранения значений в таблице, то есть не может превысить 2n.
*/

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyHashMap {

    private static String[] commands;
    private static CustomHashMap<Integer, Integer> hashMap;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder out = new StringBuilder();
        for (String command : commands) {
            String[] words = command.split(" ");
            switch (words[0]) {
                case "put":
                    {
                        int key = Integer.parseInt(words[1]);
                        int value = Integer.parseInt(words[2]);
                        hashMap.put(key, value);
                        break;
                    }
                case "get":
                    {
                        int key = Integer.parseInt(words[1]);
                        Integer value = hashMap.get(key);
                        if (value == null) {
                            out.append("None").append("\n");
                        } else {
                            out.append(value).append("\n");
                        }
                        break;
                    }
                case "delete":
                    {
                        int key = Integer.parseInt(words[1]);
                        Integer value = hashMap.get(key);
                        if (value == null) {
                            out.append("None").append("\n");
                        } else {
                            out.append(value).append("\n");
                            hashMap.delete(key);
                        }
                        break;
                    }
            }
        }
        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        hashMap = new CustomHashMap<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int commandsCount = Integer.parseInt(reader.readLine());
        commands = new String[commandsCount];
        for (int i = 0; i < commandsCount; i++) {
            commands[i] = reader.readLine();
        }
    }
}

class CustomHashMap<K, V> {

    private final int BUCKET_SIZE = 1000009;
    private CustomMapEntity<K, V>[] buckets;

    public CustomHashMap() {
        this.buckets = new CustomMapEntity[BUCKET_SIZE];
    }

    public void put(K key, V value) {

        int hash = key.hashCode();
        int index = hash % BUCKET_SIZE;

        if (buckets[index] == null) {
            buckets[index] = new CustomMapEntity<K, V>(key, value);
        } else {
            CustomMapEntity<K, V> node = buckets[index];
            CustomMapEntity<K, V> prev = null;
            while (node != null) {
                CustomMapEntity<K, V> next = node.getNext();
                if (node.getKey().equals(key)) {
                    CustomMapEntity<K, V> entity = new CustomMapEntity<>(key, value);
                    entity.setNext(next);

                    if (prev != null) {
                        prev.setNext(next);
                    } else {
                        buckets[index] = entity;
                    }
                    return;
                }

                prev = node;
                node = node.getNext();
            }

            CustomMapEntity<K, V> entity = new CustomMapEntity<>(key, value);
            prev.setNext(entity);
        }
    }

    public V get(K key) {
        int hash = key.hashCode();
        int index = hash % BUCKET_SIZE;

        CustomMapEntity<K, V> node = buckets[index];

        while (node != null && !node.getKey().equals(key)) {
            node = node.getNext();
        }

        if (node == null) {
            return null;
        }

        return node.getValue();
    }

    public boolean delete(K key) {
        int hash = key.hashCode();
        int index = hash % BUCKET_SIZE;

        if (buckets[index] == null) {
            return false;
        }

        CustomMapEntity<K, V> node = buckets[index];
        CustomMapEntity<K, V> prev = null;
        while (node != null) {
            if (node.getKey().equals(key)) {
                if (node == buckets[index]) {
                    buckets[index] = node.getNext();
                } else if (prev != null) {
                    prev.setNext(node.getNext());
                }
                return true;
            }
            prev = node;
            node = node.getNext();
        }

        return false;
    }
}

class CustomMapEntity<K, V> {

    private K key;
    private V value;
    private CustomMapEntity<K, V> next;

    public CustomMapEntity(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public CustomMapEntity<K, V> getNext() {
        return next;
    }

    public void setNext(CustomMapEntity<K, V> next) {
        this.next = next;
    }
}
