package ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements;

import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.CodeBlock;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.ParametersNode;

import java.util.Arrays;
import java.util.List;

public class FunctionStatement extends Statement {
    private final String name;
    private final ParametersNode parameters;
    private final CodeBlock codeBlock;

    public FunctionStatement(long line, long col, String name, ParametersNode parameters, CodeBlock codeBlock) {
        super(line, col);
        this.name = name;
        this.parameters = parameters;
        this.codeBlock = codeBlock;
    }

    @Override
    public List<Node> getChildren() {
        return Arrays.asList(parameters, codeBlock);
    }

    @Override
    public String getDescription() {
        return "Function \"" + name + "\" definition";
    }
}
