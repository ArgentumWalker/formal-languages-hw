package ru.spbau.svidchenko.FL.hw04;

import ru.spbau.svidchenko.FL.hw04.language.*;
import jflex.sym;

%%
%public
%class Scanner
%implements sym

%unicode

%line
%column

%type Lexem

%{
  private <T> LiteralLexem<T> literal(T value) {
    return new LiteralLexem<T>(value, yyline, yycolumn, yytext().length());
  }

  private KeywordLexem keyword(KeywordLexem.Type type) {
    return new KeywordLexem(type, yyline, yycolumn, yytext().length());
  }

  private OperationLexem operation(OperationLexem.Type type) {
    return new OperationLexem(type, yyline, yycolumn, yytext().length());
  }

  private IdentifierLexem identifier(String name) {
    return new IdentifierLexem(name, yyline, yycolumn, yytext().length());
  }

  private SeparatorLexem separator(SeparatorLexem.Type type) {
    return new SeparatorLexem(type, yyline, yycolumn, yytext().length());
  }

  private CommentLexem comment(String comment) {
    return new CommentLexem(comment, yyline, yycolumn, yytext().length());
  }

  private AssignLexem assign() {
    return new AssignLexem(yyline, yycolumn, yytext().length());
  }
%}

/* main character classes */
LineTerminator = \r|\n|\r\n
InputCharacter = [^\r\n]

WhiteSpace = {LineTerminator} | [ \t\f]

/* comments */
Comment = "//" {InputCharacter}* {LineTerminator}?

/* Lexems */
Identifier = [_a-zA-Z][_a-zA-Z0-9]*
Float = -? ({FloatType1} | {FloatType2} | {FloatType3}) {Exponent}?
FloatType1 = [0-9]+ \. [0-9]*
FloatType2 = \. [0-9]+
FloatType3 = [0-9]+
Exponent = [eE] [+-] [0-9]+

%%
<YYINITIAL> {
    /*keywords*/
    "if"    {return keyword(KeywordLexem.Type.IF);}
    "then"  {return keyword(KeywordLexem.Type.THEN);}
    "else"  {return keyword(KeywordLexem.Type.ELSE);}
    "while" {return keyword(KeywordLexem.Type.WHILE);}
    "do"    {return keyword(KeywordLexem.Type.DO);}
    "read"  {return keyword(KeywordLexem.Type.READ);}
    "write" {return keyword(KeywordLexem.Type.WRITE);}
    "begin" {return keyword(KeywordLexem.Type.BEGIN);}
    "end"   {return keyword(KeywordLexem.Type.END);}

    /*Literals*/
    "true"  {return literal((Boolean)true);}
    "false" {return literal((Boolean)false);}
    {Float} {return literal(Double.valueOf(yytext()));}

    /*Operations*/
    "+"  {return operation(OperationLexem.Type.PLUS);}
    "-"  {return operation(OperationLexem.Type.MINUS);}
    "/"  {return operation(OperationLexem.Type.DIVIDE);}
    "*"  {return operation(OperationLexem.Type.MULTIPLY);}
    "%"  {return operation(OperationLexem.Type.REMAINDER);}
    "==" {return operation(OperationLexem.Type.EQ);}
    "!=" {return operation(OperationLexem.Type.NEQ);}
    ">"  {return operation(OperationLexem.Type.GREATER);}
    ">=" {return operation(OperationLexem.Type.GEQ);}
    "<"  {return operation(OperationLexem.Type.LESSER);}
    "<=" {return operation(OperationLexem.Type.LEQ);}
    "&&" {return operation(OperationLexem.Type.AND);}
    "||" {return operation(OperationLexem.Type.OR);}

    /*Separators*/
    "("  {return separator(SeparatorLexem.Type.LEFT_BRACKET);}
    ")"  {return separator(SeparatorLexem.Type.RIGHT_BRACKET);}
    ";"  {return separator(SeparatorLexem.Type.STATEMENT_END);}

    /*Other*/
    ":="            {return assign();}
    {Comment}       {return comment(yytext());}
    {WhiteSpace}    {/* ignore */}
    {Identifier}    {return identifier(yytext());}
}