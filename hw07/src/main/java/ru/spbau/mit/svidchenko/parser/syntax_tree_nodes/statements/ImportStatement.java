package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;

import java.util.Collections;
import java.util.List;

public class ImportStatement extends Statement {
    private final String path;

    public ImportStatement(long line, long col, String path) {
        super(line, col);
        this.path = path;
    }

    @Override
    public List<Node> getChildren() {
        return Collections.emptyList();
    }

    @Override
    public String getDescription() {
        return "Import \"" + path + "\"";
    }
}
