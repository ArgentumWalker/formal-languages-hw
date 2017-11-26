package ru.spbau.svidchenko.FL.hw04;

import org.junit.Assert;
import org.junit.Test;
import ru.spbau.svidchenko.FL.hw04.language.*;

import java.util.List;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    @SuppressWarnings("all")
    public void primitives() throws Lexer.LexingException {
        //Operation lexems
        Assert.assertEquals(OperationLexem.Type.PLUS, ((OperationLexem)Lexer.lex("+").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.MINUS, ((OperationLexem)Lexer.lex("-").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.DIVIDE, ((OperationLexem)Lexer.lex("/").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.MULTIPLY, ((OperationLexem)Lexer.lex("*").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.REMAINDER, ((OperationLexem)Lexer.lex("%").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.EQ, ((OperationLexem)Lexer.lex("==").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.NEQ, ((OperationLexem)Lexer.lex("!=").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.LESSER, ((OperationLexem)Lexer.lex("<").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.LEQ, ((OperationLexem)Lexer.lex("<=").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.GREATER, ((OperationLexem)Lexer.lex(">").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.GEQ, ((OperationLexem)Lexer.lex(">=").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.AND, ((OperationLexem)Lexer.lex("&&").get(0)).getType());
        Assert.assertEquals(OperationLexem.Type.OR, ((OperationLexem)Lexer.lex("||").get(0)).getType());
        //Separators
        Assert.assertEquals(SeparatorLexem.Type.LEFT_BRACKET, ((SeparatorLexem)Lexer.lex("(").get(0)).getType());
        Assert.assertEquals(SeparatorLexem.Type.RIGHT_BRACKET, ((SeparatorLexem)Lexer.lex(")").get(0)).getType());
        Assert.assertEquals(SeparatorLexem.Type.STATEMENT_END, ((SeparatorLexem)Lexer.lex(";").get(0)).getType());
        //Keywords
        Assert.assertEquals(KeywordLexem.Type.IF, ((KeywordLexem)Lexer.lex("if").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.THEN, ((KeywordLexem)Lexer.lex("then").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.ELSE, ((KeywordLexem)Lexer.lex("else").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.WHILE, ((KeywordLexem)Lexer.lex("while").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.DO, ((KeywordLexem)Lexer.lex("do").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.BEGIN, ((KeywordLexem)Lexer.lex("begin").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.END, ((KeywordLexem)Lexer.lex("end").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.READ, ((KeywordLexem)Lexer.lex("read").get(0)).getType());
        Assert.assertEquals(KeywordLexem.Type.WRITE, ((KeywordLexem)Lexer.lex("write").get(0)).getType());
        //Identifiers
        Assert.assertEquals("t", ((IdentifierLexem)Lexer.lex("t").get(0)).getName());
        Assert.assertEquals("tttt", ((IdentifierLexem)Lexer.lex("tttt").get(0)).getName());
        Assert.assertEquals("abcd", ((IdentifierLexem)Lexer.lex("abcd").get(0)).getName());
        Assert.assertEquals("a999", ((IdentifierLexem)Lexer.lex("a999").get(0)).getName());
        Assert.assertEquals("ab_d", ((IdentifierLexem)Lexer.lex("ab_d").get(0)).getName());
        Assert.assertEquals("__abcd", ((IdentifierLexem)Lexer.lex("__abcd").get(0)).getName());
        //Literals
        Assert.assertEquals(true, ((LiteralLexem<Boolean>)Lexer.lex("true").get(0)).getValue());
        Assert.assertEquals(false, ((LiteralLexem<Boolean>)Lexer.lex("false").get(0)).getValue());
        Assert.assertEquals(1.0, ((LiteralLexem<Double>)Lexer.lex("1.0").get(0)).getValue(), 1e-10);
        Assert.assertEquals(1.0, ((LiteralLexem<Double>)Lexer.lex("1.").get(0)).getValue(), 1e-10);
        Assert.assertEquals(1.0, ((LiteralLexem<Double>)Lexer.lex("1").get(0)).getValue(), 1e-10);
        Assert.assertEquals(0.1, ((LiteralLexem<Double>)Lexer.lex("1e-1").get(0)).getValue(), 1e-10);
        Assert.assertEquals(1.0, ((LiteralLexem<Double>)Lexer.lex("0.1e+1").get(0)).getValue(), 1e-10);
        Assert.assertEquals(-1.0, ((LiteralLexem<Double>)Lexer.lex("-1").get(0)).getValue(), 1e-10);
        Assert.assertEquals(0.1, ((LiteralLexem<Double>)Lexer.lex(".1").get(0)).getValue(), 1e-10);
        //Other
        Assert.assertTrue(Lexer.lex(":=").get(0) instanceof AssignLexem);
        Assert.assertEquals("//abcd", ((CommentLexem)Lexer.lex("//abcd").get(0)).getText());
    }

    @Test
    @SuppressWarnings("all")
    public void integral_test() throws Exception {
        List<Lexem> lexems = Lexer.lex(
                "read x; y + 1"
        );
        Assert.assertEquals(KeywordLexem.Type.READ, ((KeywordLexem)lexems.get(0)).getType());
        Assert.assertEquals("x", ((IdentifierLexem)lexems.get(1)).getName());
        Assert.assertEquals(SeparatorLexem.Type.STATEMENT_END, ((SeparatorLexem)lexems.get(2)).getType());
        Assert.assertEquals("y", ((IdentifierLexem)lexems.get(3)).getName());
        Assert.assertEquals(OperationLexem.Type.PLUS, ((OperationLexem)lexems.get(4)).getType());
        Assert.assertEquals(1.0, ((LiteralLexem<Double>)lexems.get(5)).getValue(), 1e-10);
    }
}