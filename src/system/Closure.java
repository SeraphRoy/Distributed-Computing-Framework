package system;

import api.*;
import java.util.List;
import java.util.ArrayList;

public class Closure implements java.io.Serializable{
    private int counter;
    final private Task task;
    final private List<Argument> argumentList;
    private long closureId;
    private final int argc;

    public Closure(int argc, Task task){
        counter = argc - task.getArgumentList().size();
        this.argc = argc;
        this.task = task;
        closureId = task.id;
        argumentList = task.getArgumentList();
    }

    public int getCounter(){return counter;}

    public Task getTask(){return task;}

    public List<Argument> getList(){return argumentList;}

    public long getClosureId(){return closureId;}

    public synchronized void addArgument(Argument a){
        argumentList.add(a);
        counter --;
    }

    public synchronized void decrementCounter(){
        counter --;
    }
}
