grammar cc;

// Main grammar structure
start   : hardware inputs outputs latches (def+)? updates siminputs EOF;

// Grammar sections
hardware : hw+= 'hardware:' IDENTIFIER+;
inputs : inp+='inputs:' IDENTIFIER+;
outputs : otp+='outputs:' IDENTIFIER+;
latches : lhes+='latches:' IDENTIFIER+;
def : df+='def:' func_def+; 
updates : upd+='updates:' update+;
siminputs : simip+='siminputs:' siminput+;


// Definitions for function and updates
update   : exp ('=' exp)?;
siminput : IDENTIFIER '=' BINARY;
func_def : IDENTIFIER '(' IDENTIFIER (',' IDENTIFIER)* ')' '=' exp ;

exp : exp '+' exp                                   #OR         // Logical OR expression
    | exp '*' exp                                   #AND        // Logical AND expression
    | '/' exp                                       #NOT        // Logical NOT expression
    | '(' exp ')'                                   #Paren      // Parenthesized expression
    | '=' exp                                       #Assign     // Assignment expression
    | IDENTIFIER '(' (exp (',' exp)*)? ')'          #FunctionCall // Function call
    | OSCILLATOR                                    #Oscillator  // Oscillators as atomic expressions
    | IDENTIFIER                                    #Identifier  // Atomic identifier
    | (OSCILLATOR | IDENTIFIER) (('+' | '*' | '/' )* (OSCILLATOR | IDENTIFIER))* #Funcdef     
    ;



// Identifiers and oscillators
IDENTIFIER : [a-zA-Z]+[0-9]? ; 
OSCILLATOR : '/'? IDENTIFIER '\''? ('/' IDENTIFIER)?;  // Allows '/' and negations


// Binary input format for siminputs
BINARY : [0-1]+;
WHITESPACE : [ \n\t\r]+ -> skip; 
COMMENT : '//' (~[\n])* '\n' -> skip; 
LONGCOMMENT : '/' .? '*/' -> skip;