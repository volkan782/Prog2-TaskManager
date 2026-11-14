package hu.pte.taskmanager;
import hu.pte.taskmanager.persistence.TaskStorage;import hu.pte.taskmanager.ui.ConsoleUI;import java.nio.file.*;import java.io.*;import java.util.*;
public final class Main {public static void main(String[] args){TaskStorage storage=new TaskStorage(Path.of("data","tasks.txt"));try{new ConsoleUI(new Scanner(System.in),storage,storage.load()).run();}catch(IOException e){System.err.println("Unable to load saved tasks: "+e.getMessage());System.err.println("Fix or back up data/tasks.txt before restarting to avoid overwriting it.");}}}
