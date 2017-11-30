package ru.spbau.svidchenko.FL.hw04.language;

public class CommentLexem extends Lexem{
    protected final String comment;

    public CommentLexem(String comment, long line, long column, long length) {
        super(line, column, length);
        this.comment = comment;
    }

    public String getText() {
        return comment;
    }

    @Override
    public String toString() {
        return "Comment(" + comment + ")" + super.toString();
    }
}
