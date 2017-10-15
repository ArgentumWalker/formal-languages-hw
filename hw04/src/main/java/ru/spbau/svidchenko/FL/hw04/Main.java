package ru.spbau.svidchenko.FL.hw04;


import ru.spbau.svidchenko.FL.hw04.language.Lexem;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
}
