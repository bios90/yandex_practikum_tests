import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MySearch2 {
    private static Map<String, TreeSet<Integer>> wordToDocsData;
    private static ArrayList<DocumentData> documents;
    private static List<String> requests;

    public static void main(String[] args) throws IOException {
        initFields();
        RequestSearchData[] results = getResults();
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < requests.size(); i++) {
            RequestSearchData requestResults = results[i];
            out.append(requestResults.getTopFive());
            System.out.println(out.toString().trim());
            out.setLength(0);
        }
    }

    private static void initFields() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        initDocs(reader);
        initRequests(reader);
    }

    private static RequestSearchData[] getResults() {

        RequestSearchData[] results = new RequestSearchData[requests.size()];

        for (int i = 0; i < requests.size(); i++) {

            String request = requests.get(i);
            Set<String> requestAsWords = stringToSetOfWords(request);
            RequestSearchData searchData = new RequestSearchData();
            TreeSet<DocumentData> matchedData = getMatchedDocs(requestAsWords);

            for (DocumentData documentData : matchedData) {
                int matchedCount = documentData.getWordMatchCount(requestAsWords);
                if (matchedCount > 0) {
                    searchData.addResult(matchedCount, documentData.docPos);
                }
            }
            results[i] = searchData;
        }

        return results;
    }

    private static void printMatchedVariants(Set<String> request, Set<DocumentData> mathced) {
        StringBuilder out = new StringBuilder();
        Iterator<String> iteratorRequest = request.iterator();
        while (iteratorRequest.hasNext()) {
            out.append(iteratorRequest.next()).append(" ");
        }
        String strRequest = out.toString().trim();

        out.setLength(0);
        for (DocumentData data : mathced) {
            //            out.append(data.documentFullName).append("\n");
        }

        String strDocument = out.toString().trim();
        System.out.println("*** Next Request ***");
        System.out.println(strRequest);
        System.out.println("** Exists is **");
        System.out.println(strDocument);
        System.out.println("***************************************");
    }

    private static void initDocs(BufferedReader reader) throws IOException {
        int countDocs = Integer.parseInt(reader.readLine());
        wordToDocsData = new HashMap<>();
        documents = new ArrayList<>();
        for (int i = 0; i < countDocs; i++) {
            String line = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(line);
            DocumentData data = new DocumentData(i);
            while (tokenizer.hasMoreTokens()) {
                String word = tokenizer.nextToken();
                data.addWord(word);

                if (wordToDocsData.containsKey(word)) {
                    TreeSet<Integer> currentWordDocIds = wordToDocsData.get(word);
                    currentWordDocIds.add(i);
                } else {
                    TreeSet<Integer> wordIds = new TreeSet<>();
                    wordIds.add(i);
                    wordToDocsData.put(word, wordIds);
                }
            }
            documents.add(data);
        }
    }

    private static void initRequests(BufferedReader reader) throws IOException {
        int countRequests = Integer.parseInt(reader.readLine());
        requests = new ArrayList<>();
        for (int i = 0; i < countRequests; i++) {
            requests.add(reader.readLine());
        }
    }

    public static TreeSet<DocumentData> getMatchedDocs(Set<String> request) {
        TreeSet<DocumentData> data =
                new TreeSet<>(
                        new Comparator<DocumentData>() {
                            @Override
                            public int compare(DocumentData documentData, DocumentData t1) {
                                return documentData.docPos - t1.docPos;
                            }
                        });
        for (String word : request) {

            if (wordToDocsData.containsKey(word)) {
                TreeSet<Integer> mathcedDocsIds = wordToDocsData.get(word);
                for (Integer id : mathcedDocsIds) {
                    data.add(documents.get(id));
                }
            }
        }
        return data;
    }

    private static Set<String> stringToSetOfWords(String str) {
        Set<String> set = new HashSet<>();
        String[] words = str.split(" ");
        set.addAll(Arrays.asList(words));
        return set;
    }
}

class DocumentData implements Comparable<DocumentData> {
    int docPos;
    Map<String, Integer> words = new HashMap<>();

    public DocumentData(int docPos) {
        this.docPos = docPos;
    }

    public void addWord(String word) {
        if (words.containsKey(word)) {
            words.put(word, words.get(word) + 1);
        } else {
            words.put(word, 1);
        }
    }

    public int getWordMatchCount(Set<String> request) {
        int matchedWords = 0;

        for (String word : request) {
            if (words.containsKey(word)) {
                matchedWords += words.get(word);
            }
        }

        return matchedWords;
    }

    @Override
    public int compareTo(DocumentData documentData) {
        return this.docPos - documentData.docPos;
    }
}

class RequestSearchData {
    TreeMap<Integer, ArrayList<Integer>> results =
            new TreeMap<>(
                    new Comparator<Integer>() {
                        @Override
                        public int compare(Integer integer, Integer t1) {
                            return t1 - integer;
                        }
                    });

    public void addResult(Integer count, Integer documentPos) {
        if (results.get(count) == null) {
            ArrayList<Integer> result = new ArrayList<>();
            result.add(documentPos);
            results.put(count, result);
        } else {
            ArrayList<Integer> result = results.get(count);
            result.add(documentPos);
        }
    }

    public String getTopFive() {
        StringBuilder out = new StringBuilder();
        int count = 0;
        for (Map.Entry<Integer, ArrayList<Integer>> entry : results.entrySet()) {
            for (Integer documentPos : entry.getValue()) {
                out.append(documentPos + 1).append(" ");
                count++;
                if (count == 5) {
                    return out.toString().trim();
                }
            }
        }
        return out.toString().trim();
    }
}
