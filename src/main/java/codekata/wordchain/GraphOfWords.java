package codekata.wordchain;

import java.util.List;

import static codekata.wordchain.util.Tuple2.tuple2;

public class GraphOfWords {
    private GraphConnections<String> connections = new GraphConnections<String>();

    public GraphOfWords(List<String> words) {
        build(words);
    }

    public boolean isConnected(String left, String right) {
        return connections.isDeeplyConnected(left, right);
    }

    public List<String> traverse(String left, String right) {
        return connections.traverse(left, right);
    }

    private void build(List<String> words) {
        for (String word : words) {
            for (String anotherWord : words) {
                if (WordChain.canConnectTwoWords.apply(tuple2(word, anotherWord))) {
                    connections.addConnection(word, anotherWord);
                    connections.addConnection(anotherWord, word);
                }
            }
        }
    }

}
