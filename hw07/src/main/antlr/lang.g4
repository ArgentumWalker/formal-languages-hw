grammar lang;

//LEXER RULES
EqOp: '<' | '<=' | '==' | '>=' | '>' | '!=';
BoolOp: '||' | '&&';
MdOp: '*' | '/' | '%';
PmOp: '+' | '-';

//Complex
Identifier: [a-zA-Z_][0-9a-zA-Z_]*;
Literal: '-'?[1-9][0-9]* | '0';
Path: [0-9a-zA-Z_.-]+ ('/'[0-9a-zA-Z_.-]+)*;


//Skip
LineComment
    :   '//' ~[\r\n]*
        -> skip
    ;

Whitespace : (' ' | '\t' | '\r'| '\n') -> skip;

//PARSER RULES
//Base
file: block;

block: statement*;

blockWithBraces: '{' block '}';

statement
    : function
    | variable
    | expression
    | while_st
    | if_st
    | assignment
    | valueAssignment
    | return_st
    | import_st
    | io_st
    ;

return_st: 'return' expression;
import_st: 'import' '"' Path '"';

//IO
io_st: read | write;
read: 'read' Identifier;
write: 'write' expression;

//Function

function: 'fun' Identifier '(' (parameters)? ')' blockWithBraces;

parameters: Identifier (',' Identifier)*;

functionCall: Identifier '(' (arguments)? ')';

arguments: expression (',' expression)*;

//Variables
variable: 'var' Identifier ('=' expression)?;

valueAssignment: Identifier '=' expression;

assignment: Identifier ':=' expression;

//Complex statements
while_st: 'while' '(' expression ')' blockWithBraces;

if_st: 'if' '(' expression ')' blockWithBraces ('else' blockWithBraces)?;

//Expression
expression: binaryExpression | '(' expression ')' | atomicExpression;

atomicExpression: functionCall | Identifier | Literal;

binaryExpression: eqExpression | boolExpression | mdExpression | pmExpression;

boolExpression: boolExpressionHelper BoolOp (boolExpressionHelper | boolExpression);
boolExpressionHelper: eqExpressionHelper | eqExpression;

eqExpression: eqExpressionHelper EqOp (eqExpressionHelper | eqExpression);
eqExpressionHelper: pmExpressionHelper | pmExpression;

pmExpression: pmExpressionHelper PmOp (pmExpressionHelper | pmExpression);
pmExpressionHelper: mdExpressionHelper | mdExpression;

mdExpression: mdExpressionHelper MdOp (mdExpressionHelper | mdExpression);
mdExpressionHelper: atomicExpression | '(' expression ')';



