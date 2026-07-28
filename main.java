public class main {

   

    public static void main(String[] args) {

        LockOne l1 = new LockOne();
        LockOne l2 = new LockOne();
        l1.lock();

        //LockTwo testing
        LockTwo lock = new LockTwo();

        Thread t0 = new Thread(() -> {
            System.out.println("Thread 0 attempts to acquire lock");
            lock.lock(0);
            lock.unlock();
        });

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 attempts to acquire lock");
            lock.lock(1);
            lock.unlock();
        });

        t0.start();
        t1.start();
    }
}
