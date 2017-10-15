package ru.spbau.svidchenko.FL.hw04.language;

public class OperationLexem extends Lexem {
    protected final Type type;

    public OperationLexem(Type type, long line, long column, long length) {
        super(line, column, length);
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Operation_" + type.name() + super.toString();
    }

    public enum Type{
        PLUS,
        MINUS,
        DIVIDE,
        MULTIPLY,
        REMAINDER,
        EQ,
        NEQ,
        GREATER,
        GEQ,
        LESSER,
        LEQ,
        AND,
        OR
    }
}
