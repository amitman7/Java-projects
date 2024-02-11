
public class Bit {
	

    private boolean value;
  //Task 4.1
    public Bit(boolean value){
    	if (value == false) {
    		this.value = false;
    	}
    	
    	else {
    		this.value = true;
    	}
    }
    
  //Task 4.2
    public int toInt(){ 
        int ans = 0;
        if (value == true) {
        	ans = 1;
        }
        else {
        	ans = 0;
        }
        return ans;

    }
    //Task 4.3
    public String toString(){
        String ans = "";
        if (toInt() == 1) {
        	ans = "1";
        }
        else {
        	ans = "0";
        }
        return ans;
    }
}

