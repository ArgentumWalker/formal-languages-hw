package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.ArgumentsNode;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.ParametersNode;

import java.util.Collections;
import java.util.List;

public class FunctionCallExpression extends Expression {
    private final ArgumentsNode parameters;
    private final String identifier;

    public FunctionCallExpression(String id, ArgumentsNode parameters, long line, long col) {
        super(line, col);
        this.parameters = parameters;
        this.identifier = id;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.singletonList((Node)parameters);
    }

    @Override
    public String getDescription() {
        return "Call function \"" + identifier + "\"";
    }
}
