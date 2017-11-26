package ru.spbau.mit.svidchenko.parser;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import ru.spbau.mit.parser.langParser;
import ru.spbau.mit.parser.langVisitor;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Expression;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Node;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.Statement;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.NullParsingException;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.UnknownParsingException;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.expressions.*;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.ArgumentsNode;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.CodeBlock;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.other.ParametersNode;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.statements.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SyntaxTreeBuilder extends AbstractParseTreeVisitor<Node> implements langVisitor<Node> {

    @Override
    public CodeBlock visitFile(langParser.FileContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return visitBlock(ctx.block());
    }

    @Override
    public CodeBlock visitBlock(langParser.BlockContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        List<Statement> statements = new ArrayList<>();
        for (langParser.StatementContext context : ctx.statement()) {
            statements.add(visitStatement(context));
        }
        return new CodeBlock(statements, ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public CodeBlock visitBlockWithBraces(langParser.BlockWithBracesContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return visitBlock(ctx.block());
    }

    @Override
    public Statement visitStatement(langParser.StatementContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.import_st() != null) {
            return visitImport_st(ctx.import_st());
        }
        if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        }
        if (ctx.assignment() != null) {
            return visitAssignment(ctx.assignment());
        }
        if (ctx.function() != null) {
            return visitFunction(ctx.function());
        }
        if (ctx.valueAssignment() != null) {
            return visitValueAssignment(ctx.valueAssignment());
        }
        if (ctx.if_st() != null) {
            return visitIf_st(ctx.if_st());
        }
        if (ctx.while_st() != null) {
            return visitWhile_st(ctx.while_st());
        }
        if (ctx.return_st() != null) {
            return visitReturn_st(ctx.return_st());
        }
        if (ctx.variable() != null) {
            return visitVariable(ctx.variable());
        }
        return null;
    }

    @Override
    public ImportStatement visitImport_st(langParser.Import_stContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new ImportStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(), ctx.Path().getText());
    }

    @Override
    public ReturnStatement visitReturn_st(langParser.Return_stContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new ReturnStatement(visitExpression(ctx.expression()), ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public FunctionStatement visitFunction(langParser.FunctionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new FunctionStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                ctx.Identifier().getText(), visitParameters(ctx.parameters()), visitBlockWithBraces(ctx.blockWithBraces()));
    }

    @Override
    public ParametersNode visitParameters(langParser.ParametersContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.Identifier() != null) {
            return new ParametersNode(ctx.Identifier().stream().map(TerminalNode::getText).collect(Collectors.toList()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new ParametersNode(Collections.emptyList(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public FunctionCallExpression visitFunctionCall(langParser.FunctionCallContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new FunctionCallExpression(ctx.Identifier().getText(), visitArguments(ctx.arguments()),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArgumentsNode visitArguments(langParser.ArgumentsContext ctx) {
        if (ctx.expression() != null) {
            return new ArgumentsNode(ctx.expression().stream().map(this::visitExpression).collect(Collectors.toList()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new ArgumentsNode(Collections.emptyList(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public VariableStatement visitVariable(langParser.VariableContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.expression() != null) {
            return new VariableStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                    ctx.Identifier().getText(), visitExpression(ctx.expression()));
        }
        return new VariableStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                ctx.Identifier().getText(), null);
    }

    @Override
    public VariableValueAssignStatement visitValueAssignment(langParser.ValueAssignmentContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new VariableValueAssignStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                ctx.Identifier().getText(), visitExpression(ctx.expression()));
    }

    @Override
    public VariableAssignStatement visitAssignment(langParser.AssignmentContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new VariableAssignStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                ctx.Identifier().getText(), visitExpression(ctx.expression()));
    }

    @Override
    public WhileStatement visitWhile_st(langParser.While_stContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new WhileStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                visitIntegerExpression(ctx.integerExpression()), visitBlockWithBraces(ctx.blockWithBraces()));
    }

    @Override
    public IfStatement visitIf_st(langParser.If_stContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.blockWithBraces().size() > 1) {
            return new IfStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                    visitIntegerExpression(ctx.integerExpression()),
                    visitBlockWithBraces(ctx.blockWithBraces().get(0)),
                    visitBlockWithBraces(ctx.blockWithBraces().get(1)));
        }
        return new IfStatement(ctx.start.getLine(), ctx.start.getCharPositionInLine(),
                visitIntegerExpression(ctx.integerExpression()),
                visitBlockWithBraces(ctx.blockWithBraces().get(0)),
                null);
    }

    @Override
    public Expression visitExpression(langParser.ExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.expression() != null) {
            return visitExpression(ctx.expression());
        }
        if (ctx.arrayExpression() != null) {
            return visitArrayExpression(ctx.arrayExpression());
        }
        if (ctx.integerExpression() != null) {
            return visitIntegerExpression(ctx.integerExpression());
        }
        if (ctx.unknownTypeExpression() != null) {
            return visitUnknownTypeExpression(ctx.unknownTypeExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitUnknownTypeExpression(langParser.UnknownTypeExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.functionCall() != null) {
            return visitFunctionCall(ctx.functionCall());
        }
        if (ctx.Identifier() != null) {
            return new VariableCallExpression(ctx.Identifier().getText(), ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitIntegerExpression(langParser.IntegerExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.integerExpression() != null) {
            return visitIntegerExpression(ctx.integerExpression());
        }
        if (ctx.atomicExpression() != null) {
            return visitAtomicExpression(ctx.atomicExpression());
        }
        if (ctx.binaryExpression() != null) {
            return visitBinaryExpression(ctx.binaryExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitAtomicExpression(langParser.AtomicExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.Literal() != null) {
            return new IntegerExpression(Long.decode(ctx.Literal().getText()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.lenArrayExpression() != null) {
            return visitLenArrayExpression(ctx.lenArrayExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public BinaryIntegerExpression visitBinaryExpression(langParser.BinaryExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.boolExpression() != null) {
            return visitBoolExpression(ctx.boolExpression());
        }
        if (ctx.eqExpression() != null) {
            return visitEqExpression(ctx.eqExpression());
        }
        if (ctx.mdExpression() != null) {
            return visitMdExpression(ctx.mdExpression());
        }
        if (ctx.pmExpression() != null) {
            return visitPmExpression(ctx.pmExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public BinaryIntegerExpression visitBoolExpression(langParser.BoolExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.boolExpression() != null) {
            return new BinaryIntegerExpression(
                    visitBoolExpressionHelper(ctx.boolExpressionHelper().get(0)),
                    ctx.BoolOp().getText(),
                    visitBoolExpression(ctx.boolExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new BinaryIntegerExpression(
                visitBoolExpressionHelper(ctx.boolExpressionHelper().get(0)),
                ctx.BoolOp().getText(),
                visitBoolExpressionHelper(ctx.boolExpressionHelper().get(1)),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitBoolExpressionHelper(langParser.BoolExpressionHelperContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.eqExpression() != null) {
            return visitEqExpression(ctx.eqExpression());
        }
        return visitEqExpressionHelper(ctx.eqExpressionHelper());
    }

    @Override
    public BinaryIntegerExpression visitEqExpression(langParser.EqExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.eqExpression() != null) {
            return new BinaryIntegerExpression(
                    visitEqExpressionHelper(ctx.eqExpressionHelper().get(0)),
                    ctx.EqOp().getText(),
                    visitEqExpression(ctx.eqExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new BinaryIntegerExpression(
                visitEqExpressionHelper(ctx.eqExpressionHelper().get(0)),
                ctx.EqOp().getText(),
                visitEqExpressionHelper(ctx.eqExpressionHelper().get(1)),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitEqExpressionHelper(langParser.EqExpressionHelperContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.pmExpression() != null) {
            return visitPmExpression(ctx.pmExpression());
        }
        return visitPmExpressionHelper(ctx.pmExpressionHelper());
    }

    @Override
    public BinaryIntegerExpression visitPmExpression(langParser.PmExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.pmExpression() != null) {
            return new BinaryIntegerExpression(
                    visitPmExpressionHelper(ctx.pmExpressionHelper().get(0)),
                    ctx.PmOp().getText(),
                    visitPmExpression(ctx.pmExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new BinaryIntegerExpression(
                visitPmExpressionHelper(ctx.pmExpressionHelper().get(0)),
                ctx.PmOp().getText(),
                visitPmExpressionHelper(ctx.pmExpressionHelper().get(1)),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitPmExpressionHelper(langParser.PmExpressionHelperContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.mdExpression() != null) {
            return visitMdExpression(ctx.mdExpression());
        }
        return visitMdExpressionHelper(ctx.mdExpressionHelper());
    }

    @Override
    public BinaryIntegerExpression visitMdExpression(langParser.MdExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.mdExpression() != null) {
            return new BinaryIntegerExpression(
                    visitMdExpressionHelper(ctx.mdExpressionHelper().get(0)),
                    ctx.MdOp().getText(),
                    visitMdExpression(ctx.mdExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        return new BinaryIntegerExpression(
                visitMdExpressionHelper(ctx.mdExpressionHelper().get(0)),
                ctx.MdOp().getText(),
                visitMdExpressionHelper(ctx.mdExpressionHelper().get(1)),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitMdExpressionHelper(langParser.MdExpressionHelperContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.atomicExpression() != null) {
            return visitAtomicExpression(ctx.atomicExpression());
        }
        if (ctx.integerExpression() != null) {
            return visitIntegerExpression(ctx.integerExpression());
        }
        if (ctx.unknownTypeExpression() != null) {
            return visitUnknownTypeExpression(ctx.unknownTypeExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArrayLenExpression visitLenArrayExpression(langParser.LenArrayExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.arrayExpression() != null) {
            return new ArrayLenExpression(visitArrayExpression(ctx.arrayExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.unknownTypeExpression() != null) {
            return new ArrayLenExpression(visitUnknownTypeExpression(ctx.unknownTypeExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.arrayBase() != null) {
            return new ArrayLenExpression(visitArrayBase(ctx.arrayBase()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitArrayExpression(langParser.ArrayExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.arrayExpression() != null) {
            return visitArrayExpression(ctx.arrayExpression());
        }
        if (ctx.unknownTypeExpression() != null) {
            return visitUnknownTypeExpression(ctx.unknownTypeExpression());
        }
        if (ctx.arrayBase() != null) {
            return visitArrayBase(ctx.arrayBase());
        }
        if (ctx.arrayOperationExpression() != null) {
            return visitArrayOperationExpression(ctx.arrayOperationExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArrayOperationExpression visitArrayOperationExpression(langParser.ArrayOperationExpressionContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new ArrayOperationExpression(
                visitArrayOperationExpressionLeft(ctx.arrayOperationExpressionLeft()),
                ctx.arrayOperationExpressionRight().stream().map(this::visitArrayOperationExpressionRight)
                        .collect(Collectors.toList()),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public Expression visitArrayOperationExpressionLeft(langParser.ArrayOperationExpressionLeftContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.arrayBase() != null) {
            return visitArrayBase(ctx.arrayBase());
        }
        if (ctx.unknownTypeExpression() != null) {
            return visitUnknownTypeExpression(ctx.unknownTypeExpression());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArrayOperationExpression.Operation visitArrayOperationExpressionRight(
            langParser.ArrayOperationExpressionRightContext ctx
    ) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.arrayBase() != null) {
            return new ArrayOperationExpression.Operation(ctx.BinArrOp().getText(), visitArrayBase(ctx.arrayBase()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.unknownTypeExpression() != null) {
            return new ArrayOperationExpression.Operation(ctx.BinArrOp().getText(),
                    visitUnknownTypeExpression(ctx.unknownTypeExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.atomicExpression() != null) {
            return new ArrayOperationExpression.Operation(ctx.BinArrOp().getText(),
                    visitAtomicExpression(ctx.atomicExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.expression() != null) {
            return new ArrayOperationExpression.Operation(ctx.BinArrOp().getText(),
                    visitExpression(ctx.expression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArrayExpression visitArrayBase(langParser.ArrayBaseContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        return new ArrayExpression(ctx.expression().stream().map(this::visitExpression).collect(Collectors.toList()),
                ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }

    @Override
    public ArrayGetExpression visitGetElement(langParser.GetElementContext ctx) {
        if (ctx == null) {
            throw new NullParsingException();
        }
        if (ctx.unknownTypeExpression() != null) {
            return new ArrayGetExpression(visitArrayExpression(ctx.arrayExpression()),
                    visitUnknownTypeExpression(ctx.unknownTypeExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        if (ctx.integerExpression() != null) {
            return new ArrayGetExpression(visitArrayExpression(ctx.arrayExpression()),
                    visitIntegerExpression(ctx.integerExpression()),
                    ctx.start.getLine(), ctx.start.getCharPositionInLine());
        }
        throw new UnknownParsingException(ctx.start.getLine(), ctx.start.getCharPositionInLine());
    }
}
