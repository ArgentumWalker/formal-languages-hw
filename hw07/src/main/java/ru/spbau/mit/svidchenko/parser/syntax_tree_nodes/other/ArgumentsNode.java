package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.FakeNode;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.List;
import java.util.stream.Collectors;

public class ArgumentsNode extends FakeNode {
    private final List<Expression> args;

    public ArgumentsNode(List<Expression> args, long line, long col) {
        super(line, col);
        this.args = args;
    }

    @Override
    public List<Node> getChildren() {
        return args.stream().map(expr -> (Node)expr).collect(Collectors.toList());
    }

    @Override
    public String getDescription() {
        return "Arguments";
    }
}
