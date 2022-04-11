
grammar Test;

@header {
  package com.linfafa.test.antlr4.parser;
}

stmt : expr;

expr : expr NUL expr   # Mul
     | expr ADD expr   # Add
     | expr DIV expr   # Div
     | expr MIN expr   # Min
     | INT             # Int
     ;

NUL : '*';
ADD : '+';
DIV : '/';
MIN : '-';

INT : [0-9]+;
WS  : [ \t\u000C\r\n]+ -> skip;

SHEBANG : '#' '!' ~('\n'|'\r')* -> channel(HIDDEN);