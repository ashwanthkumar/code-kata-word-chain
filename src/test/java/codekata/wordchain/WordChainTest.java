package codekata.wordchain;

import static codekata.wordchain.util.Lists.of;
import static codekata.wordchain.util.Tuple2.*;

import codekata.wordchain.WordChain;
import org.junit.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class WordChainTest {
    @Test
    public void shouldMatch2WordsOnlyIfTheyDifferBy1Character() {
        assertThat(WordChain.canConnectTwoWords.apply(tuple2("cat", "cot")), is(true));
        assertThat(WordChain.canConnectTwoWords.apply(tuple2("cat", "cog")), is(false));
    }

    @Test
    public void shouldCheckForChainFormation() {
        WordChain wordChain = new WordChain(of("cat", "cot", "cog", "dot", "dog", "ghi"));
        assertThat(wordChain.isConnected("cat", "dog"), is(true));
        assertThat(wordChain.isConnected("dog", "cat"), is(true));
        assertThat(wordChain.isConnected("ghi", "dog"), is(false));
    }

    @Test
    public void shouldGenerateWordChains() {
        WordChain wordChain = new WordChain(of("cat", "cot", "cog", "dot", "dog", "ghi"));
        assertThat(wordChain.chain("cat", "dog"), is(of("cat", "cot", "dot", "dog")));
        assertThat(wordChain.chain("cat", "ghi"), is(Collections.<String>emptyList()));
    }


}