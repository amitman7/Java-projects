

public class Warmup {
	
	//using a regular loop to iterate the array, while every index of the array is "pushing" into the stack
	//till the "forwardCapacity". then starts to "pop" till the "backCapacity". 
    public static int backtrackingSearch(int[] arr, int x, int forward, int back, Stack myStack) {
    	int index = 0;
    	int forwardCapacity = forward;
    	int backCapacity = back;
    	for (int i=0;i<arr.length ;i=i+1) {
    		if (arr[i] == x) {
    			return index;
    		}
    		index = index +1;
    		forwardCapacity = forwardCapacity -1;
    		myStack.push(arr[i]);
    		if (forwardCapacity == 0) { // if the forwardCapacity is 0 we should start to pop
    			while (backCapacity > 0) {
    				myStack.pop();
    				backCapacity = backCapacity -1;
    			}
    			// when we both the forwardCapacity and the backCapacity are 0, returning to the main loop 
    			forwardCapacity = forward;
    			backCapacity = back;		
    		}	
    	}
    	return -1; 
    }

     //implements Binary Search with added feature of backtracking.
    //for each iteration of the algorithm (for every recursion call) an "midHigh" array (midHigh[0] = low and midHigh[1] = high of the current iteration) is being pushed to "myStack"  the function is checking if any backtracking is necessary with the "isConsistent" function.
    //if any backtracking is needed, the midHigh array will get its previous value (which is being popped from the stack) and the number of "pops" is decided by the value returned from the "isConsistent" function.
    public static int consistentBinSearch(int[] arr, int x, Stack myStack) {
    	
    	//calling the helper function BinarySearch with the "backtracking" feature (stack of the previous data)
    	return binarySearch(arr, 0, arr.length-1, x, myStack);
    	

    }
    
    //binary search function with added "backtracking" functionality ("myStack" is keeping all previous data)
    public static int binarySearch(int arr[], int low, int high, int x, Stack myStack)
    {
    	int inconsistencies;
    	int [] midHigh = new int [2];
    	midHigh[0] = low;
		midHigh[1] = high;
		
		//checking if the algorithm is in bounds
        if (low <= high) {
        	
        	
            int mid = (low + high) / 2;
     
            if (arr[mid] == x) {
                return mid;
            }
     
          
            if (arr[mid] > x) {
            	
    			midHigh[1] = mid - 1;
    			
    			//pushing the current iteration and checking if any backtracking is needed before proceeding
    			//if so, the "midHigh" array will get its data from the previous iterations (according to the value of "inconsistencies"
    			myStack.push(midHigh);
    			inconsistencies = Consistency.isConsistent(arr);
    			
    			while (inconsistencies > 0 & !myStack.isEmpty()) {
    				midHigh = (int[]) myStack.pop();
    				inconsistencies = inconsistencies - 1;
    			
    			}
    		
                return binarySearch(arr, midHigh[0], midHigh[1], x, myStack);
            }
            else {
            	midHigh[0] = mid + 1;
    			
            	//pushing the current iteration and checking if any backtracking is needed before proceeding
    			//if so, the "midHigh" array will get its data from the previous iterations (according to the value of "inconsistencies"
    			myStack.push(midHigh);
    			inconsistencies = Consistency.isConsistent(arr);
    			
    			while (inconsistencies > 0 & !myStack.isEmpty()) {
    				midHigh = (int[]) myStack.pop();
    				inconsistencies = inconsistencies - 1;
    			
    			}
    			
    			return binarySearch(arr, midHigh[0], midHigh[1], x, myStack);
            }
     
         
            
        }
     
    
        return -1;
    }
     
    //binary search helper function
    public static int binarySearch(int arr[], int low, int high, int key)
    {
    	
		
		//checking if the algorithm is in bounds
        if (low <= high) {
        	
        	
            int mid = (low + high) / 2;
     
            if (arr[mid] == key) {
                return mid;
            }
     
          
            if (arr[mid] > key) {
            	
    			high = mid - 1;
    		
                return binarySearch(arr, low, high, key);
            }
            else {
            	low = mid + 1;
    			
    			return binarySearch(arr, low, high, key);
            }
     

        }
     
    
        return -1;
    }
    
	
	

    
}
