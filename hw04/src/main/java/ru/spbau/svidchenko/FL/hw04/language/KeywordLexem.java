package ru.spbau.svidchenko.FL.hw04.language;

public class KeywordLexem extends Lexem{
    protected final Type type;

    public KeywordLexem(Type type, long line, long column, long length) {
        super(line, column, length);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Keyword_" + type.name() + super.toString();
    }

    public enum Type{
        IF,
        THEN,
        ELSE,
        WHILE,
        DO,
        READ,
        WRITE,
        BEGIN,
        END
    }
}
