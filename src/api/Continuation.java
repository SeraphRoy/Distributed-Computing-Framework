package api;

public class Continuation implements java.io.Serializable{
    final private long closureId;
    final private int slot;

    public Continuation(long closureId, int s){
        this.closureId = closureId;
        slot = s;
    }

    public long getClosureId(){return closureId;}

    public int getSlot(){return slot;}
}
