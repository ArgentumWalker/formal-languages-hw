grammar lang;

//LEXER RULES
//Literal operations
EqOp: '<' | '<=' | '==' | '>=' | '>' | '!=';
BoolOp: '||' | '&&';
MdOp: '*' | '/' | '%';
PmOp: '+' | '-';
//Array operations
BinArrOp: '<<' | '--';
ArrLength: '.len';

//Complex
Identifier: [a-zA-Z_][0-9a-zA-Z_]*;
Path: [0-9a-zA-Z_.-]+ ('/'[0-9a-zA-Z_.-]+)*;
Literal: '-'?[0-9]+;

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
    ;

import_st: 'import' '"' Path '"';

return_st: 'return' expression;

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
while_st: 'while' '(' integerExpression ')' blockWithBraces;

if_st: 'if' '(' integerExpression ')' blockWithBraces ('else' blockWithBraces)?;

//Expression
expression: integerExpression | arrayExpression | unknownTypeExpression | '(' expression ')';

unknownTypeExpression: functionCall | Identifier | getElement;

//Double expression
integerExpression: binaryExpression | '(' integerExpression ')' | atomicExpression;

atomicExpression: Literal | lenArrayExpression;

binaryExpression: eqExpression | boolExpression | mdExpression | pmExpression;

boolExpression: boolExpressionHelper BoolOp (boolExpressionHelper | boolExpression);
boolExpressionHelper: eqExpressionHelper | eqExpression;

eqExpression: eqExpressionHelper EqOp (eqExpressionHelper | eqExpression);
eqExpressionHelper: pmExpressionHelper | pmExpression;

pmExpression: pmExpressionHelper PmOp (pmExpressionHelper | pmExpression);
pmExpressionHelper: mdExpressionHelper | mdExpression;

mdExpression: mdExpressionHelper MdOp (mdExpressionHelper | mdExpression);
mdExpressionHelper: unknownTypeExpression | atomicExpression | '(' integerExpression ')';

lenArrayExpression: (unknownTypeExpression | arrayBase | '(' arrayExpression ')') ArrLength;

//Array expression
arrayExpression: arrayOperationExpression | unknownTypeExpression | arrayBase | '(' arrayExpression ')';

arrayOperationExpression: arrayOperationExpressionLeft arrayOperationExpressionRight+;
arrayOperationExpressionLeft: unknownTypeExpression | arrayBase;
arrayOperationExpressionRight: BinArrOp (unknownTypeExpression | arrayBase | atomicExpression | '(' expression ')');

//Arrays
arrayBase: '[' (expression (',' expression)*)? ']';
getElement: '(' arrayExpression ')' '['(unknownTypeExpression | integerExpression)']';