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
func_def : IDENTIFIER '(' IDENTIFIER (',' IDENTIFIER)* ')' '=' logic_expr ;
update : OSCILLATOR | IDENTIFIER | '(' | '=' | '*' | '+' | '-' | IDENTIFIER | OSCILLATOR | ')' | logic_expr;
siminput : IDENTIFIER '=' BINARY;


// Expressions and function calls
logic_expr : term (('+' | '*' | '/' )* term)*;
term : OSCILLATOR | func_call | IDENTIFIER; // Terms can be oscillators, function calls, or identifiers
func_call : IDENTIFIER '(' logic_expr (',' logic_expr)* ')'; 


// Identifiers and oscillators
IDENTIFIER : [a-zA-Z]+[0-9]? ; 
OSCILLATOR : '/'? IDENTIFIER '\''? ('/' IDENTIFIER)?;  // Allows '/' and negations


// Binary input format for siminputs
BINARY : [0-1]+;
WHITESPACE : [ \n\t\r]+ -> skip; 
COMMENT : '//' (~[\n])* '\n' -> skip; 
LONGCOMMENT : '/*' .*? '*/' -> skip;
