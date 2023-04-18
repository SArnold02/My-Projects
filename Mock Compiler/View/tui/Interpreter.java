package View.tui;
import java.io.BufferedReader;

import View.Statemants;
import View.tui.command.ExitCommand;
import View.tui.command.RunCommand;
import View.tui.textMenu.TextMenu;
import controller.Controller;
import javafx.util.Pair;
import modell.statemants.Stmt;
import modell.adts.dictionary.Dictionary;
import modell.adts.dictionary.IDictionary;
import modell.adts.fileTable.FileTable;
import modell.adts.fileTable.IFileTable;
import modell.adts.heapTable.HeapTable;
import modell.adts.heapTable.IHeapTable;
import modell.adts.list.IList;
import modell.adts.list.List;
import modell.adts.semaphore.ISemaphoreTable;
import modell.adts.semaphore.SemaphoreTable;
import modell.adts.stack.IStack;
import modell.adts.stack.Stack;
import modell.dataTypes.Type;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;
import modell.values.StringValue;
import modell.values.Value;
import repository.IRepo;
import repository.Repo;

public class Interpreter {
    private static void addFirstStatement(TextMenu menu){
        Stmt ex = Statemants.getStatemants()[0];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();

        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile1.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("1", ex.toString(), controller));
    }

    private static void addFourthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[1];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile2.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("2", ex.toString(), controller));
    }

    private static void addThirdStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[2];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }

        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile3.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("3", ex.toString(), controller));
    }

    private static void addSecondStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[3];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile4.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("4", ex.toString(), controller));
    }
    
    private static void addFifthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[4];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile5.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("5", ex.toString(), controller));
    }

    private static void addSixthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[5];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile6.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("6", ex.toString(), controller));
    }

    private static void addSeventhStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[6];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile7.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("7", ex.toString(), controller));
    }

    private static void addEighthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[7];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile8.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("8", ex.toString(), controller));
    }

    private static void addNinthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[8];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile9.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("9", ex.toString(), controller));
    }

    private static void addTenthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[9];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile10.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("10", ex.toString(), controller));
    }

    private static void addEleventhStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[10];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile11.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("11", ex.toString(), controller));
    }

    private static void addTwelthStatement(TextMenu menu) {
        Stmt ex = Statemants.getStatemants()[11];

        try {
            ex.typeCheck(new Dictionary<String, Type>());
        } catch (InterpreterExceptions e) {
            System.out.println(e.getMessage());
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(ex);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        IRepo repo = new Repo(newState, "logFile12.txt");
        Controller controller = new Controller(repo);
        menu.addCommand(new RunCommand("12", ex.toString(), controller));
    }

    private static void setupAllMenu(TextMenu menu){
        menu.addCommand(new ExitCommand("0", "exit"));
        addFirstStatement(menu);
        addSecondStatement(menu);
        addThirdStatement(menu);
        addFourthStatement(menu);
        addFifthStatement(menu);
        addSixthStatement(menu);
        addSeventhStatement(menu);
        addEighthStatement(menu);
        addNinthStatement(menu);
        addTenthStatement(menu);
        addEleventhStatement(menu);
        addTwelthStatement(menu);
    }

    public static void main(String[] args) {
        TextMenu menu = new TextMenu();

        setupAllMenu(menu);

        menu.run();
    }
}
