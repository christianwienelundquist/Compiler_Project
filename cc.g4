grammar cc;

// Main grammar structure
start   : hardwar=hardware input=inputs output=outputs latche=latches definisio=def* updat=updates siminpu=siminputs EO=EOF;


//if things is capital letter, it is a token

// Grammar sections
hardware :'hardware:'  hw+=IDENTIFIER+;
inputs : 'inputs:' inp+=IDENTIFIER+;
outputs : 'outputs:' otp+=IDENTIFIER+;
latches : 'latches:' lhes+=IDENTIFIER+;
def : 'def:' df=func_def; 
updates : 'updates:' upd+=update*; // TODO find out in the assingment if we need to have multiple updates
siminputs : 'siminputs:' simip+=siminput*;
// Definitions for function and updates
update   : upda=IDENTIFIER '=' e=exp;
siminput : simi=IDENTIFIER '=' bir=BINARY;
func_def : fucd=IDENTIFIER '(' parms+=IDENTIFIER (',' parms+=IDENTIFIER)* ')' '=' fucd4=exp ;



exp : '/' e1=exp           #NOT           // Logical OR expression
    | e1=exp '*'? e2=exp    #AND                           // Logical AND expression
    | e1=exp '+' e2=exp    #OR                          // Logical NOT expression
    | '(' e1=exp ')'                                   #Paren      // Parenthesized expression
    // | '=' e1=exp                                       #Assign     // Assignment expression
    | e1=IDENTIFIER '(' (e2+=exp (',' e2+=exp)*)? ')'          #FunctionCall // Function call
    | e1=IDENTIFIER                                    #Identifier  // Atomic identifier   
    ;



// Identifiers and oscillators
IDENTIFIER : [a-zA-Z]+[0-9]?'\''? ; 


// Binary input format for siminputs
BINARY : [0-1]+;
WHITESPACE : [ \n\t\r]+ -> skip; 
COMMENT : '//' (~[\n])* '\n' -> skip; 
LONGCOMMENT : '/' .? '*/' -> skip;