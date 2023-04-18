package View.Gui.programList;

import java.io.BufferedReader;

import View.Statemants;
import View.Gui.programRun.RunController;
import controller.Controller;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;
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
import modell.statemants.Stmt;
import modell.values.StringValue;
import modell.values.Value;
import repository.IRepo;
import repository.Repo;

public class ListController {
    private RunController runControl;

    public void setRunController(RunController runControl){
        this.runControl = runControl;
    }

    @FXML
    private ListView<Stmt> statements;

    @FXML
    private Button chooseButton;

    @FXML
    public void initialize(){
        this.statements.setItems(FXCollections.observableArrayList(Statemants.getStatemants()));
    }

    public void btnClicked(){
        int index = this.statements.getSelectionModel().getSelectedIndex();
        if (index < 0)
            return;

        Stmt currentStmt = Statemants.getStatemants()[index];

        try {
            currentStmt.typeCheck(new Dictionary<String,Type>());
        } catch (InterpreterExceptions e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
            return;
        }
        
        IStack<Stmt> stack = new Stack<Stmt>();
        stack.push(currentStmt);
        IDictionary<String,Value> map = new Dictionary<String, Value>();
        IList<Value> list = new List<Value>();
        IFileTable<StringValue, BufferedReader> fileTable = new FileTable<>();
        IHeapTable<Value> heap = new HeapTable<Value>();
        ISemaphoreTable<Pair<Integer, java.util.List<Integer>>> semaphore = new SemaphoreTable<>();
        
        PrgState newState = new PrgState(stack, map, list, fileTable, heap, semaphore);
        String filePath = "logFile" + Integer.toString(index + 1) + ".txt";
        IRepo repo = new Repo(newState, filePath);
        Controller controller = new Controller(repo);

        this.runControl.setController(controller);
    }
}
