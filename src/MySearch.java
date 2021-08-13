// Id успешного решения на контесте 52292813
/*
-- ПРИНЦИП РАБОТЫ --
Для выполнения задания нужно сделать перебор слов в запросах с перебором слов в документах. Для оптимизации этой работы производится мемоизация данных. Строки запроса переводятся в сет слов(так как в запросе считаются только уникальные слова). При считывании данных документов происходит из запись в хеш таблицу где ключем будут слова, а значением сет документов в которых это слово содержится. Так же в самом документе хранится не строка с названием документа, а таблица где ключем является слово, а значением его количество повторений в документе. Последний шаг для оптимизации это хранение уже полученных результатов сравнения в хеш таблице где ключем будет запрос а значением строка с результатом сравнения.

-- ВРЕМЕННАЯ СЛОЖНОСТЬ --
Сложность алгоритма будет O(n^2), где n будет количество уникальных слов в запросах. Такая сложность будет потому что нам нужно для каждого слова в запросе получить количество его повторений в документе. Это будет цикл в цикле.

-- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
Сложность по памяти будет O(n) где n это количество запросов + количество документов.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MySearch {
    private static Set<String>[] requests;
    private static Map<String, Set<ModelDocument>> wordToDocs;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initDocuments(reader);
        initRequests(reader);

        StringBuilder out = new StringBuilder();
        Map<Set<String>, String> mapOfResults = new HashMap<>();

        for (Set<String> request : requests) {
            if (mapOfResults.containsKey(request)) {
                out.append(mapOfResults.get(request)).append("\n");
            } else {
                ModelSearchResult result = new ModelSearchResult();
                Set<ModelDocument> matchedDocuments = getMatchedDocuments(request);
                for (ModelDocument document : matchedDocuments) {
                    int matchedCount = getMatchedCount(request, document);
                    if (matchedCount > 0) {
                        result.addDocumentMatch(matchedCount, document.getId());
                    }
                }
                String topFive = result.getTopFive();
                out.append(topFive).append("\n");
                mapOfResults.put(request, topFive);
            }
        }

        System.out.println(out.toString().trim());
    }

    private static int getMatchedCount(Set<String> request, ModelDocument document) {
        int count = 0;
        for (String word : request) {
            if (document.containsWord(word)) {
                count += document.getWordCount(word);
            }
        }
        return count;
    }

    private static Set<ModelDocument> getMatchedDocuments(Set<String> request) {
        Set<ModelDocument> documents = new HashSet<>();
        for (String word : request) {
            if (wordToDocs.containsKey(word)) {
                documents.addAll(wordToDocs.get(word));
            }
        }
        return documents;
    }

    private static void initDocuments(BufferedReader reader) throws IOException {
        int docsCount = Integer.parseInt(reader.readLine());
        wordToDocs = new HashMap<>();
        for (int i = 0; i < docsCount; i++) {
            ModelDocument document = new ModelDocument(i);
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                document.addWord(word);

                wordToDocs.computeIfPresent(
                        word,
                        (s, modelDocuments) -> {
                            modelDocuments.add(document);
                            return modelDocuments;
                        });
                wordToDocs.computeIfAbsent(
                        word,
                        s -> {
                            Set<ModelDocument> currentWordDocuments = new HashSet<>();
                            currentWordDocuments.add(document);
                            return currentWordDocuments;
                        });
            }
        }
    }

    private static void initRequests(BufferedReader reader) throws IOException {
        int requestsCount = Integer.parseInt(reader.readLine());
        requests = new Set[requestsCount];
        for (int i = 0; i < requestsCount; i++) {
            String[] word = reader.readLine().split(" ");
            Set<String> requestAsWords = new HashSet<>(Arrays.asList(word));
            requests[i] = requestAsWords;
        }
    }
}

class ModelDocument {
    private final int id;
    private final Map<String, Integer> wordsWithCount = new HashMap<>();

    public ModelDocument(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean containsWord(String word) {
        return wordsWithCount.containsKey(word);
    }

    public int getWordCount(String word) {
        return wordsWithCount.get(word);
    }

    public void addWord(String word) {
        if (wordsWithCount.containsKey(word)) {
            int count = wordsWithCount.get(word);
            wordsWithCount.put(word, ++count);
        } else {
            wordsWithCount.put(word, 1);
        }
    }
}

class ModelSearchResult {

    private final Map<Integer, List<Integer>> matchedCountToDocIds =
            new TreeMap<>(Collections.reverseOrder());

    public void addDocumentMatch(int count, int documentId) {
        if (matchedCountToDocIds.containsKey(count)) {
            List<Integer> matchedIds = matchedCountToDocIds.get(count);
            matchedIds.add(documentId);
        } else {
            List<Integer> matchedIds = new ArrayList<>();
            matchedIds.add(documentId);
            matchedCountToDocIds.put(count, matchedIds);
        }
    }

    public String getTopFive() {
        StringBuilder out = new StringBuilder();
        int count = 0;
        for (Map.Entry<Integer, List<Integer>> entry : matchedCountToDocIds.entrySet()) {
            List<Integer> matched = entry.getValue();
            Collections.sort(matched);
            for (Integer docId : matched) {
                out.append(docId + 1).append(" ");
                count++;
                if (count == 5) {
                    return out.toString().trim();
                }
            }
        }
        return out.toString().trim();
    }
}
