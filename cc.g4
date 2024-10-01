grammar cc;

// Main grammar structure
start   : hardware inputs outputs latches def? updates siminputs EOF;

// Grammar sections
hardware : 'hardware:' IDENTIFIER*;
inputs : 'inputs:' IDENTIFIER*;
outputs : 'outputs:' IDENTIFIER*;
latches : 'latches:' IDENTIFIER*;
def : 'def:' func_def+; 
updates : 'updates:' update*;
siminputs : 'siminputs:' siminput+;


// Definitions for function and updates
func_def : IDENTIFIER '(' IDENTIFIER (',' IDENTIFIER)* ')' '=' logic_expr;
update : OSCILLATOR|IDENTIFIER | '(' | '='| '*' | '+' | '-' | IDENTIFIER | ')'|logic_expr;
siminput : IDENTIFIER '=' BINARY;


// Expressions and function calls
exp : func_call | OSCILLATOR;
func_call : IDENTIFIER '(' exp (',' exp)* ')';
logic_expr : OSCILLATOR ('*' OSCILLATOR)*; // Parses logic like /a * /b


// Identifiers and oscillators
IDENTIFIER : [a-zA-Z]+ ; 
OSCILLATOR : '/'? IDENTIFIER '\''? ('/' IDENTIFIER)?;  // Allows '/' and negations


// Binary input format for siminputs
BINARY : [0-1]+;
WHITESPACE : [ \n\t]+ -> skip; 
COMMENT : '//' (~[\n])* '\n' -> skip; 
LONGCOMMENT : '/*' .*? '*/' -> skip;
