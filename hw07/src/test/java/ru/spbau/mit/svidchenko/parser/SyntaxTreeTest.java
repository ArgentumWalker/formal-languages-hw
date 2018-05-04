package ru.spbau.mit.svidchenko.parser;

import org.junit.Test;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.ParsingException;
import ru.spbau.mit.svidchenko.parser.syntax_tree_nodes.exceptions.UnknownParsingException;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.fail;

public class SyntaxTreeTest {
    private List<String> okTests = Arrays.asList(
            "(1);",
            "(0);",
            "(10);",
            "(-1);",
            "var a;",
            "var aba;",
            "var a_;",
            "var a0;",
            "(1 + 1);",
            "(1 - 1);",
            "(1 * 1);",
            "(1 / 1);",
            "(1 % 1);",
            "(1 || 1);",
            "(1 && 1);",
            "(1 == 1);",
            "(1 < 1);",
            "(1 <= 1);",
            "(1 != 1);",
            "(1 >= 1);",
            "(1 > 1);",
            "(1 + 1);",
            "((1 + 1));",
            "((1 + 1) + (1 + 1));",
            "if (1) {(1);}",
            "if (1) {(1);} else {(1);}",
            "while (0) {(1);}",
            "var a;",
            "var a := 2;",
            "var a; a := 2;",
            "var a := 2; while (a) {a := a - 1;}",
            "fun foo() {(2);}",
            "fun foo() {return 2;}",
            "fun foo(a) {return a;}",
            "fun foo() {return 2;} foo();",
            "fun foo(a) {return a;} foo(2);",
            "read a;",
            "write a + 1;"
    );

    private List<String> failTests = Arrays.asList(
            "var a := 2", //Потеря точки с запятой
            "var a := 2 a;", //Потеря точки с запятой (или лишний символ)
            "var a := 2; ,,,,,,,", //Некорректный текст в конце кода
            "if (1) {(1);} else {;}", //Пустой statement
            "if (1 {(1);} else {(0);}", //Незакрытая скобка
            "if 1 {(1);} else {(0);}", //Отсутствие скобок
            "if (1) {(1);} else", //Нет блока
            "if (1) else {(0);}", //Нет блока
            "while (1 {(1);}", //Незакрытая скобка
            "while 1 {(1);}", //Отсутствие скобок
            "while (1)", //Нет блока
            "read a + 1;", //Чтение выражения
            "f(a b c d);", //Нет запятых
            "--1;", //Нельзя два унарных минуса
            "();", //Так тоже нельзя
            "2 ** 3;", //Несуществующая операция
            "2 *;", //Нехватает аргумента
            "* 3;", //Нехватает аргумента
            "fun f() { read x ;\nx := 1 ;\n y := 12 ;\ntw\nx (); }"
    );

    @Test
    public void test() throws Exception {
        for (String test : okTests) {
            System.err.println(test);
            SyntaxTree.buildSyntaxTree(test);
            SyntaxTree.buildSyntaxTree(test).print();
        }
        for (String test : failTests) {
            try {
                System.err.println(test);
                SyntaxTree.buildSyntaxTree(test);
                fail();
            } catch (ParsingException e) {
                //Ok
            }
        }
    }

}