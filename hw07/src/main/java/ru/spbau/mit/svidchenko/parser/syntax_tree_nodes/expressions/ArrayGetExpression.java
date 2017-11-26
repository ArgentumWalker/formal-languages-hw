package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Arrays;
import java.util.List;

public class ArrayGetExpression extends Expression {
    private final Expression arrayExpr;
    private final Expression indexExpr;

    public ArrayGetExpression(Expression arrayExpr, Expression index, long line, long col) {
        super(line, col);
        this.arrayExpr = arrayExpr;
        this.indexExpr = index;
    }

    @Override
    public List<Node> getChildren() {
        return Arrays.asList(arrayExpr, indexExpr);
    }

    @Override
    public String getDescription() {
        return "Get element";
    }
}
