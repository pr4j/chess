package Board;

import com.google.common.base.Objects;

public class Position {
    public Integer x;
    public Integer y;

    public Position(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equal(x, position.x) &&
                Objects.equal(y, position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(x, y);
    }

    @Override
    public String toString() {
        final String alphabet = "abcdefghijklmnopqrstuvwxyz";

        return alphabet.charAt(y) + String.valueOf(8 - x);
    }
}
