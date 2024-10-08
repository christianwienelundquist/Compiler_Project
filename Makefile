antlrjar = antlr-4.13.2-complete.jar

###### FOR LINUX AND MAC -- comment the following line if you use Windows:
classpath = '$(antlrjar):.'

###### FOR WINDOWS -- uncomment the following line if you use Windows:
#classpath = '$(antlrjar);.'

antlr4 = java -cp $(classpath) org.antlr.v4.Tool
grun = java -cp $(classpath) org.antlr.v4.gui.TestRig
SRCFILES = main.java AST.java Environment.java
GENERATED = ccLexer.java ccParser.java progListener.java progBaseListener.java progParser.java progLexer.java progBaseVisitor.java progVisitor.java 

all: grun

ccLexer.java ccParser.java: cc.g4
	$(antlr4) cc.g4

%.class: %.java
	javac -cp $(classpath) $<

grun: ccLexer.class ccParser.class TestFiles/04-von-Neumann.hw
	$(grun) cc start -gui -tokens TestFiles/04-von-Neumann.hw

main.class: $(GENERATED) $(SRCFILES)
	javac -cp $(classpath) $(GENERATED) $(SRCFILES)

run: main.class
	java -cp $(classpath) main prog.txt

clean:
	rm -f $(GENERATED) *.class *.interp *.tokens
