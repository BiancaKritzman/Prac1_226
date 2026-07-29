interface Lock {
    void lock(int id);
    void unlock(int id);
}

public class Peterson implements Lock {
    // thread-local index, 0 or 1
    private boolean[] flag = new boolean[2];
    private int victim;

    public void lock(int id) {
        int i = id;
        int j = 1 - i;
        flag[i] = true;
        // I’m interested
        victim = i;
        System.out.println("Thread " + id + " is interested and is the victim");
        // you go first
        while (flag[j] && victim == i) {}; // wait
        // critical section
        System.out.println("Thread " + id + " is in the critical section");
    }

    public void unlock(int id) {
        flag[id] = false;
        // I’m not interested
    }

}
