public class main {

   

    public static void main(String[] args) {

        LockOne l1 = new LockOne();
        LockOne l2 = new LockOne();
        l1.lock();

        l2.lock(); //can't happen because l1 is locked

        if(l1.flag[0] && l2.flag[1]){ {
            System.out.println("Both threads are locked");
        } else {
            System.out.println("One of the threads is locked");
        }

        System.out.println("Thread 1 is locked and thread 2 is waiting for it to unlock");

    }
}
