package ru.spbau.svidchenko.FL.hw04.language;

public class IdentifierLexem extends Lexem{
    protected final String name;

    public IdentifierLexem(String name, long line, long column, long length) {
        super(line, column, length);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Identifier(" + name + ")" + super.toString();
    }
}
