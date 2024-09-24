grammar cc;

// grammatik parser
start   : hardware inputs outputs latches Oscillator siminputs Reset EOF;

hardware : IDENTIFIER ;  
inputs : IDENTIFIER ;    
outputs : IDENTIFIER ;   
latches : IDENTIFIER ;  
siminputs : IDENTIFIER ; 
Reset : IDENTIFIER ;     
Oscillator : IDENTIFIER;
    
IDENTIFIER : [a-zA-Z] [a-zA-Z_0-9]* ;  // x17y

FLOAT      : [0-9]+ ('.' [0-9]+)? ;

WHITESPACE : [ \t\n]+ -> skip;

COMMENT : '//' ~[\n]* -> skip;
LONGCOMMENT : '/*' (~[*] | '*'~[/])* '*/' -> skip;

