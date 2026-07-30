public class LockOne implements Lock {
    private boolean[] flag = new boolean[2];

    public void lock(int i){
        int j = 1 - i;
        flag[i] = true;
        
        while(flag[j]){
            //do nothing
            if (Thread.currentThread().isInterrupted()) {
            System.out.println("Releasing Thread " + i + " from hanging"); 
            return; //a way to get out of the loop and  continue the demo
            }
        }
    }

    public void unlock(int i){
    
        if(flag[i]){
            flag[i] = false;
        }
        
    }
}