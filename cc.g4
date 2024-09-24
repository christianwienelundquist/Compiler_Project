grammar cc;

// grammatik  parser
start   :

exp: IDENT | INT | exp'+'exp | exp'*'exp | '('exp')' ;
IDENT: [a-zA-Z][a-zA-Z0-9]*;
INT : [0-9]+;