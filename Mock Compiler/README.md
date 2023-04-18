# Mock language compiler

>Stack based mock language compiler in Java, simulating memory management, with a gui for better debugging capabilities

This is an MVC and stack based mock compiler, with internal variable managemant, heap managemant (with garbage collector) and thread managemant

## Code representation

---

>Since there is no interpreter, the code is entirely hardcoded in the [Statemants.java](/Mock%20Compiler/View/Statemants.java) file
For example, the following code segment:
```
int a, b = 6, c;
if (a > b) {
    c = 12;
}
else {
    c = 25;
}
```
Is stored as:
```
Stmt varDecla = new VarDeclStmt("a", new IntType());
Stmt varDeclb = new VarDeclStmt("b", new IntType());
Stmt varDeclc = new VarDeclStmt("c", new IntType());
Stmt bValue = new AssignStmt("b", new ValueExp(new IntValue(6)));
Stmt relStmt = new IfStmt(new RelationalExp(ROperaion.BIGGER, new VarExp("a"), new VarExp("b")), new AssignStmt("c", new ValueExp(new IntValue(12))), new AssignStmt("c", new ValueExp(new IntValue(25))));
return new CompStmt(varDecla, new CompStmt(varDeclb, new CompStmt(varDeclc, new CompStmt(bValue, relStmt))));
```

## Running of the code

---

>The controller of the compiler, goes through the stack, in which the statemants are present, and the top one in every thread in one cicle, until there is nothing left

Every statemant is an action, which changes something in the current process

Every expression is an action the computes or retrieves some data

For example: 
- VarDeclStmt, is a statemant that create a variable under a certain name
- IfStmt, executes the one of two statemants, given the value of an expression
- ForkStmt, creates a new thread, which has a different execution stack, but shares variables, files, etc.

The full list of implemented statemants can be seen in the [modell/statemants](/Mock%20Compiler/modell/statemants/)/[Down below](#the-full-list-of-supported-features) folder

The full list of implemented statemants can be seen in the [modell/expressions](/Mock%20Compiler/modell/expressions/)/[Down below](#the-full-list-of-supported-expressions) folder

After the execution of a statemant, the controller manages the different threads in the program

Meaning for example if a thread has nothing more to execute, it is closed, or if a new is created, it is added to the list of running threads

## Garbage collector

---

>The compiler simulates memory management, with an internal map for variables and an internal mep for heap memory, which is automatically managed by a garbage collector

This is run after every execution step, and it loops through the data in the simulated variable and heap map, checking if any heap memory is being isolated 
if there is any, it is getting freed


## The full list of supported features

---

- Variable managemant
- Heap memory managemant
- File I/O operations
- Console output operation
- Multithreading with semaphore synchronization
- If logic statemant
- While loop statemant


## The full list of supported expressions

---

- Arithmetic expressions(+, -, *, /)
- Relational expressions(>, =>, <, <=, ==)
- Logical expressions(and, or) 