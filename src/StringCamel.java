import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class StringCamel {
    private static Node rootText;
    private static String[] patterns;

    public static void main(String[] args) throws IOException {
        initFields();
        StringBuilder out = new StringBuilder();
        for (String pattern : patterns) {
            Node node = rootText;
            boolean mathces = true;
            for (Character c : pattern.toCharArray()) {
                if(node.childNodes.containsKey(c)){
                    node = node.childNodes.get(c);
                }
                else{
                    mathces = false;
                    break;
                }
            }

            if(!mathces) continue;

            Set<String> result = new TreeSet<>();

            ArrayDeque<Node> stack = new ArrayDeque<>();
            stack.push(node);
            while (!stack.isEmpty()){
                Node n = stack.pop();
                result.addAll(n.words);
                for (Map.Entry<Character, Node> entry : n.childNodes.entrySet()) {
                   stack.push(entry.getValue());
                }
            }

            for(String r : result){
                out.append(r).append("\n");
            }
        }
        System.out.println(out.toString().trim());
    }

    private static void initFields() throws IOException {
        rootText = new Node(null);
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int countWords = Integer.parseInt(reader.readLine());
        for (int i = 0; i < countWords; i++) {
            String word = reader.readLine();
            addWord(rootText, word);
        }

        int countPatterns = Integer.parseInt(reader.readLine());
        patterns = new String[countPatterns];
        for (int i = 0; i < countPatterns; i++) {
            patterns[i] = reader.readLine();
        }
    }

    private static void addWord(Node root, String word) {
        Node currentNode = root;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                Node child;
                if (currentNode.hasCharacter(c)) {
                    child = currentNode.childNodes.get(c);
                } else {
                    child = new Node(c);
                    currentNode.childNodes.put(c, child);
                }
                currentNode = child;
            }
        }
        currentNode.words.add(word);
    }

    private static class Node {
        Character letter;
        Set<String> words = new TreeSet<>();
        Map<Character, Node> childNodes = new TreeMap<>();

        public Node(Character letter) {
            this.letter = letter;
        }

        public boolean hasCharacter(char c) {
            return childNodes.get(c) != null;
        }
    }
}
