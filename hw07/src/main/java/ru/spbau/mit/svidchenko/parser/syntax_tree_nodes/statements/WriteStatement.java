package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;

import java.util.Collections;
import java.util.List;

public class WriteStatement extends Statement {
    private final Expression expression;

    public WriteStatement(long line, long col, Expression expression) {
        super(line, col);
        this.expression = expression;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.singletonList(expression);
    }

    @Override
    public String getDescription() {
        return "Write";
    }
}
