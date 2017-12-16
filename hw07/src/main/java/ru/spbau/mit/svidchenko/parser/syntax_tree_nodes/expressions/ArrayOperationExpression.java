package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ArrayOperationExpression extends Expression {
    private final List<Operation> operations;
    private final Expression base;

    public ArrayOperationExpression(Expression baseArray, List<Operation> operations, long line, long col) {
        super(line, col);
        base = baseArray;
        this.operations = operations;
    }

    @Override
    public List<Node> getChildren() {
        List<Node> nodes = Arrays.asList(base);
        operations.forEach(nodes::add);
        return nodes;
    }

    @Override
    public String getDescription() {
        return "Array operation";
    }

    public static class Operation extends Node {
        private final String operation;
        private final Expression expression;

        public Operation(String op, Expression right, long line, long col) {
            super(line, col);
            this.expression = right;
            this.operation = op;
        }

        @Override
        public List<Node> getChildren() {
            return Collections.singletonList(expression);
        }

        @Override
        public String getDescription() {
            return "Apply " + operation;
        }
    }
}
