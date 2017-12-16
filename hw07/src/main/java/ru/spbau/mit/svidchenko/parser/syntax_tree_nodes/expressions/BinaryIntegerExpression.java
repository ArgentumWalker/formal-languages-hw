package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Arrays;
import java.util.List;

public class BinaryIntegerExpression extends Expression {
    private final String operation;
    private final Expression left;
    private final Expression right;

    public BinaryIntegerExpression(Expression left, String op, Expression right, long line, long col) {
        super(line, col);
        this.operation = op;
        this.left = left;
        this.right = right;
    }

    @Override
    public List<Node> getChildren() {
        return Arrays.asList(left, right);
    }

    @Override
    public String getDescription() {
        return "Operation " + operation;
    }
}
