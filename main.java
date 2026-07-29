public class main {

   

    public static void main(String[] args) throws InterruptedException{

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

        //LockTwo testing
        //Concurrent 
        System.out.println("LockTwo demonstration with concurrent Threads:\n");

        LockTwo concurrentLock = new LockTwo();

        Thread t0 = new Thread(() -> {
            System.out.println("Thread 0 attempts to acquire lock");
            concurrentLock.lock(0);
            concurrentLock.unlock(0);
        });

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1 attempts to acquire lock");
            concurrentLock.lock(1);
            concurrentLock.unlock(1);
        });

        t0.start(); //.start() calls the Thread's run() method in parallel
        t1.start();

        t0.join(); //to wait for t0 and t1 to finish the demonstration before continuing
        t1.join();

        System.out.println("\nBoth Threads completed successfully!!\n");

        //Sequential
        System.out.println("LockTwo demonstration with sequential Threads:\n");

        LockTwo sequentialLock = new LockTwo();

        Thread t2 = new Thread(() -> {
            System.out.println("Thread 0 attempts to acquire lock");
            sequentialLock.lock(0);
            sequentialLock.unlock(0);
        });

        t2.start();
        Thread.sleep(2000); //2s to run

        if(t2.isAlive()){ //check if its stuck
            System.out.println("Thread 0 waits forever");
            t2.interrupt(); //sets an interrupt flag so we can stop the while loop and move on
            t2.join(); //wait to finish
        }

        Thread t3 = new Thread(() -> {
            System.out.println("Thread 1 attempts to acquire lock");
            sequentialLock.lock(1);
            sequentialLock.unlock(1);
        });

        t3.start();
        Thread.sleep(2000);

        if(t3.isAlive()){
            System.out.println("Thread 1 waits forever");
            t3.interrupt(); 
            t3.join();
        }

        System.out.println("\nBoth Threads were not able to execute because they couldn't be freed as the victim.\n");

    }
}
