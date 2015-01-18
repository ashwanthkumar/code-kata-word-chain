package codekata.wordchain.util;

public interface Function<I, O> {
    public O apply(I input);
}
