//LockOne
interface Lock {
    void lock(int ID);
    void unlock();
}

public class LockOne implements Lock {
    private boolean[] flag = new boolean[2];

    private int lockID;

    public void lock(int i){
        lockID = i;
        int j = 1 - i;
        flag[lockID] = true;
        
        while(flag[j]){
            //do nothing
        }
    }

    public void unlock(){
        int i = lockID;
    
        if(flag[i]){
            flag[i] = false;
        }
        
    }
}