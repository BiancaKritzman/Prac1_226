//LockOne

class LockOne {
    private boolean[] flag = new boolean[2];

    public void lock(){
        for(int i = 0; i < 2; i++){
            if(flag[i]){
                while(flag[i]){
                //do nothing
                }
            }
        }
    }

    public void unlock(){
        int i = 0;
        int j = 1;
        if(flag[i]){
            flag[i] = false;
        }

        if(flag[j]){
            flag[j] = false;
        }
        
    }
}