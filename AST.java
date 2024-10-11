import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.antlr.v4.runtime.CharStreams;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AST{};

abstract class HTMLElementsStringss extends AST{
    public abstract String ToHTML();
}


class Start extends HTMLElementsStringss {
    List<HTMLElementsStringss> ps;
    Start(List<HTMLElementsStringss> ps) {
        this.ps = ps;
    }
    @Override
    public String ToHTML() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>\n");
        for (HTMLElementsStringss p : ps) {
            sb.append(p.ToHTML()).append("\n");
        }
        sb.append("</html>");
        return sb.toString();
    }
}



class Hardware extends HTMLElementsStringss{
    List<String> ps;
    Hardware(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}


class Input extends HTMLElementsStringss{
    List<String> ps;
    Input(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class Output extends HTMLElementsStringss{
    List<String> ps;
    Output(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class latches extends HTMLElementsStringss{
    List<String> ps;
    latches(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class def extends HTMLElementsStringss{
    List<String> ps;
    def(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class updates extends HTMLElementsStringss{
    List<String> ps;
    updates(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class siminputs extends HTMLElementsStringss{
    List<String> ps;
    siminputs(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class update extends HTMLElementsStringss{
    List<String> ps;
    update(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class siminput extends HTMLElementsStringss{
    List<String> ps;
    siminput(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}

class func_def extends HTMLElementsStringss{
    List<String> ps;
    func_def(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
}










abstract class Exp extends AST{
    public abstract String toLatex();
}

class OR extends Exp{
    Exp e1, e2;
    OR(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}  
    public String toLatex(){
        return "("+ e1.toLatex() + " \\vee "+ e2.toLatex() +")" ;
    }  
}

class And extends Exp{
    Exp e1, e2;
    And(Exp e1, Exp e2){this.e1=e1; this.e2=e2;}
    public String toLatex(){
        return "("+ e1.toLatex() + " \\wedge "+ e2.toLatex() +")" ;
    } 
}

class Not extends Exp{
    Exp e1;
    Not(Exp e1){this.e1=e1;}
    public String toLatex(){
        return "(\\neg"+ e1.toLatex() +")" ;
    } 
}

class Paren extends Exp{
    Exp e1;
    Paren(Exp e1){this.e1=e1;}
    public String toLatex(){
        return "("+ e1.toLatex() +")" ;
    } 
}

class Assign extends Exp{
    Exp e1;
    Assign(Exp e1){this.e1=e1;}
    public String toLatex(){
        return "="+ e1.toLatex() ;
    } 
}

class FunctionCall extends Exp{
    Exp e1;
    List<String> ps;
    FunctionCall(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        String.join("\n",ps);
    }
    FunctionCall(Exp e1){this.e1=e1;}
    public String toLatex(){
        return "(" + e1.toLatex() +"(" + ps +", "+")" ;
    } 
}

class Identifier extends Exp{
    Exp e1;
    Identifier(Exp e1){this.e1=e1;}
    public String toLatex(){
        return "("+ e1.toLatex() +")" ;
    } 
}

