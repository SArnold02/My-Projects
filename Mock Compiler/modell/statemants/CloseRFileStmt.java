package modell.statemants;

import java.io.BufferedReader;
import java.io.IOException;

import modell.adts.dictionary.IDictionary;
import modell.adts.fileTable.IFileTable;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.StringType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.StringValue;
import modell.values.Value;

public class CloseRFileStmt implements Stmt{
    Exp e;

    public CloseRFileStmt(Exp e) {
        this.e = e;
    }

    @Override
    public String toString() {
        return "Close file: " + e.toString();
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> symTable = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        Value v = e.eval(symTable, heap);
        
        StringValue var = (StringValue)v;

        IFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(var))
            throw new StmtException("Given file: " + var.getValue() + "was not opened!");

        BufferedReader fileDesc = fileTable.get(var);
        try {
            fileDesc.close();
            fileTable.remove(var);
        } catch (IOException e1) {
            throw new StmtException("File:" + var.getValue() + " couldnt be closed!");
        }

        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type t = e.typeCheck(types);
        if (!t.equals(new StringType()))
            throw new StmtException("Given file name is not a string type");

        return types;
    }
}
