package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;

import java.util.List;
import java.util.stream.Collectors;

public class CodeBlock extends Node {
    private final List<Statement> statements;

    public CodeBlock(List<Statement> statements, long line, long col) {
        super(line, col);
        this.statements = statements;
    }

    @Override
    public List<Node> getChildren() {
        return statements.stream().map(st -> (Node)st).collect(Collectors.toList());
    }

    @Override
    public String getDescription() {
        return "CodeBlock";
    }
}
