package codekata.wordchain.util;

public class Tuple2<L, R> {
    public static <L, R> Tuple2<L, R> tuple2(L left, R right) {
        return new Tuple2<L, R>(left, right);
    }

    private L left;
    private R right;

    public Tuple2(L left, R right) {
        this.left = left;
        this.right = right;
    }

    public L _1() {
        return left;
    }

    public R _2() {
        return right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tuple2 tuple2 = (Tuple2) o;

        if (left != null ? !left.equals(tuple2.left) : tuple2.left != null) return false;
        if (right != null ? !right.equals(tuple2.right) : tuple2.right != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = left != null ? left.hashCode() : 0;
        result = 31 * result + (right != null ? right.hashCode() : 0);
        return result;
    }
}
