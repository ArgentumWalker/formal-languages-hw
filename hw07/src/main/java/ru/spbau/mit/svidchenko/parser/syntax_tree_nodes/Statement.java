package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes;

public abstract class Statement extends Node {
    public Statement(long line, long col) {
        super(line, col);
    }
}
