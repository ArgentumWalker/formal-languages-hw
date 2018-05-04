package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes;

import java.util.List;

public abstract class Node {
    private final long line;
    private final long col;
    public Node(long line, long col) {
        this.line = line;
        this.col = col;
    }

    public long getLine() {
        return line;
    }

    public long getCol() {
        return col;
    }

    public abstract List<Node> getChildren();
    public abstract String getDescription();
}
