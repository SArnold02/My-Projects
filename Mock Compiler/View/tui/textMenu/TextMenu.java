package View.tui.textMenu;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import View.tui.command.Command;
import modell.exceptions.InterpreterExceptions;

public class TextMenu {    
    private Map<String, Command> commands;    
    
    public  TextMenu(){ 
        this.commands = new HashMap<>(); 
    }     
    
    public void addCommand(Command c){ 
        commands.put(c.getKey(), c);
    }    

    private void printMenu(){
        for(Command com : commands.values()){                     
            System.out.println(com.toString());        
        }
    }       
    
    public void run(){        
        try (Scanner scanner = new Scanner(System.in)) {
            while(true){
                printMenu();            
                System.out.printf("Input the option: ");            
                String key=scanner.nextLine();            
                Command com = commands.get(key);            
                if (com==null){                
                    System.out.println("Invalid Option");                
                    continue;  
                }            
                try {
                    com.execute();
                } catch (InterpreterExceptions e) {
                    System.out.println(e.getMessage());
                }            
            }
        } 
    }
} 