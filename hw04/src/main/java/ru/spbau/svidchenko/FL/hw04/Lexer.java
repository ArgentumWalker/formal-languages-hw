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
    public static List<Lexem> lex(CharSequence chars) throws LexingException {
        return lex(new Scanner(new StringReader(String.valueOf(chars))));
    }

    public static List<Lexem> lex(InputStream stream) throws LexingException {
        return lex(new Scanner(new InputStreamReader(stream)));
    }

    private static List<Lexem> lex(Scanner scanner) throws LexingException {
        List<Lexem> lexems = new ArrayList<>();
        try {
            Lexem lexem = scanner.yylex();
            while (lexem != null) {
                lexems.add(lexem);
                lexem = scanner.yylex();
            }
        } catch (IOException e) {
            if (lexems.isEmpty()) {
                throw new LexingException();
            } else {
                throw new LexingException(lexems.get(lexems.size() - 1).getColumn(), lexems.get(lexems.size() - 1).getLine());
            }
        }
        return lexems;
    }

    public static class LexingException extends Exception {
        public final long row;
        public final long col;

        private LexingException(){
            this(1, 1);
        }

        private LexingException(long col, long row) {
            this.row = row;
            this.col = col;
        }

    }
}
