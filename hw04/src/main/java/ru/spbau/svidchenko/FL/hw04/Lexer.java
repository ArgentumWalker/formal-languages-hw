package ru.spbau.svidchenko.FL.hw04;

import ru.spbau.svidchenko.FL.hw04.Scanner;
import ru.spbau.svidchenko.FL.hw04.language.Lexem;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public class Lexer {
    public static List<Lexem> lex(CharSequence chars) {
        return lex(new Scanner(new StringReader(String.valueOf(chars))));
    }

    public static List<Lexem> lex(InputStream stream) {
        return lex(new Scanner(new InputStreamReader(stream)));
    }

    private static List<Lexem> lex(Scanner scanner) {
        List<Lexem> lexems = new ArrayList<>();
        try {
            Lexem lexem = scanner.yylex();
            while (lexem != null) {
                lexems.add(lexem);
                lexem = scanner.yylex();
            }
        } catch (IOException e) {
            //finish parsing
        }
        return lexems;
    }
}
