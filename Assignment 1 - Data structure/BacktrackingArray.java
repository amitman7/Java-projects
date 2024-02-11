import java.util.Arrays;

public class BacktrackingArray implements Array<Integer>, Backtrack {
    private Stack stack;
    private int[] arr;
    private int arrIndex; 
   

    // Do not change the constructor's signature
    public BacktrackingArray(Stack stack, int size) {
        this.stack = stack;
        arr = new int[size];
        arrIndex = 0;
        
    }

    @Override
    public Integer get(int index){
    	if (index > arrIndex) {
    		throw new IllegalArgumentException("the index is not valid");
    	}
    	//passing the cell that its value is -1, to get the real value
    	int realIndex = 0;
    	int tempIndex = index ;
    	while (tempIndex>0) {
    		 if(arr[realIndex] != -1) {
    			 tempIndex = tempIndex -1;
    			 realIndex = realIndex + 1;
    		 }
    		 else{
    			 realIndex = realIndex + 1;
    		 }
    	 }
    	//dealing the situation which the "tempIndex" ended in cell with -1 
    	while (arr[realIndex] == -1) {
    		realIndex = realIndex +1;
    	}
    	
    	if (realIndex >= arrIndex) {
    		throw new IllegalArgumentException("the index is not valid");
    	}
    	return arr[realIndex];
    }

    
    @Override
    public Integer search(int k) {
    	int index = 0;
        for (int i = 0; i<arrIndex; i=i+1) {
        	if (arr[i] == k) {
        		return index ;
        	}
        	//passing the cell with -1 value to get the real index
        	if(arr[i] != -1) {
        		index = index + 1;
        	}
        }
    	return -1; 
    }

    @Override
    public void insert(Integer x) {
    	if (arrIndex > arr.length) {
    		throw new IllegalArgumentException("the array is full");
    	}
    	arr[arrIndex] = x;
    	
    	int [] toInsert = new int [3];
    	toInsert[0] = arrIndex;// index
    	toInsert[1] = x; // value
    	toInsert[2] = 0; //  insert or delete
    	stack.push(toInsert);
    	arrIndex = arrIndex +1;	
    	
    }

    @Override
    public void delete(Integer index) {
    	if (arrIndex ==0) {
    		throw new IllegalArgumentException("cant delete from empty set");
    	}
    	
    	int [] toDelete = new int [3];
    	int realVal = get(index);
    	int realindex = search(realVal);
    	
    	toDelete[0] = realindex;// index
    	toDelete[1] = realVal; // value
    	toDelete[2] = -1; //  insert or delete
    	stack.push(toDelete);
    	
    	arr[realindex] = -1;
    	}

    @Override
    public Integer minimum() {
    	if (arrIndex ==0) {
    		throw new IllegalArgumentException("cant find min in an empty set");
    	}
    	
    	int min = arr[0];
    	for (int i =0;i<arrIndex;i=i+1) {
    		if (arr[i] < min & arr[i] != -1) {
    			min = arr[i];
    		}
    		if(arr[i] != -1) {	
    		}
    	}
       int indexMin = search(min); // using search to get the real index
       return indexMin;
    }

    @Override
    public Integer maximum() {
    	if (arrIndex ==0) {
    		throw new IllegalArgumentException("cant find max in an empty set");
    	}
    	
    	int max = arr[0];
    	for (int i =0;i<arrIndex;i=i+1) {
    		if (arr[i] > max & arr[i] != -1) {
	        	max = arr[i];
	        }
    	}
    	int indexMax = search(max); // using search to get the real index
        return indexMax;
    }

    @Override
    public Integer successor(Integer index) {
    	int max = maximum();
    	int current = get(index);
    	
    	//Exceptions
    	if (current ==  get(max)) {
    		throw new IllegalArgumentException("this is the maximum, there is no successor");
    	}
    	if (index > arrIndex) {
    		throw new IllegalArgumentException("the index is not valid");
    	}
    	
    	//finding the minimum value that bigger then the current, starting with the max.
    	int successor = arr[max];
    	for (int i =0;i<arrIndex;i=i+1) {
    		if (arr[i] > current & arr[i] < successor & arr[i] != -1) {
    			successor = arr[i]; 
    		}
    	}

    	return search(successor);
    	
    }

    @Override
    public Integer predecessor(Integer index) {
    	int min = minimum();
    	int current = get(index);
    	
    	//Exceptions
    	if (current == get(min)) {
    	   throw new IllegalArgumentException("this is the minimum, there is no predecessor");
    	}
    	if (index > arr.length) {
    		throw new IllegalArgumentException("the index is not valid");
    	}
    	
    	//finding the maximum value that smaller then the current, starting with the min.
    	int predecessor = arr[min];
    	for (int i =0;i<arrIndex;i=i+1) {
    		if (arr[i] < current & arr[i] > predecessor & arr[i] != -1) {
    			predecessor = arr[i]; 
    		}
    	}
    	return search(predecessor);
    }

    
    @Override
    public void backtrack() {
    	if (!stack.isEmpty()) {
    		int [] toBack = (int[]) stack.pop();
    		
    		//checking if the last operation was "delete" or "insert".
    		if (toBack[2]== 0) {// we need to delete
    			int indexDel = toBack[0];
    			arr[indexDel] = -1;
    		}
    		else { // we need to insert
    			int indexIns = toBack[0];
    			arr[indexIns] = toBack[1];
    		}
    		
    	}
    }
    @Override
    public void retrack() {
		/////////////////////////////////////
		// Do not implement anything here! //
		/////////////////////////////////////
    }

    @Override
    public void print() {
    	String toString= "";
    
    	for (int i =0;i<arrIndex;i= i+1) {
    		if (arr[i] != -1) {
    			toString = toString +arr[i] + " " ;
    		}
    	}
       System.out.println(toString);
    }
   
    
}
