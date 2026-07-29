

public class main {   

    public static void main(String[] args) {

        //LockOne testing

        LockOne l1 = new LockOne();
        LockOne l2 = new LockOne();
        //int value = 0;

        //sequential
         Thread thread1 = new Thread(() -> {
            System.out.println("(LockOne) Thread 1 attempts to acquire lock");
            l1.lock(1);
            int value = 10;
            l1.unlock();
            System.out.println("(LockOne) Thread 1 successfully finished critcal section" + value);

        });

        Thread thread2 = new Thread(() -> {
            System.out.println("(LockOne) Thread 2 attempts to acquire lock");
            l2.lock(2);
            int value = 20;
            l2.unlock();
            System.out.println("(LockOne) Thread 2 succesfully finished critical section" + value);

        });

        thread1.start();

        if(thread1.flag == true){
            System.out.println("(LockOne) thread1 running");
        }

        //concurrent


       
        // l1.lock();

        // l2.lock(); //can't happen because l1 is locked

        // if(l1.flag[0] && l2.flag[1]){ {
        //     System.out.println("Both threads are locked");
        // } else {
        //     System.out.println("One of the threads is locked");
        // }

        // System.out.println("Thread 1 is locked and thread 2 is waiting for it to unlock");

        //LockTwo testing
        LockTwo lock = new LockTwo();

        Thread t0 = new Thread(() -> {
            System.out.println("Thread 0 attempts to acquire lock");
            lock.lock(0);
            lock.unlock();
        });

        //t1 ends up waiting forever
        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 attempts to acquire lock");
            lock.lock(1);
            lock.unlock();
        });

        t0.start(); //.start() calls the Thread's run() method in parallel
        t1.start();
    }
}
