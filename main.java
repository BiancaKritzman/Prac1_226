

public class main {   

    public static void main(String[] args) {

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
                Thread.sleep(1000 + (int) (Math.random() * 2000));
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
            System.out.println("(LockOne) Thread 1 successfully inside critcal section ");
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // empty
            }            
            l1.unlock(0);
            System.out.println("(LockOne) Thread 1 released lock");
            
        });

        Thread t2Concurrent = new Thread(() -> {
            System.out.println("(LockOne) Thread 2 attempts to acquire lock");
            l1.lock(1);
            System.out.println("(LockOne) Thread 2 succesfully inside critical section ");
       
                    
            try {
                Thread.sleep(1000 + (int) (Math.random() * 2000));
            } catch (InterruptedException e) {
                // empty
            }    
            l1.unlock(1);
            System.out.println("(LockOne) Thread 2 released lock");


        });
        
        System.out.println("Starting threads concurrently...");
        //concurrent - causes deadlock
        //thread 1 unlock thread 2 flag by mistake
        //thread 1's flag was never set back to false

        t1Concurrent.start();
        t2Concurrent.start();

        try {
            t1Concurrent.join();
            t2Concurrent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads finished execution.");

        // Thread 1 acquires lock: Thread 1 sets lockID = 0 and flag[0] = true. It sees flag[1] is false, so it enters the critical section and goes to sleep.
        // Thread 2 arrives: While Thread 1 is sleeping, Thread 2 calls lock(1). It overwrites the class field lockID = 1 and sets flag[1] = true. It checks flag[0] (which is true), so Thread 2 starts spinning in its while loop.
        // Thread 1 releases lock: Thread 1 wakes up and calls unlock(). Inside unlock(), it reads int i = lockID;. Because Thread 2 overwrote lockID with 1, Thread 1 executes flag[1] = false!
        // The deadlock: Thread 1 finishes its run, thinking it cleared its own flag. But flag[0] is still true. Thread 2 is stuck spinning on while(flag[0]) forever because nobody will ever set flag[0] back to false

    }
}
