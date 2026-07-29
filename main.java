

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
            l1.unlock();
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
            l1.unlock();
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
            l1.unlock();
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
            l1.unlock();
            System.out.println("(LockOne) Thread 2 released lock");


        });
        
        System.out.println("Starting threads concurrently...");
        //concurrent - causes deadlock
        t1Concurrent.start();
        t2Concurrent.start();

        try {
            t1Concurrent.join();
            t2Concurrent.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Both threads finished execution.");

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
