package ru.spbau.svidchenko.FL.hw04.language;

public class LiteralLexem<T> extends Lexem {
    protected final T value;

    public LiteralLexem(T value, long line, long column, long length) {
        super(line, column, length);
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Literal(" + value + ")" + super.toString();
    }
}
