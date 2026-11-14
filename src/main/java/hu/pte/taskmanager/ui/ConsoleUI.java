package hu.pte.taskmanager.ui;
import hu.pte.taskmanager.service.TaskManager;import hu.pte.taskmanager.persistence.TaskStorage;import hu.pte.taskmanager.model.Task;import hu.pte.taskmanager.exception.TaskException;import java.io.*;import java.util.*;
public final class ConsoleUI {
 private final Scanner scanner;private final TaskStorage storage;private TaskManager manager;
 public ConsoleUI(Scanner scanner,TaskStorage storage,TaskManager manager){this.scanner=scanner;this.storage=storage;this.manager=manager;}
 public void run(){System.out.println("=== PTE Task Manager ===");while(true){System.out.println("\n1 Add task | 2 List priority | 3 List title | 4 Complete | 5 Delete | 6 Save | 0 Exit");System.out.print("Choose: ");if(!scanner.hasNextLine())return;String choice=scanner.nextLine().trim();try{switch(choice){case "1" -> add();case "2" -> show(manager.listByPriority());case "3" -> show(manager.listByTitle());case "4" -> {manager.complete(readId());System.out.println("Completed.");}case "5" -> {manager.remove(readId());System.out.println("Deleted.");}case "6" -> {storage.save(manager);System.out.println("Saved.");}case "0" -> {storage.save(manager);System.out.println("Saved. Goodbye!");return;}default -> System.out.println("Unknown menu option.");}}catch(TaskException|IOException e){System.out.println("Error: "+e.getMessage());}}}
 private void add(){System.out.print("Type (REGULAR/URGENT): ");String type=scanner.nextLine().trim();System.out.print("Title: ");String title=scanner.nextLine();Task t=manager.add(type,title);System.out.println("Created "+t.display());}
 private int readId(){System.out.print("Task ID: ");try{return Integer.parseInt(scanner.nextLine().trim());}catch(NumberFormatException e){throw new TaskException("Enter a valid integer ID");}}
 private void show(List<Task> tasks){if(tasks.isEmpty())System.out.println("No tasks.");else tasks.forEach(t->System.out.println(t.display()));}
}
