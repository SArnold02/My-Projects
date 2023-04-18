package modell.adts.stack;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import modell.exceptions.StackException;

public class Stack<T> implements IStack<T>{
    java.util.Stack<T> stack;
    
    public Stack() {
        this.stack = new java.util.Stack<T>();
    }

    @Override
    public T pop() throws StackException{
        if (this.stack.empty())
            throw new StackException("Cannot pop empty stack!");
        return this.stack.pop();
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public boolean isEmpty() {
        return this.stack.empty();
    }

    @Override
    public String toString() {
        List<T> stackList = this.stack.stream().collect(Collectors.toList());
        Collections.reverse(stackList);
        return stackList.stream().map(v -> v.toString() + "\n").reduce("", String::concat);
    }

    @Override
    public Iterator<T> iterator() {
        return this.stack.iterator();
    }

    @Override
    public java.util.Stack<T> getContent() {
        return this.stack;
    }

    @Override
    public void setContent(java.util.Stack<T> stack) {
        this.stack = stack;
    }

    public IStack<T> clone(){
        java.util.Stack<T> cloneStack = new java.util.Stack<T>();
        List<T> stackList = this.stack.stream().collect(Collectors.toList());
        stackList.forEach(p -> cloneStack.push(p));
        IStack<T> newStack = new Stack<T>();
        return newStack;
    }
}
