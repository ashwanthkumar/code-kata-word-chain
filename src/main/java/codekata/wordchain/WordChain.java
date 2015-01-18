package codekata.wordchain;

import codekata.wordchain.util.Predicate;
import codekata.wordchain.util.Tuple2;

import java.util.ArrayList;
import java.util.List;

public class WordChain {
    private GraphOfWords graphOfWords = new GraphOfWords(new ArrayList<String>());

    public WordChain(List<String> dic) {
        this.graphOfWords = new GraphOfWords(dic);
    }

    public boolean isConnected(String left, String right) {
        return graphOfWords.isConnected(left, right);
    }

    public List<String> chain(String left, String right) {
        return graphOfWords.traverse(left, right);
    }

    public static Predicate<Tuple2<String, String>> canConnectTwoWords = new Predicate<Tuple2<String, String>>() {
        @Override
        public Boolean apply(Tuple2<String, String> input) {
            String left = input._1();
            String right = input._2();
            int diff = 0;
            for (int index = 0; diff <= 1 && index < Math.min(left.length(), right.length()); index++) {
                if (left.charAt(index) != right.charAt(index)) diff++;
            }
            return diff == 1;
        }
    };


}
