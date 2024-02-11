

public class BacktrackingSortedArray implements Array<Integer>, Backtrack {
    private Stack stack;
    public int[] arr; // This field is public for grading purposes. By coding conventions and best practice it should be private.
    private int takenspace;
    private boolean backtracking = false;
    // Do not change the constructor's signature
    public BacktrackingSortedArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        this.takenspace = 0;
    }
    
    @Override
    //returns the value of arr[index] (if the index is a valid input), else - throw an exception
    public Integer get(int index){
        if (index < 0 | index > arr.length -1 ) {
        	throw new IllegalArgumentException("Index must be in array's bounds");
        }
        
    	return this.arr[index];
    }

    @Override
    //Searching for value k in the array and returns its index
    //if the value is not found, -1 is returned
    //searching for 0 will also return -1 because of default values of initialized int array.
    public Integer search(int k) {
    	
    	if (this.takenspace == 0) {
    		return -1;
    	}
        return binarySearch(this.arr,0,this.takenspace - 1, k);
    }
    
    //BinarySearch Helper function for search
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

    @Override
    //Inserting the value x while keeping the array sorted
    public void insert(Integer x) {
    	
    	int [] insert = new int [2];
    	insert[0] = 0;
        if (arr.length - this.takenspace  == 0) {
        	throw new IllegalArgumentException("No more available space in the array");
        }

        	int current = this.takenspace;
            for (int i = current - 1; i >= 0 && arr[i] > x ; i = i - 1) {
            	
            	this.arr[i+1] = this.arr[i];
            	current = i;
            }
            
        this.arr[current] = x;
        this.takenspace = this.takenspace + 1;     
        insert[1] = x;
        
        if (!this.backtracking) {
        	this.stack.push(insert);
        }
       
    }

    @Override
    // deleting the value of arr[index] while keeping the array sorted
    public void delete(Integer index) {

    	 int [] delete = new int [2];
    	 delete[0] = 1;
    	
    	 if (index < 0 | index > arr.length -1 ) {
         	throw new IllegalArgumentException("Index must be in array's bounds");
         }
    	 if (index >= this.takenspace ) {
          	throw new IllegalArgumentException("There is nothing to delete");
         }
    	 
    	
    	 delete[1] = get(index);
    	 
    	 for (int i = index; i < this.takenspace - 1; i = i + 1) {
    		 this.arr[i] = arr[i+1];
    	 }
    	 
    	 if (this.takenspace != 0) {
    		 this.arr[this.takenspace-1] = 0;
    	 }
    	 
    	 this.takenspace = this.takenspace - 1;
    	 if(!this.backtracking) {
    		 this.stack.push(delete);
    	 }
    	 
    }

    @Override
    //returns the minimum value of all the elements in the array (thanks to the fact that the array is sorted, if the array is not empty = the minimum value will be arr[0])
    public Integer minimum() {
    	
    	 if (this.takenspace == 0 ) {
           	throw new IllegalArgumentException("No elements in the array");
         }
    	 
    	return 0; 
    }

    @Override
    //returns the maximum value of all the elements in the array (thanks to the fact that the array is sorted, if the array is not empty the max value will be arr[takenspace - 1]
    public Integer maximum() {
    	
    	 if (this.takenspace == 0 ) {
            	throw new IllegalArgumentException("No elements in the array");
         }
        
  
    	return this.takenspace-1;
    	
    	
    }

    @Override
    //returns the value of the succecor of arr[index]
    //the function checks if the array is not empty / the index is the last element of the array
    public Integer successor(Integer index) {
    	
    	 if (index < 0 | index > this.takenspace - 1 ) {
          	throw new IllegalArgumentException("Index must be in array's bounds (and bounds of existing elements)");
         }
    	 
    	 if (index == this.takenspace - 1 ) {
           	throw new IllegalArgumentException("No successor for the last element of the array");
         }
    	return index + 1; 
    }

    @Override
    //returns the value of the predecessor of arr[index]
    //the function checks if the array is not empty / the index is the first element of the array
    public Integer predecessor(Integer index) {
    	
    	if (index <= 0 | index > arr.length -1 ) {
          	throw new IllegalArgumentException("Index must be in array's bounds and bigger than 0");
        }
    	
    	if (index >= this.takenspace ) {
          	throw new IllegalArgumentException("No element in this index");
         }
    	
    	
    	return index-1; 
    }

    @Override
    //undo the last action implemented on the array
    //using the stack to get info about the last action that was performed on the array.
    //the action is stored in array "backtrack" of size 2, while backtrack[0] indicate what kind of action was performed last (insertion or deletion) and backtrack[1] stores its value) 
    public void backtrack() {
    	
        if(!stack.isEmpty()) {
        	this.backtracking = true;
        	int [] backtrack = (int[]) stack.pop();
        	
        	if (backtrack[0] == 0) {
        		delete(search(backtrack[1]));
        	}
        	else {
        		insert(backtrack[1]);
        	}
        	this.backtracking = false;
        }
    }

    @Override
    public void retrack() {
		/////////////////////////////////////
		// Do not implement anything here! //
		/////////////////////////////////////
    }

    @Override
    //printing the elements of the array with space (" ") between them
    public void print() {
        for (int i = 0; i < this.takenspace; i = i + 1) {
        	System.out.print(get(i) + " ");
        }
    }
    
	
    

    
}
