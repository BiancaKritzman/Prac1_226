interface Lock {
    void lock(int ID);
    void unlock();
}

public class LockTwo implements Lock{
    private int victim;
    public void lock(int ID){
        int i = ID;
        victim = i;
        System.out.println("Thread "+ ID + "is the victim");
        while (victim == i){} //wait
        System.out.println("Thread "+ ID + "is in the critical section");
    }
    public void unlock(){};
}
