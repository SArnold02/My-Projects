package View;

import modell.statemants.Stmt;
import modell.statemants.SwitchStmt;
import modell.expressions.ArithExp;
import modell.expressions.Exp;
import modell.expressions.HeapReadExp;
import modell.expressions.RelationalExp;
import modell.expressions.ValueExp;
import modell.expressions.VarExp;
import modell.expressions.ArithExp.Operaion;
import modell.expressions.RelationalExp.ROperaion;
import modell.statemants.AcquireStmt;
import modell.statemants.AssignStmt;
import modell.statemants.CloseRFileStmt;
import modell.statemants.CompStmt;
import modell.statemants.CrtSemaphoreStmt;
import modell.statemants.ForkStmt;
import modell.statemants.HeapAllocStmt;
import modell.statemants.HeapWriteStmt;
import modell.statemants.IfStmt;
import modell.statemants.OpenRFileStmt;
import modell.statemants.PrintStmt;
import modell.statemants.ReadRFileStmt;
import modell.statemants.ReleaseStmt;
import modell.statemants.VarDeclStmt;
import modell.statemants.WhileStmt;
import modell.values.BoolValue;
import modell.values.IntValue;
import modell.values.StringValue;
import modell.dataTypes.BoolType;
import modell.dataTypes.IntType;
import modell.dataTypes.RefType;
import modell.dataTypes.StringType;

public class Statemants {
    private static Stmt getFirstStatment(){
        return new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()),new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
    }

    private static Stmt getSecondStatemant() {
        return new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()),new CompStmt(new AssignStmt("a", new ArithExp(Operaion.ADDITION, new ValueExp(new IntValue(2)),new ArithExp(Operaion.MULTIPLICATION, new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),  new CompStmt(new AssignStmt("b",new ArithExp(Operaion.ADDITION, new VarExp("a"), new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));
    }

    private static Stmt getThirdStatemant() {
        return new CompStmt(new VarDeclStmt("v",new IntType()),new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
    }

    private static Stmt getFourthStatemant() {
        Stmt varDecl = new VarDeclStmt("varf", new StringType());
        Stmt assign = new AssignStmt("varf", new ValueExp(new StringValue("test.in")));
        Stmt openFile = new OpenRFileStmt(new VarExp("varf"));
        Stmt varDecl2 = new VarDeclStmt("varc", new IntType());
        Stmt readFile = new ReadRFileStmt(new VarExp("varf"), "varc");
        Stmt print = new PrintStmt(new VarExp("varc"));
        Stmt readFile2 = new ReadRFileStmt(new VarExp("varf"), "varc");
        Stmt print2 = new PrintStmt(new VarExp("varc"));
        Stmt closeFile = new CloseRFileStmt(new VarExp("varf"));
        return new CompStmt(varDecl, new CompStmt(assign, new CompStmt(openFile, new CompStmt(varDecl2, new CompStmt(readFile, new CompStmt(print, new CompStmt(readFile2, new CompStmt(print2, closeFile))))))));
    }

    private static Stmt getFifthStatemant() {
        Stmt varDecla = new VarDeclStmt("a", new IntType());
        Stmt varDeclb = new VarDeclStmt("b", new IntType());
        Stmt varDeclc = new VarDeclStmt("c", new IntType());
        Stmt bValue = new AssignStmt("b", new ValueExp(new IntValue(6)));
        Stmt relStmt = new IfStmt(new RelationalExp(ROperaion.BIGGER, new VarExp("a"), new VarExp("b")), new AssignStmt("c", new ValueExp(new IntValue(12))), new AssignStmt("c", new ValueExp(new IntValue(25))));
        return new CompStmt(varDecla, new CompStmt(varDeclb, new CompStmt(varDeclc, new CompStmt(bValue, relStmt))));
    }

    private static Stmt getSixthStatement(){
        Stmt varDeclv = new VarDeclStmt("v", new RefType(new IntType()));
        Stmt allocv = new HeapAllocStmt("v", new ValueExp(new IntValue(20)));
        Stmt varDecla = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        Stmt alloca = new HeapAllocStmt("a", new VarExp("v"));
        Stmt printv = new PrintStmt(new VarExp("v"));
        Stmt printa = new PrintStmt(new VarExp("a"));
        Stmt varDeclb = new VarDeclStmt("b", new RefType(new RefType(new RefType(new IntType()))));
        Stmt allocb = new HeapAllocStmt("b", new VarExp("a"));
        return new CompStmt(varDeclv, new CompStmt(allocv, new CompStmt(varDecla, new CompStmt(alloca, new CompStmt(printv, new CompStmt(printa, new CompStmt(varDeclb, allocb)))))));
    }

    private static Stmt getSeventhStatement(){
        Stmt varDeclv = new VarDeclStmt("v", new IntType());
        Stmt assignv = new AssignStmt("v", new ValueExp(new IntValue(4)));
        Stmt whileSt = new WhileStmt(new RelationalExp(ROperaion.BIGGER, new VarExp("v"), new ValueExp(new IntValue(0))), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(Operaion.SUBTRACTION, new VarExp("v"), new ValueExp(new IntValue(1))))));
        Stmt printSt = new PrintStmt(new VarExp("v"));
        return new CompStmt(varDeclv, new CompStmt(assignv, new CompStmt(whileSt, printSt)));
    }

    private static Stmt getEighthStatement(){
        Stmt varDeclv = new VarDeclStmt("v", new RefType(new IntType()));
        Stmt allocv = new HeapAllocStmt("v", new ValueExp(new IntValue(20)));
        Stmt printRh1 = new PrintStmt(new HeapReadExp(new VarExp("v")));
        Stmt write = new HeapWriteStmt("v", new ValueExp(new IntValue(30)));
        Stmt printRh2 = new PrintStmt(new ArithExp(Operaion.ADDITION, new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5))));
        return new CompStmt(varDeclv, new CompStmt(allocv, new CompStmt(printRh1, new CompStmt(write, printRh2))));
    }

    private static Stmt getNinthStatement(){
        Stmt varDeclv = new VarDeclStmt("v", new RefType(new IntType()));
        Stmt allocv = new HeapAllocStmt("v", new ValueExp(new IntValue(20)));
        Stmt varDecla = new VarDeclStmt("a", new RefType(new RefType(new IntType())));
        Stmt alloca = new HeapAllocStmt("a", new VarExp("v"));
        Stmt allocv2 = new HeapAllocStmt("v", new ValueExp(new IntValue(30)));
        Stmt print = new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a"))));
        Stmt alloca2 = new HeapAllocStmt("a", new VarExp("v"));
        return new CompStmt(varDeclv, new CompStmt(allocv, new CompStmt(varDecla, new CompStmt(alloca, new CompStmt(allocv2, new CompStmt(print, alloca2))))));
    }

    private static Stmt getTenthStatement(){
        Stmt varDeclv = new VarDeclStmt("v", new IntType());
        Stmt varDela = new VarDeclStmt("a", new RefType(new IntType()));
        Stmt assignv = new AssignStmt("v", new ValueExp(new IntValue(10)));
        Stmt alloca = new HeapAllocStmt("a", new ValueExp(new IntValue(22)));
        
        Stmt whA = new HeapWriteStmt("a", new ValueExp(new IntValue(30)));
        Stmt assignv2 = new AssignStmt("v", new ValueExp(new IntValue(32)));
        Stmt printv = new PrintStmt(new VarExp("v"));
        Stmt printa = new PrintStmt(new HeapReadExp(new VarExp("a")));
        Stmt toFork = new CompStmt(whA, new CompStmt(assignv2, new CompStmt(printv, printa)));
        
        Stmt fork = new ForkStmt(toFork);

        Stmt assignv3 = new AssignStmt("v", new ValueExp(new IntValue(80)));
        Stmt printv2 = new PrintStmt(new VarExp("v"));
        Stmt printa2 = new PrintStmt(new HeapReadExp(new VarExp("a")));

        return new CompStmt(varDeclv, new CompStmt(varDela, new CompStmt(assignv, new CompStmt(alloca, new CompStmt(fork, new CompStmt(assignv3, new CompStmt(printv2, printa2)))))));
    }

    private static Stmt getEleventhStatement(){
        Stmt vardecla = new VarDeclStmt("a", new IntType());
        Stmt vardeclb = new VarDeclStmt("b", new IntType());
        Stmt vardeclc = new VarDeclStmt("c", new IntType());

        Stmt assigna = new AssignStmt("a", new ValueExp(new IntValue(1)));
        Stmt assignb = new AssignStmt("b", new ValueExp(new IntValue(2)));
        Stmt assignc = new AssignStmt("c", new ValueExp(new IntValue(5)));

        Exp arit1 = new ArithExp(Operaion.MULTIPLICATION, new VarExp("a"), new ValueExp(new IntValue(10)));
        Exp arit2 = new ArithExp(Operaion.MULTIPLICATION, new VarExp("b"), new VarExp("c"));
        Stmt stmt1 = new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b")));
        Stmt stmt2 = new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200))));
        Stmt stmt3 = new PrintStmt(new ValueExp(new IntValue(300)));
        Stmt switchstmt = new SwitchStmt(arit1, arit2, new ValueExp(new IntValue(10)), stmt1, stmt2, stmt3);

        Stmt print1 = new PrintStmt(new ValueExp(new IntValue(300)));
        return new CompStmt(vardecla, new CompStmt(vardeclb, new CompStmt(vardeclc, new CompStmt(assigna, new CompStmt(assignb, new CompStmt(assignc, new CompStmt(switchstmt, print1)))))));
    }

    private static Stmt getTwelthStatement(){
        Stmt vardeclv = new VarDeclStmt("v1", new RefType(new IntType()));
        Stmt vardeclcnt = new VarDeclStmt("cnt", new IntType());

        Stmt heapAlloc = new HeapAllocStmt("v1", new ValueExp(new IntValue(1)));
        Stmt crtsem = new CrtSemaphoreStmt("cnt", new HeapReadExp(new VarExp("v1")));

        Stmt acq1 = new AcquireStmt("cnt");
        Stmt heapw1 = new HeapWriteStmt("v1", new ArithExp(Operaion.MULTIPLICATION, new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10))));
        Stmt print1 = new PrintStmt(new HeapReadExp(new VarExp("v1")));
        Stmt release1 = new ReleaseStmt("cnt");
        Stmt stmt1 = new CompStmt(acq1, new CompStmt(heapw1, new CompStmt(print1, release1)));
        Stmt fork1 = new ForkStmt(stmt1);

        Stmt acq2 = new AcquireStmt("cnt");
        Stmt heapw2 = new HeapWriteStmt("v1", new ArithExp(Operaion.MULTIPLICATION, new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10))));
        Stmt heapw3 = new HeapWriteStmt("v1", new ArithExp(Operaion.MULTIPLICATION, new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(2))));
        Stmt print2 = new PrintStmt(new HeapReadExp(new VarExp("v1")));
        Stmt release2 = new ReleaseStmt("cnt");
        Stmt stmt2 = new CompStmt(acq2, new CompStmt(heapw2, new CompStmt(heapw3, new CompStmt(print2, release2))));
        Stmt fork2 = new ForkStmt(stmt2);

        Stmt acq3 = new AcquireStmt("cnt");
        Stmt print3 = new PrintStmt(new ArithExp(Operaion.SUBTRACTION, new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1))));
        Stmt rel3 = new ReleaseStmt("cnt");

        return new CompStmt(vardeclv, new CompStmt(vardeclcnt, new CompStmt(heapAlloc, new CompStmt(crtsem, new CompStmt(fork1, new CompStmt(fork2, new CompStmt(acq3, new CompStmt(print3, rel3))))))));
    }

    public static Stmt[] getStatemants(){
        Stmt stmt1 = getFirstStatment();
        Stmt stmt2 = getSecondStatemant();
        Stmt stmt3 = getThirdStatemant();
        Stmt stmt4 = getFourthStatemant();
        Stmt stmt5 = getFifthStatemant();
        Stmt stmt6 = getSixthStatement();
        Stmt stmt7 = getSeventhStatement();
        Stmt stmt8 = getEighthStatement();
        Stmt stmt9 = getNinthStatement();
        Stmt stmt10 = getTenthStatement();
        Stmt stmt11 = getEleventhStatement();
        Stmt stmt12 = getTwelthStatement();

        return new Stmt[]{stmt1, stmt2, stmt3, stmt4, stmt5, stmt6, stmt7, stmt8, stmt9, stmt10, stmt11, stmt12};
    }
}
