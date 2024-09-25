grammar cc;

// grammatik parser
start   : hardware inputs outputs latches Reset EOF;

hardware : IDENTIFIER;
inputs : OSCILLATOR ;
outputs : OSCILLATOR ;
Reset   : FLOAT;
latches : OSCILLATOR ;

IDENTIFIER : [a-zA-Z] [a-zA-Z]* ;  // x17y
OSCILLATOR : '/' OSCILLATOR ''';

FLOAT      : [0-9]+ ('.' [0-9]+)? ;

WHITESPACE : [ \t\n]+ -> skip;

COMMENT : '//' ~[\n]* -> skip;
LONGCOMMENT : '/' (~[] | ''~[/]) '*/' -> skip;