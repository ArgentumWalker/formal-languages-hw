package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Collections;
import java.util.List;

public class ArrayLenExpression extends Expression {
    private final Expression arrayExpr;

    public ArrayLenExpression(Expression arrayExpr, long line, long col) {
        super(line, col);
        this.arrayExpr = arrayExpr;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.singletonList(arrayExpr);
    }

    @Override
    public String getDescription() {
        return "Length of list";
    }
}
