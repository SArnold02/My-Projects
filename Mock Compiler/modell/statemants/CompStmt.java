package modell.statemants;

import modell.adts.dictionary.IDictionary;
import modell.adts.stack.IStack;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;

public class CompStmt implements Stmt{
    Stmt s1;
    Stmt s2;
    
    public CompStmt(Stmt s1, Stmt s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public String toString() {
        return "(" + s1.toString() + ";" + s2.toString() + ")";
    }

    @Override
    public PrgState execute(PrgState state) {
        IStack<Stmt> stk = state.getExecStack();

        stk.push(s2);
        stk.push(s1);
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        return s2.typeCheck(s1.typeCheck(types));
    }
}
