package hu.pte.taskmanager.model;
public final class RegularTask extends Task { public RegularTask(int id,String title,boolean done){super(id,title,done);} @Override public String getType(){return "REGULAR";} @Override public int getPriority(){return 1;} }
