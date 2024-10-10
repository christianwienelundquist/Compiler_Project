import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.CharStreams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AST{};

abstract class Program extends AST{
    abstract public void eval(Environment env);
}

class Sequence extends Program{
    List<Program> ps;
    Sequence(List<Program> ps){this.ps=ps;}
    public void eval(Environment env){
	for(Program p:ps) p.eval(env);
    };
}

class Assignment extends Program{
    String varname;
    Exp e;
    Assignment(String varname, Exp e){ this.varname=varname; this.e=e;}
    public void eval(Environment env){
	Double d=e.eval(env);
	env.setVariable(varname,d);
    };
}

class While extends Program{
    Condition c;
    Program p;
    While(Condition c, Program p){this.c=c; this.p=p;}
    public void eval(Environment env){
	while(c.eval(env)){
	    p.eval(env);
	}
	/*
	  Boolean b=c.eval();
	  if (b){ p.eval(); this.eval();}

	 */
    };
}

class If extends Program{
    Condition c;
    Program p1,p2;
    If(Condition c, Program p1, Program p2){
	this.c=c; this.p1=p1; this.p2=p2;
    }
    public void eval(Environment env){
	if(c.eval(env)) p1.eval(env);
	else p2.eval(env);
    };
}

class Output extends Program{
    Exp e;
    Output(Exp e){ this.e=e;}
    public void eval(Environment env){
	System.out.println(e.eval(env));
    };
}

abstract class Condition extends AST{
    abstract public Boolean eval(Environment env);
};

class Greater extends Condition{
    Exp e1,e2;
    Greater(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}
    public Boolean eval(Environment env){
	return e1.eval(env)>e2.eval(env);
    }
}

abstract class Exp extends AST{
    abstract public Double eval(Environment env);
}

class Addition extends Exp{
    Exp e1, e2;
    Addition(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}    
    public Double eval(Environment env){
	return e1.eval(env)+e2.eval(env);
    };
}

class Subtraction extends Exp{
    Exp e1, e2;
    Subtraction(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}    
    public Double eval(Environment env){
	return e1.eval(env)-e2.eval(env);
    };
}
class Multiplication extends Exp{
    Exp e1, e2;
    Multiplication(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)*e2.eval(env);
    };
}
class Division extends Exp{
    Exp e1, e2;
    Division(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}
    public Double eval(Environment env){
	return e1.eval(env)/e2.eval(env);
    };
}

class Constant extends Exp{
    public Double d;
    Constant(Double d){ this.d=d;}
    public Double eval(Environment env){
	return d;
    };
};

class Variable extends Exp{
    public String varname;
    Variable(String varname){this.varname=varname;}
    public Double eval(Environment env){
	return env.getVariable(varname);
    };
};
