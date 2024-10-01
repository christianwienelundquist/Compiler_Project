grammar cc;

start   : hardware inputs outputs latches updates siminputs EOF;

// Grammar sections
hardware : 'hardware:' IDENTIFIER;
inputs : 'inputs:' IDENTIFIER;
outputs : 'outputs:' IDENTIFIER;
latches : 'latches:' IDENTIFIER;
updates : 'updates:' update+;
siminputs : 'siminputs:' siminput+;

// Definitions for updates and siminputs
update : exp '=' exp;
siminput : IDENTIFIER '=' BINARY;

// Expressions and identifiers
exp : OSCILLATOR | IDENTIFIER '(' IDENTIFIER (',' IDENTIFIER)* ')';
IDENTIFIER : [a-zA-Z]+ ;  
OSCILLATOR : '/' IDENTIFIER '\''? ('/' IDENTIFIER)?;

// Formating
BINARY : [0-1]+;
WHITESPACE : [ \t\n]+ -> skip;
COMMENT : '//' ~[\n]* -> skip;
LONGCOMMENT : '/*' .*? '*/' -> skip;
