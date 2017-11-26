package ru.spbau.mit.svidchenko.parser;

import java.io.*;

public class Main {
    public static void main(String[] args) {
        /*if (args.length != 1) {
            System.out.println("Wrong number of arguments");
            return;
        }
        SyntaxTree tree;
        try {
             tree = SyntaxTree.buildSyntaxTree(new File(args[0]));
        } catch (IOException e) {
            System.out.println("Can't read file");
            return;
        }*/
        SyntaxTree tree = SyntaxTree.buildSyntaxTree("a * 150\n");
        System.out.println(tree.print());
    }
}
