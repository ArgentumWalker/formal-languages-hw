package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.List;
import java.util.stream.Collectors;

public class ArrayExpression extends Expression {
    private final List<Expression> children;

    public ArrayExpression(List<Expression> children, long line, long col) {
        super(line, col);
        this.children = children;
    }

    @Override
    public List<Node> getChildren() {
        return children.stream().map(expr -> (Node)expr).collect(Collectors.toList());
    }

    @Override
    public String getDescription() {
        return "Array initializer";
    }
}
