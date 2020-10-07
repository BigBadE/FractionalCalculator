parser grammar FractionParser;

options { tokenVocab=FractionLexer; }

equation
: (expression+ (ADDITION|SUBTRACTION|DIVISION|MULTIPLICATION|POWER) expression+)+ EOF;

expression
: (operation|parenthesis);

value
: (NUMBER|parenthesis);

parenthesis
: OPEN_PARENTHESES expression CLOSED_PARENTHESES;

operation
: (addition|subtraction|multiplication|division|power);

addition
: value ADDITION (NUMBER|parenthesis);

subtraction
: value SUBTRACTION (NUMBER|parenthesis);

division
: value DIVISION (NUMBER|parenthesis);

multiplication
: value MULTIPLICATION value
| NUMBER parenthesis
| parenthesis NUMBER;

power
: value POWER value;
