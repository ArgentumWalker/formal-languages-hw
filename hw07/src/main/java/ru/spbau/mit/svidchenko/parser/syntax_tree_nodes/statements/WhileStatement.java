package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.CodeBlock;

import java.util.Arrays;
import java.util.List;

public class WhileStatement extends Statement {
    private final Expression whileExpr;
    private final CodeBlock doBlock;

    public WhileStatement(long line, long col, Expression whileExpr, CodeBlock doBlock) {
        super(line, col);
        this.whileExpr = whileExpr;
        this.doBlock = doBlock;
    }

    @Override
    public List<Node> getChildren() {
        return Arrays.asList(whileExpr, doBlock);
    }

    @Override
    public String getDescription() {
        return "While";
    }
}
