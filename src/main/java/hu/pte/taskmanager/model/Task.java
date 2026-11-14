package hu.pte.taskmanager.model;
import hu.pte.taskmanager.exception.TaskException;
public abstract class Task {
 private final int id; private final String title; private boolean completed;
 protected Task(int id,String title,boolean completed) { if(id<=0 || title==null || title.isBlank() || title.contains("\n") || title.contains("\r")) throw new TaskException("Invalid task ID or title"); this.id=id;this.title=title.trim();this.completed=completed; }
 public int getId(){return id;} public String getTitle(){return title;} public boolean isCompleted(){return completed;} public void complete(){completed=true;}
 public abstract String getType(); public abstract int getPriority();
 public String display(){return "#"+id+" ["+(completed?"DONE":"OPEN")+"] "+title+" ("+getType()+", priority "+getPriority()+")";}
}
