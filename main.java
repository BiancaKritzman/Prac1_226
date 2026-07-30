

public class main {   

    public static void main(String[] args) throws InterruptedException {

        //LockOne testing

        LockOne l1 = new LockOne();
        //private static int value = 0;

        //sequential
         Thread thread1 = new Thread(() -> {
            System.out.println("(LockOne) Thread 1 attempts to acquire lock");
            l1.lock(0);
            System.out.println("(LockOne) Thread 1 successfully inside critcal section ");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // empty
            }            
            l1.unlock(0);
            System.out.println("(LockOne) Thread 1 released lock");
            
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("(LockOne) Thread 2 attempts to acquire lock");
            l1.lock(1);
            System.out.println("(LockOne) Thread 2 succesfully inside critical section ");
       
                    
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // empty
            }    
            l1.unlock(1);
            System.out.println("(LockOne) Thread 2 released lock");


        });

        System.out.println("Starting threads sequentially...");
        //sequential
        thread1.start();

        try {
            thread1.join();
        }
        catch (InterruptedException e){
            e.printStackTrace();
        }

        thread2.start();

        try{
            thread2.join();
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        System.out.println("Both threads finished execution.");

        Thread t1Concurrent = new Thread(() -> {
            System.out.println("(LockOne) Thread 1 attempts to acquire lock");
            l1.lock(0);
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // empty
            }            
            l1.unlock(0);
            
        });

        Thread t2Concurrent = new Thread(() -> {
            System.out.println("(LockOne) Thread 2 attempts to acquire lock");
            l1.lock(1);       
                    
            try {
                Thread.sleep(1000 );
            } catch (InterruptedException e) {
                // empty
            }    
            l1.unlock(1);

        });
        
        System.out.println("Starting threads concurrently...");
        //concurrent - causes deadlock
        //thread 1 unlock thread 2 flag by mistake
        //thread 1's flag was never set back to false
        //thread 2 changes the thread ID meaning thread 2 can never go back to false (deadlock)

        t1Concurrent.start();
        t2Concurrent.start();

        Thread.sleep(2000);

        if(t1Concurrent.isAlive()){
            System.out.println("Thread 1 waits forever");
            t1Concurrent.interrupt(); 
            t1Concurrent.join();
        }

         Thread.sleep(2000);

        if(t2Concurrent.isAlive()){
            System.out.println("Thread 1 waits forever");
            t2Concurrent.interrupt(); 
            t2Concurrent.join();
        }
    
        //add this code to add a Interrupt
        System.out.println("\nBoth Threads were not able to execute Thread2 changed the thread ID and now Thread1 will constantly be true\n");
 
        // Thread 1 acquires lock: Thread 1 sets lockID = 0 and flag[0] = true. It sees flag[1] is false, so it enters the critical section and goes to sleep.
        // Thread 2 arrives: While Thread 1 is sleeping, Thread 2 calls lock(1). It overwrites the class field lockID = 1 and sets flag[1] = true. It checks flag[0] (which is true), so Thread 2 starts spinning in its while loop.
        // Thread 1 releases lock: Thread 1 wakes up and calls unlock(). Inside unlock(), it reads int i = lockID;. Because Thread 2 overwrote lockID with 1, Thread 1 executes flag[1] = false!
        // The deadlock: Thread 1 finishes its run, thinking it cleared its own flag. But flag[0] is still true. Thread 2 is stuck spinning on while(flag[0]) forever because nobody will ever set flag[0] back to false

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

        System.out.println("\nBoth Threads were not able to execute because they couldn't be freed at the victim.\n");
    }
}
