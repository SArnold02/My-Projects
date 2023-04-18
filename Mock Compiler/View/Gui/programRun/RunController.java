package View.Gui.programRun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import controller.Controller;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.util.Pair;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;
import modell.statemants.Stmt;
import modell.values.StringValue;
import modell.values.Value;

public class RunController {
    private Controller controller;

    public void setController(Controller controller){
        this.controller = controller;
        populate();
    }

    @FXML
    private ListView<Stmt> execListView;

    @FXML
    private ListView<Integer> programStatesListView;

    @FXML
    private TextField numberOfProgramStates;

    @FXML
    private ListView<Value> outListView;

    @FXML
    private ListView<StringValue> fileListView;

    @FXML
    private TableView<Pair<Integer, Value>> heapTableView;

    @FXML
    private TableColumn<Pair<Integer, Value>, Integer> addressColumn;

    @FXML
    private TableColumn<Pair<Integer, Value>, Value> heapValueColumn;

    @FXML
    private TableView<Pair<String, Value>> symTableListView;

    @FXML
    private TableColumn<Pair<String, Value>, String> varNameColumn;

    @FXML
    private TableColumn<Pair<String, Value>, Value> varValueColumn;

    @FXML
    private TableView<Pair<Integer, Pair<Integer, java.util.List<Integer>>>> SemaphoreTableView;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, java.util.List<Integer>>>, Integer> SIndexColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, java.util.List<Integer>>>, String> SListColumn;

    @FXML
    private TableColumn<Pair<Integer, Pair<Integer, java.util.List<Integer>>>, Integer> SValueColumn;

    @FXML
    public void initialize(){
        addressColumn.setCellValueFactory(heapEntry -> new SimpleIntegerProperty(heapEntry.getValue().getKey()).asObject());
        heapValueColumn.setCellValueFactory(heapEntry -> new SimpleObjectProperty<Value>(heapEntry.getValue().getValue()));

        varNameColumn.setCellValueFactory(var -> new SimpleStringProperty(var.getValue().getKey()));
        varValueColumn.setCellValueFactory(var -> new SimpleObjectProperty<Value>(var.getValue().getValue()));

        SIndexColumn.setCellValueFactory(var -> new SimpleIntegerProperty(var.getValue().getKey()).asObject());
        SValueColumn.setCellValueFactory(var -> new SimpleIntegerProperty(var.getValue().getValue().getKey()).asObject());
        SListColumn.setCellValueFactory(var -> new SimpleStringProperty(var.getValue().getValue().getValue().toString()));
    }

    public void oneStepExec(){
        if ((this.controller == null) || (this.controller.getPrgStates().size() == 0))
            return;

        try {
            this.controller.oneStepExec();
            populate();
        } catch (InterpreterExceptions e) {
            Alert alert = new Alert(AlertType.ERROR, e.getMessage(), ButtonType.OK);
            alert.showAndWait();
        }
    }

    private PrgState getCurrenPrgState(){
        if (this.controller.getPrgStates().size() == 0)
            return null;
        int index = programStatesListView.getSelectionModel().getSelectedIndex();
        if (index < 0)
            return this.controller.getPrgStates().get(0);
        return this.controller.getPrgStates().get(index);
    }

    public void populate(){
        populatePrgStates();
        populateStack();
        populateOut();
        populateHeap();
        populateFileTable();
        populateSymTable();
        populateSemaphore();
    }

    private void populatePrgStates(){
        List<PrgState> prgStates = this.controller.getPrgStates();
        List<Integer> ids = prgStates.stream().map(prg -> prg.id).collect(Collectors.toList());
        programStatesListView.setItems(FXCollections.observableList(ids));
        programStatesListView.refresh();
        numberOfProgramStates.setText(Integer.toString(prgStates.size()));
    }

    private void populateStack(){
        PrgState state = getCurrenPrgState();
        List<Stmt> stackList;
        if (state != null)
            stackList = state.getExecStack().getContent().stream().collect(Collectors.toList());
        else
            stackList = new ArrayList<Stmt>();

        Collections.reverse(stackList);

        execListView.setItems(FXCollections.observableList(stackList));
        execListView.refresh();
    }

    private void populateOut(){
        List<Value> outList;
        if (this.controller.getPrgStates().size() > 0)
            outList = this.controller.getPrgStates().get(0).getOut().getContent();
        else
            outList = new ArrayList<Value>();

        outListView.setItems(FXCollections.observableList(outList));
        outListView.refresh();
    }

    private void populateFileTable(){
        List<StringValue> fileTableList;
        if (this.controller.getPrgStates().size() > 0)
            fileTableList = this.controller.getPrgStates().get(0).getFileTable().getContent().keySet().stream().collect(Collectors.toList());
        else
            fileTableList = new ArrayList<StringValue>();

        fileListView.setItems(FXCollections.observableList(fileTableList));
        fileListView.refresh();
    }

    private void populateSymTable(){
        List<Pair<String, Value>> variables;
        PrgState state = getCurrenPrgState();

        if (state != null)
            variables = state.getVarMap().getContent().entrySet().stream().map(v -> new Pair<String, Value>(v.getKey(), v.getValue())).collect(Collectors.toList());
        else
            variables = new ArrayList<Pair<String, Value>>();

        symTableListView.setItems(FXCollections.observableList(variables));
        symTableListView.refresh();
    }

    private void populateHeap(){
        List<Pair<Integer, Value>> heapEntries;
        if (this.controller.getPrgStates().size() > 0)
            heapEntries = this.controller.getPrgStates().get(0).getHeap().getContent().entrySet().stream().map(v -> new Pair<Integer, Value>(v.getKey(), v.getValue())).collect(Collectors.toList());
        else
            heapEntries = new ArrayList<Pair<Integer, Value>>();

        heapTableView.setItems(FXCollections.observableList(heapEntries));
        heapTableView.refresh();
    }

    private void populateSemaphore(){
        List<Pair<Integer, Pair<Integer, java.util.List<Integer>>>> semaphoreEntries;
        if (this.controller.getPrgStates().size() > 0){
            semaphoreEntries = this.controller.getPrgStates().get(0).getSemaphore().getContent().entrySet().stream().map(v -> new Pair<Integer, Pair<Integer, java.util.List<Integer>>>(v.getKey(), v.getValue())).collect(Collectors.toList());
        } else {
            semaphoreEntries = new ArrayList<>();
        }

        SemaphoreTableView.setItems(FXCollections.observableList(semaphoreEntries));
        SemaphoreTableView.refresh();
    }
}
