package hu.pte.taskmanager.service;
import hu.pte.taskmanager.model.*; import hu.pte.taskmanager.exception.TaskException;
/** Factory pattern: one place decides which concrete task to create. */
public final class TaskFactory { private TaskFactory(){} public static Task create(String type,int id,String title,boolean done){ if(type==null) throw new TaskException("Missing task type"); return switch(type.toUpperCase(java.util.Locale.ROOT)){case "REGULAR" -> new RegularTask(id,title,done);case "URGENT" -> new UrgentTask(id,title,done);default -> throw new TaskException("Unknown task type: "+type);};} }
