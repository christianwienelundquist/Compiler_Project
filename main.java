import org.antlr.v4.*;
import org.antlr.v4.runtime.CharStreams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class main {
    public static void main(String[] args) throws IOException{

	// we expect exactly one argument: the name of the input file
	if (args.length!=1) {
	    System.err.println("\n");
	    System.err.println("Please give as input argument a filename\n");
	    System.exit(-1);
	}
	String filename=args[0];

	// open the input file
	CharStream input = CharStreams.fromFileName(filename);
	    //new ANTLRFileStream (filename); // depricated
	
	// create a lexer/scanner
	progLexer lex = new progLexer(input);
	
	// get the stream of tokens from the scanner
	CommonTokenStream tokens = new CommonTokenStream(lex);
	
	// create a parser
	progParser parser = new progParser(tokens);
	
	// and parse anything from the grammar for "start"
	ParseTree parseTree = parser.start();

	// Construct an interpreter and run it on the parse tree
	Interpreter interpreter = new Interpreter();
	Double result=interpreter.visit(parseTree);
	System.out.println("The result is: "+result);
    }
}

// We write an interpreter that implements interface
// "implVisitor<T>" that is automatically generated by ANTLR
// This is parameterized over a return type "<T>" which is in our case
// simply a Double.
/*
inputs : 'inputs:' IDENTIFIER+;
outputs : 'outputs:' IDENTIFIER+;
latches : 'latches:' IDENTIFIER+;
def : 'def:' func_def+; 
updates : 'updates:' update+;
siminputs : 'siminputs:' siminput+;
*/

class VisitorCC extends AbstractParseTreeVisitor<Double> implements progVisitor<Double>{
	public Double visitHardware(progParser.StartContext ctx){
		
	}

	public Double visitInputs(progParser.StartContext ctx){
		
	}

	public Double visitOutputs(progParser.StartContext ctx){
		
	}

	public Double visitLatches(progParser.StartContext ctx){
		
	}

	public Double visitUpdate(progParser.StartContext ctx){
		
	}

	public Double visitSimInputs(progParser.StartContext ctx){
		
	}
	public Double visitUpdates(progParser.StartContext ctx){
		
	}
}

class Interpreter extends AbstractParseTreeVisitor<Double>
                  implements progVisitor<Double> {
    // todo - Java will complain that "Interpreter" does not in fact
    // implement "implVisitor" at the moment.

	public Double visitStart(progParser.StartContext ctx){
	
		return visit(ctx.e);
	}
	public Double visitVariable(progParser.VariableContext ctx){
	    System.err.println("Variables are not yet supported.\n");
	    System.exit(-1);
	    return null; }
	public Double visitAddSub(progParser.AddSubContext ctx){
	    // e1=exp op=('+'|'-') e2=exp
	    System.out.println("Addition/Subtraction");
	    Double d1=visit(ctx.e1);
	    Double d2=visit(ctx.e2);
	    if (ctx.op.getText().equals("+")){
		System.out.println("Add "+d1+" + "+d2+" = "+(d1+d2));
		return d1+d2;
	    }
	    else{
		System.out.println("Sub "+d1+" - "+d2+" = "+(d1-d2));
		return d1-d2;
	    }
	}
	public Double visitConstant(progParser.ConstantContext ctx){
	    String s=ctx.f.getText();
	    System.out.println("Constant "+ s);
	    return Double.valueOf(s);
	}
	public Double visitParen(progParser.ParenContext ctx){ return visit(ctx.e); }
	public Double visitMultDiv(progParser.MultDivContext ctx){
	    System.out.println("Mult/Div");
	    Double d1=visit(ctx.e1);
	    Double d2=visit(ctx.e2);
	    if (ctx.op.getText().equals("*")){
		System.out.println("Mult "+ d1 +" * "+ d2 +" = "+(d1 * d2));
		return d1*d2;
	    }
	    else{
		System.out.println("Div "+ d1 +" / "+ d2 +" = "+(d1 / d2));				
		return d1/d2;	
	    }

    
	}


}

