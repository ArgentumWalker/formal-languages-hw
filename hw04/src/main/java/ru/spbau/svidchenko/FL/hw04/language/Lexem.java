package ru.spbau.svidchenko.FL.hw04.language;

public abstract class Lexem {
    protected final long line;
    protected final long column;
    protected final long length;

    public Lexem(long line, long column, long length) {
        this.line = line;
        this.column = column;
        this.length = length;
    }

    public long getColumn() {
        return column;
    }

    public long getLine() {
        return line;
    }

    @Override
    public String toString() {
        return "[" + line + ":" + column + ":" + length + "]";
    }
}
