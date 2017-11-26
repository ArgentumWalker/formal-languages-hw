package ru.spbau.svidchenko.FL.hw04;


import ru.spbau.svidchenko.FL.hw04.language.Lexem;

import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            return;
        }
        try (InputStream input = new FileInputStream(args[0])) {
            for (Lexem lexem : Lexer.lex(input)) {
                System.out.println(lexem.toString());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error when reading file");
        } catch (Lexer.LexingException e) {
            System.out.println("Syntax error at " + e.row + ":" + e.col);
        }
    }
}
