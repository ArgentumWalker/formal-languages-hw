package ru.spbau.mit.svidchenko.parser;

import org.antlr.v4.runtime.BufferedTokenStream;
import org.antlr.v4.runtime.CharStreams;
import ru.spbau.mit.parser.langLexer;
import ru.spbau.mit.parser.langParser;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.SomeParsingErrorsException;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.UnknownParsingException;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.CodeBlock;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

public class SyntaxTree {
    private final CodeBlock root;

    private SyntaxTree(CodeBlock rootNode) {
        root = rootNode;
    }

    public String print() {
        StringBuilder res = new StringBuilder();
        StringBuilder spaces = new StringBuilder();
        __print(res, root, spaces);
        return res.toString();
    }

    private void __print(StringBuilder to, Node node, StringBuilder spaces) {
        to.append(spaces);
        to.append(node.getDescription())
                .append("<")
                .append(node.getLine())
                .append("::")
                .append(node.getCol())
                .append("> {\n");
        spaces.append("  ");
        for (Node child : node.getChildren()) {
            __print(to, child, spaces);
        }
        spaces.delete(spaces.length() - 2, spaces.length());
        to.append(spaces)
                .append("}\n");
    }

    public static SyntaxTree buildSyntaxTree(String code) {
        langLexer lexer = new langLexer(CharStreams.fromString(code));
        langParser parser = new langParser(new BufferedTokenStream(lexer));
        SyntaxTreeBuilder builder = new SyntaxTreeBuilder();
        SyntaxTree result = new SyntaxTree(builder.visitFile(parser.file()));
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new SomeParsingErrorsException();
        }
        return result;
    }

    public static SyntaxTree buildSyntaxTree(File file) throws IOException {
        langLexer lexer = new langLexer(CharStreams.fromPath(Paths.get(file.getAbsolutePath())));
        langParser parser = new langParser(new BufferedTokenStream(lexer));
        SyntaxTreeBuilder builder = new SyntaxTreeBuilder();
        SyntaxTree result = new SyntaxTree(builder.visitFile(parser.file()));
        if (parser.getNumberOfSyntaxErrors() > 0) {
            throw new SomeParsingErrorsException();
        }
        return result;
    }
}
