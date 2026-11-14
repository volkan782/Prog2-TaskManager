package hu.pte.taskmanager.model;
public final class UrgentTask extends Task { public UrgentTask(int id,String title,boolean done){super(id,title,done);} @Override public String getType(){return "URGENT";} @Override public int getPriority(){return 10;} }
