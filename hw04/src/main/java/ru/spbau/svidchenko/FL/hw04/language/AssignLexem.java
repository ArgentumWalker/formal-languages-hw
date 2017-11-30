package ru.spbau.svidchenko.FL.hw04.language;

public class AssignLexem extends Lexem {

    public AssignLexem(long line, long column, long length) {
        super(line, column, length);
    }

    @Override
    public String toString() {
        return "Assign()" + super.toString();
    }
}
