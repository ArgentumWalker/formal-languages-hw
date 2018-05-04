package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Collections;
import java.util.List;

public class VariableCallExpression extends Expression {
    private final String name;

    public VariableCallExpression(String name, long line, long col) {
        super(line, col);
        this.name = name;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return "Call variable \"" + name + "\"";
    }
}
