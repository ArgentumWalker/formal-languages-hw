package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Collections;
import java.util.List;

public class IntegerExpression extends Expression {
    private final long value;

    public IntegerExpression(long value, long line, long col) {
        super(line, col);
        this.value = value;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return "Integer (" + value + ")";
    }
}
