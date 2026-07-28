public class Worker implements Runnable{

}

public class Peterson implements Runnable {
    // thread-local index, 0 or 1
    private boolean[] flag = new boolean[2];
    private int victim;
    private Worker worker;

    public void lock() {
        int i = 
        int j = 1 - i;
        flag[i] = true;
        // I’m interested
        victim = i;
        // you go first
        while (flag[j] && victim == i) {}; // wait
    }

    public void unlock() {
        int i = 
        flag[i] = false;
        // I’m not interested
    }

    public void run() {
        // critical section
        lock();
        // perform some operations
        unlock();
    }
}
