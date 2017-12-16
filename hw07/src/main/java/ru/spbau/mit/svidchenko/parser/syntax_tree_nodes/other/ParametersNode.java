package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.FakeNode;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;

import java.util.Collections;
import java.util.List;

public class ParametersNode extends FakeNode {
    private final List<String> names;

    public ParametersNode(List<String> names, long line, long col) {
        super(line, col);
        this.names = names;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        StringBuilder res = new StringBuilder("Parameters (");
        names.forEach(name -> res.append(name).append(", "));
        res.delete(res.length() - 2, res.length());
        res.append(")");
        return res.toString();
    }
}
