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
        return String.join("\n",ps);
    }
}


class Input extends HTMLElementsStringss{
    List<String> ps;
    Input(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        return String.join("\n",ps);
    }
}

class Output extends HTMLElementsStringss{
    List<String> ps;
    Output(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        return String.join("\n",ps);
    }
}

class latches extends HTMLElementsStringss{
    List<String> ps;
    latches(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        return String.join("\n",ps);
    }
}

class Def extends HTMLElementsStringss{
    func_def df;
    Def(func_def df){this.df=df;}
    public String ToHTML(){
        return df.ToHTML();
    }
}

class updates extends HTMLElementsStringss{
    List<String> ps;
    updates(List<String> ps){this.ps=ps;}
    public String ToHTML(){
        return String.join("\n",ps);
    }
}

class siminputs extends HTMLElementsStringss{
    List<siminput> simi;
    siminputs(List<siminput> simi){this.simi=simi;}
    public String ToHTML(){
        StringBuilder sb = new StringBuilder();
        for (siminput s : simi) {
            sb.append(s.ToHTML()).append("\n");
        }
        return sb.toString();
    }
}

class update extends HTMLElementsStringss{
    String upda;
    Exp e;
    update(String upda, Exp e){this.upda=upda;this.e=e;} 
    public String ToHTML(){
        return String.format("%s: (%s)\n", upda, e.toLatex());
    }
}

class siminput extends HTMLElementsStringss{
    String simi;
    String bir;
    siminput(String simi, String bir){this.simi=simi; this.bir=bir;}
    public String ToHTML(){
        return String.join("\n",simi,bir);
    }
}

class func_def extends HTMLElementsStringss{
    String fucd;
    List<String> parms;
    Exp fucd4;

    func_def(String fucd, List<String> parms, Exp fucd4){this.fucd=fucd;this.parms=parms;this.fucd4=fucd4;}
    public String ToHTML(){
        return String.format("(%s(%s) = %s)\n", fucd, String.join(", ", parms), fucd4.toLatex());
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

// class Paren extends Exp{
//     Exp e1;
//     Paren(Exp e1){this.e1=e1;}
//     public String toLatex(){
//         return "("+ e1.toLatex() +")" ;
//     } 
// }

// class Assign extends Exp{
//     Exp e1;
//     Assign(Exp e1){this.e1=e1;}
//     public String toLatex(){
//         return "="+ e1.toLatex() ;
//     } 
// }

class FunctionCall extends Exp{
    String e1;
    List<String> e2;

    FunctionCall(String e1,List<String> e2 ){this.e1=e1;this.e2=e2;}
    public String toLatex(){
        return "(" + e1 +"(" + e2 +", "+")" ;
    } 
}

class Identifier extends Exp{
    String e1;
    Identifier(String e1){this.e1=e1;}
    public String toLatex(){
        return "("+ e1 +")" ;
    } 
}

