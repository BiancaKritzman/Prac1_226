interface Lock {
    void lock(int ID);
    void unlock();
}

public class LockTwo implements Lock{
    private int victim;
    public void lock(int ID){ //enters critical section
        int i = ID;
        victim = i;
        while (victim == i){}
    }
    public void unlock(){};
}
