package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.expressions.RelationalExp;
import modell.expressions.RelationalExp.ROperaion;
import modell.programState.PrgState;

public class SwitchStmt implements Stmt{
    Exp exp, exp1, exp2;
    Stmt stmt1, stmt2, stmt3;
    
    public SwitchStmt(Exp exp, Exp exp1, Exp exp2, Stmt stmt1, Stmt stmt2, Stmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    @Override
    public String toString() {
        return "switch(" + exp + ")(" + exp1 + ":" + stmt1 + ")(" + exp2 + ":" + stmt2 + ")(default:" + stmt3 + ")";
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        Exp equal1 = new RelationalExp(ROperaion.EQUAL, exp, exp1);
        Exp equal2 = new RelationalExp(ROperaion.EQUAL, exp, exp2);

        Stmt ifstmt = new IfStmt(equal1, stmt1, new IfStmt(equal2, stmt2, stmt3));
        state.getExecStack().push(ifstmt);

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type t1 = exp.typeCheck(types);
        Type t2 = exp1.typeCheck(types);
        Type t3 = exp2.typeCheck(types);
        if (!t1.equals(t2) || !t1.equals(t3) || !t2.equals(t3))
            throw new StmtException("Given expressions in swith are not of equal type");

        stmt1.typeCheck(types.clone());
        stmt2.typeCheck(types.clone());
        stmt3.typeCheck(types.clone());
        
        return types;
    }
    
    
}
