package controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import modell.adts.dictionary.IDictionary;
import modell.adts.heapTable.IHeapTable;
import modell.exceptions.InterpreterExceptions;
import modell.programState.PrgState;
import modell.values.RefValue;
import modell.values.Value;
import repository.IRepo;

public class Controller implements IController{
    IRepo repo;
    ExecutorService executor;

    public Controller(IRepo repo) {
        this.repo = repo;
    }

    @Override
    public void oneStepForAll(List<PrgState> prgList) throws InterpreterExceptions{
        //Log the programstate before execution
        for (PrgState prg : prgList){
            this.repo.logPrgStateExec(prg);
        }
        
        //Making the callables for the execution
        List<Callable<PrgState>> callableList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStepExec();}))
                .collect(Collectors.toList());

        List<PrgState> newPrgList = new ArrayList<PrgState>();
        List<Future<PrgState>> futureList;

        //Execute all program states
        try {
            futureList = executor.invokeAll(callableList);
        } catch (InterruptedException e) {
            throw new InterpreterExceptions(e.getMessage());
        }

        for (Future<PrgState> future : futureList){
            try {
                PrgState temp = future.get();
                if (temp != null)
                    newPrgList.add(temp);

            } catch (InterruptedException | ExecutionException e) {
                throw new InterpreterExceptions(e.getMessage());
            }
        }

        //Add all the new programs, that may have been created in fork to the old list
        prgList.addAll(newPrgList);

        //Update the list of the repo
        this.repo.setPrgList(prgList);
    }

    @Override
    public List<PrgState> removeCompletedPrgs(List<PrgState> prgList) {
        //Get the list of programs that have something in their execution stack
        return prgList.stream().filter(p -> p.isNotCompleted()).collect(Collectors.toList());
    }

    @Override
    public void completeExec() throws InterpreterExceptions {
        //Create the executor for concurrency
        this.executor = Executors.newFixedThreadPool(5);
        
        //Get the programs which have something to execute
        List<PrgState> prgList = removeCompletedPrgs(this.repo.getPrgList());
        
        //Execution
        while (prgList.size() > 0){
            //One step execution for all concurrent programs
            oneStepForAll(prgList);

            //Using the garbage collector
            IHeapTable<Value> heap = this.repo.getPrgList().get(0).getHeap();
            heap.setContent(garbageCollector());

            //Log the programs after execution only if they finished
            for (PrgState prg: prgList){
                if (!prg.isNotCompleted())
                    this.repo.logPrgStateExec(prg);
            }

            //Update the active programs
            prgList = removeCompletedPrgs(repo.getPrgList());
        }

        //Close the executor
        this.executor.shutdownNow();

        //Update the repo
        repo.setPrgList(prgList);
    }

    @Override
    public void oneStepExec() throws InterpreterExceptions {
        //Create the executor for concurrency
        this.executor = Executors.newFixedThreadPool(5);
        
        //Get the programs which have something to execute
        List<PrgState> prgList = removeCompletedPrgs(this.repo.getPrgList());

        if (prgList.size() > 0){
            //One step execution for all concurrent programs
            oneStepForAll(prgList);

            //Using the garbage collector
            IHeapTable<Value> heap = this.repo.getPrgList().get(0).getHeap();
            heap.setContent(garbageCollector());

            //Log the programs after execution only if they finished
            for (PrgState prg: prgList){
                if (!prg.isNotCompleted())
                    this.repo.logPrgStateExec(prg);
            }
        }

        //Close the executor
        this.executor.shutdownNow();

        //Update the repo
        repo.setPrgList(prgList);
    }

    private Map<Integer, Value> garbageCollector(){
        IHeapTable<Value> heap = this.repo.getPrgList().get(0).getHeap();
        
        //Get addresses from symTable
        List<Integer> addrSymList = getAddrFromSymTable();

        //Initializing the stack to parse all adresses with depth search
        Stack<Integer> stackAddr = new Stack<Integer>();
        for (int addr : addrSymList){
            stackAddr.push(addr);
        }

        //Make a set for all adresses that are in use
        Set<Integer> addresses = new HashSet<Integer>();
        
        //Do a deep search in the heap based on symTable
        while(!stackAddr.empty()){
            int currentAddress = stackAddr.pop();
            addresses.add(currentAddress);
            Value heapValue;
            try {
                heapValue = heap.get(currentAddress);
                if (heapValue instanceof RefValue){
                    RefValue refHeapValue = (RefValue) heapValue;
                    if (!stackAddr.contains(refHeapValue.getAddr()))
                        stackAddr.push(refHeapValue.getAddr());
                }
            } catch (InterpreterExceptions e1) {}
        }

        //Returning new heap with every filter
        return heap.getContent().entrySet().stream()
                        .filter(e -> addresses.contains(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    private List<Integer> getAddrFromSymTable(){
        List<IDictionary<String, Value>> symTables = this.repo.getPrgList().stream().map(prg -> prg.getVarMap()).collect(Collectors.toList());
        List<Integer> addresses = new ArrayList<Integer>();

        for (IDictionary<String, Value> map : symTables){
            List<Integer> temp = map.getContent().values().stream()
                                    .filter(e -> e instanceof RefValue)
                                    .map(e -> {RefValue e1 = (RefValue)e; return e1.getAddr();})
                                    .collect(Collectors.toList());

            addresses.addAll(temp);
        }
        return addresses;
    }

    public List<PrgState> getPrgStates(){
        return this.repo.getPrgList();
    }
}
