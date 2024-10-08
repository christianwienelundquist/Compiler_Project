grammar cc;

// Main grammar structure
start   : hardware inputs outputs latches (def+)? updates siminputs EOF;

// Grammar sections
hardware : 'hardware:' IDENTIFIER+;
inputs : 'inputs:' IDENTIFIER+;
outputs : 'outputs:' IDENTIFIER+;
latches : 'latches:' IDENTIFIER+;
def : 'def:' func_def+; 
updates : 'updates:' update+;
siminputs : 'siminputs:' siminput+;


// Definitions for function and updates
func_def : IDENTIFIER '(' IDENTIFIER (',' IDENTIFIER)* ')' '=' exp ;
update : OSCILLATOR | IDENTIFIER | '(' | '=' | '*' | '+' | '-' | IDENTIFIER | OSCILLATOR | ')' | exp;
siminput : IDENTIFIER '=' BINARY;

exp : exp '+' exp         // Handles addition
    | exp '-' exp         // Handles subtraction
    | exp '*' exp         // Handles multiplication
    | exp '/' exp         // Handles division
    | '(' exp ')'         // Parenthesized expressions
    |IDENTIFIER '(' (exp (',' exp)*)? ')'
    | OSCILLATOR          // Oscillators as atomic expressions
    | IDENTIFIER
    | exp '/'  exp      // Identifiers as atomic expressions
    | (OSCILLATOR | IDENTIFIER) (('+' | '*' | '/' )* (OSCILLATOR | IDENTIFIER))*;



// Identifiers and oscillators
IDENTIFIER : [a-zA-Z]+[0-9]? ; 
OSCILLATOR : '/'? IDENTIFIER '\''? ('/' IDENTIFIER)?;  // Allows '/' and negations


// Binary input format for siminputs
BINARY : [0-1]+;
WHITESPACE : [ \n\t\r]+ -> skip; 
COMMENT : '//' (~[\n])* '\n' -> skip; 
LONGCOMMENT : '/*' .*? '*/' -> skip;
