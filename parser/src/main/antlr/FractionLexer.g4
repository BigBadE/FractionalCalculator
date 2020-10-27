lexer grammar FractionLexer;

//Tells the lexer what a digit, letter, etc... is
fragment DIGIT: [0-9];
fragment LETTER: [a-zA-Z];

//Ignore whitespace and newlines
WHITESPACE: (' ' | '\r' | '\t' | '\n' | '\f')+ -> skip;

//Basic operators
ADDITION: '+';
SUBTRACTION: '-';
MULTIPLICATION
: '*'
| '⋅'
| '×';
DIVISION
: '/'
| '÷';
POWER: '^';
EQUALS: '=';

//Other symbols
PI
: 'pi'
| 'π';

E: 'e';

OPEN_PARENTHESES: '(';
CLOSED_PARENTHESES: ')';

//A number can have a decimal
NUMBER: DIGIT+ ([.,] DIGIT+)? ;

VARIABLE: LETTER;
