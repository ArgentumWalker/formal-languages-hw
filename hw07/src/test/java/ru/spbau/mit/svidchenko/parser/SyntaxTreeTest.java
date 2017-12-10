package ru.spbau.mit.svidchenko.parser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class SyntaxTreeTest {
    List<String> tests = Arrays.asList(
            "1",
            "0",
            "10",
            "-1",
            "var a",
            "var aba",
            "var a_",
            "var a0",
            "1 + 1",
            "1 - 1",
            "1 * 1",
            "1 / 1",
            "1 % 1",
            "1 || 1",
            "1 && 1",
            "1 == 1",
            "1 < 1",
            "1 <= 1",
            "1 != 1",
            "1 >= 1",
            "1 > 1",
            "(1 + 1)",
            "((1 + 1))",
            "(1 + 1) + (1 + 1)",
            "if (1) {1}",
            "if (1) {1} else {0}",
            "while (0) {1}",
            "var a",
            "var a = 2",
            "var a a = 2",
            "var a = 2 while (a) {a = a - 1}",
            "fun foo() {2}",
            "fun foo() {return 2}",
            "fun foo(a) {return a}",
            "fun foo() {return 2} foo()",
            "fun foo(a) {return a} foo(2)",
            "write 2",
            "var a read a"
    );

    @Test
    public void test() throws Exception {
        for (String test : tests) {
            System.err.println(test);
            SyntaxTree.buildSyntaxTree(test);
            SyntaxTree.buildSyntaxTree(test).print();
        }
    }

}