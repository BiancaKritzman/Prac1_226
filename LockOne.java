//LockOne

class LockOne {
    private boolean[] flag = new boolean[2];

    public void lock(){
        int i = (int) Thread.currentThread().threadId();
        int j = 1 - i;
        flag[i] = true;
        
        while(flag[j]){
            //do nothing
        }
    }

    public void unlock(){
        int i = (int) Thread.currentThread().threadId();
    
        if(flag[i]){
            flag[i] = false;
        }
        
    }
}