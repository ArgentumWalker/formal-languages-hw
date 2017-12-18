package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions;

public class SomeParsingErrorsException extends ParsingException {
    @Override
    public String getMessage() {
        return "There are some syntax errors in code.";
    }
}
