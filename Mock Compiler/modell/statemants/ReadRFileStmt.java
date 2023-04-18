package modell.statemants;

import java.io.BufferedReader;
import java.io.IOException;

import modell.adts.dictionary.IDictionary;
import modell.adts.fileTable.IFileTable;
import modell.adts.heapTable.IHeapTable;
import modell.dataTypes.IntType;
import modell.dataTypes.StringType;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.exceptions.StmtException;
import modell.expressions.Exp;
import modell.programState.PrgState;
import modell.values.IntValue;
import modell.values.StringValue;
import modell.values.Value;

public class ReadRFileStmt implements Stmt{
    String varName;
    Exp e;

    public ReadRFileStmt(Exp e, String varName) {
        this.varName = varName;
        this.e = e;
    }

    @Override
    public String toString() {
        return "From: " + e.toString() + " to: " + varName;
    }

    @Override
    public PrgState execute(PrgState state) throws InterpreterExceptions {
        IDictionary<String, Value> symTable = state.getVarMap();
        IHeapTable<Value> heap = state.getHeap();

        if (!symTable.isDefined(varName))
            throw new StmtException("Variable: " + varName + "not defined!");
        
        Value v = e.eval(symTable, heap);

        StringValue var = (StringValue)v;

        IFileTable<StringValue, BufferedReader> fileTable = state.getFileTable();
        if (!fileTable.isDefined(var))
            throw new StmtException(var.getValue() + " is not open!");
        BufferedReader fileDesc = fileTable.get(var);

        int readValue;
        try {
            String read = fileDesc.readLine();
            if (read == null){
                readValue = 0;
            } else{
                readValue = Integer.parseInt(read);
            }
        } catch (IOException e1) {
            throw new StmtException("Couldnt read from file");
        }

        symTable.put(varName, new IntValue(readValue));
        
        return null;
    }

    @Override
    public IDictionary<String, Type> typeCheck(IDictionary<String, Type> types) throws InterpreterExceptions {
        Type typeVar = types.get(this.varName);
        Type typeExp = e.typeCheck(types);

        if (!typeVar.equals(new IntType()) || !typeExp.equals(new StringType()))
            throw new StmtException("The type of the variable or the file name is incorrect!");

        return types;
    }
}
