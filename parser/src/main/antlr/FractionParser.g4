parser grammar FractionParser;

options { tokenVocab=FractionLexer; }

input
: operation EOF
| equation EOF
| assignment EOF
| value EOF
| EOF;

equation
: operation EQUALS operation?;

assignment
: variable EQUALS operation?;

variable
: VARIABLE;

value
: SUBTRACTION? (NUMBER|PI|E|parenthesis);

parenthesis
: OPEN_PARENTHESES operation CLOSED_PARENTHESES?;

operation
: addition
| subtraction
| multiplication
| division
| power
| parenthesis;

addition
: value? ADDITION (value|operation)?;

subtraction
: value? SUBTRACTION (value|operation)?;

division
: value? DIVISION (value|operation)?;

multiplication
: value? MULTIPLICATION (value|operation)?
| value parenthesis
| parenthesis value;

power
: value? POWER (value|operation)?;
