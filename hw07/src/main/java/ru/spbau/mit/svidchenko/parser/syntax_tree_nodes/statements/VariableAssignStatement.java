package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;

import java.util.Collections;
import java.util.List;

public class VariableAssignStatement extends Statement {
    private final String name;
    private final Expression expression;

    public VariableAssignStatement(long line, long col, String name, Expression expression) {
        super(line, col);
        this.name = name;
        this.expression = expression;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.singletonList(expression);
    }

    @Override
    public String getDescription() {
        return "Assign \"" + name + "\"";
    }
}
