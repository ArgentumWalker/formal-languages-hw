package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions;

public class UnknownParsingException extends RuntimeException {
    private final long line;
    private final long col;

    public UnknownParsingException(long line, long col) {
        this.line = line;
        this.col = col;
    }

    @Override
    public String getMessage() {
        return "Parsing failure at " + line + "::" + col;
    }
}
