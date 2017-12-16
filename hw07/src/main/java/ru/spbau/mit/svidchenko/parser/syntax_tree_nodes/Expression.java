package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes;

public abstract class Expression extends Statement {
    public Expression(long line, long col) {
        super(line, col);
    }
}
