package ru.spbau.svidchenko.FL.hw04.language;

public class SeparatorLexem extends Lexem {
    protected final Type type;

    public SeparatorLexem(Type type, long line, long column, long length) {
        super(line, column, length);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Separator_" + type.name() + super.toString();
    }

    public enum Type{
        LEFT_BRACKET,
        RIGHT_BRACKET,
        STATEMENT_END
    }
}
