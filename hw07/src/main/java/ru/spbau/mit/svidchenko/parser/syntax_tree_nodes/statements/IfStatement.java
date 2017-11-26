package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.CodeBlock;

import java.util.Arrays;
import java.util.List;

public class IfStatement extends Statement {
    private final Expression ifExpr;
    private final CodeBlock thenBlock;
    private final CodeBlock elseBlock;

    public IfStatement(long line, long col, Expression ifExpr, CodeBlock thenBlock, CodeBlock elseBlock) {
        super(line, col);
        this.ifExpr = ifExpr;
        this.thenBlock = thenBlock;
        this.elseBlock = elseBlock;
    }

    @Override
    public List<Node> getChildren() {
        return elseBlock == null ? Arrays.asList(ifExpr, thenBlock) : Arrays.asList(ifExpr, thenBlock, elseBlock);
    }

    @Override
    public String getDescription() {
        return "If";
    }
}
