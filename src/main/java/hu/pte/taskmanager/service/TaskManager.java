package hu.pte.taskmanager.service;
import hu.pte.taskmanager.model.Task; import hu.pte.taskmanager.exception.TaskException; import java.util.*;
public final class TaskManager {
 private final List<Task> tasks=new ArrayList<>(); private int nextId=1;
 public Task add(String type,String title){Task task=TaskFactory.create(type,nextId,title,false);tasks.add(task);nextId++;return task;}
 public void restore(Task task){if(tasks.stream().anyMatch(t->t.getId()==task.getId())) throw new TaskException("Duplicate ID");tasks.add(task);nextId=Math.max(nextId,task.getId()+1);}
 public Task find(int id){return tasks.stream().filter(t->t.getId()==id).findFirst().orElseThrow(()->new TaskException("Task not found: "+id));}
 public void complete(int id){Task task=find(id);if(task.isCompleted())throw new TaskException("Task already completed");task.complete();}
 public void remove(int id){Task task=find(id);tasks.remove(task);}
 public List<Task> listByPriority(){List<Task> result=new ArrayList<>(tasks);result.sort(Comparator.comparingInt(Task::getPriority).reversed().thenComparing(Task::getTitle));return result;}
 public List<Task> listByTitle(){List<Task> result=new ArrayList<>(tasks);result.sort(Comparator.comparing(Task::getTitle,String.CASE_INSENSITIVE_ORDER));return result;}
 public List<Task> all(){return List.copyOf(tasks);}

    public List<Task> listByStatus(boolean completed) {
        List<Task> result = new ArrayList<>();

        for (Task task : tasks) {
            if (task.isCompleted() == completed) {
                result.add(task);
            }
        }

        result.sort(Comparator.comparing(Task::getTitle,
                String.CASE_INSENSITIVE_ORDER));

        return result;
    }

}
