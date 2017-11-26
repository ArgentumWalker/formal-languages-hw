package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions;

public class NullParsingException extends RuntimeException {
    @Override
    public String getMessage() {
        return "Nothing to parse";
    }
}
