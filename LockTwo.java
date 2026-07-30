public class LockTwo implements Lock{
    private volatile int victim = -1; //decides who waits
    public void lock(int ID){
        int i = ID;
        victim = i;
        System.out.println("Thread "+ ID + " is the victim");
        while (victim == i){
            if (Thread.currentThread().isInterrupted()) {
            System.out.println("Releasing Thread " + ID + " from hanging"); 
            return; //a way to get out of the loop and  continue the demo
            }
        } //wait
        System.out.println("Thread "+ ID + " enters the critical section");
    }
    public void unlock(int ID){
        if (victim != ID)
        {
            System.out.println("Thread "+ ID + " exits the critical section");
        }
        victim = -1; //resets victim releasing the waiting thread
    };
}
