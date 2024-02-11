import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BacktrackingAVL extends AVLTree {
    // For clarity only, this is the default ctor created implicitly.
    public BacktrackingAVL() {
        super();
    }

	//You are to implement the function Backtrack.
    public void Backtrack() {
        if(!backStack.isEmpty()) {
        	Deque<Object> stck = backStack.pop();
        	int state = (int) stck.pop();
        	
        	if (state == 0) {
        		Node inserted  = (Node) stck.pop();
        		Node funbalanced = inserted.parent;
        		
        		if (inserted == root) {
        			root = null;
        			
        		}
        		else {
        			if (funbalanced.left == inserted) {
            			funbalanced.left = null;
            			funbalanced.updateHeight();
            	
            		}
            		else {
            			funbalanced.right = null;
            			funbalanced.updateHeight();
            			
            		}
        			
        		}
        	
        		
        		
        	}
        	
        	if (state == 1) { // left-left
        		Node unbalanced  = (Node) stck.pop();
        		Node inserted  = (Node) stck.pop();
        		Node funbalanced = unbalanced.parent;
        		Node ffunbalanced = funbalanced.parent;
        		
        		String side = "left";
        		if (ffunbalanced != null && ffunbalanced.right == funbalanced) {
        			side = "right";
        		}
        		
        		if (funbalanced != root) { //
        			if (side == "left") {
        				ffunbalanced.left = this.rotateLeft(funbalanced);
        			}
        			else {
        				ffunbalanced.right = this.rotateLeft(funbalanced);
        			}
        		
        			funbalanced.left = null;
        			
        			funbalanced.updateHeight();
        			root.left.updateHeight();
        			root.updateHeight();
        		
        		}
   
        		else { 
        			root = this.rotateLeft(root);
        			removeNode(inserted);
        		}
        		
        		
        	}
        	
        	if (state == 2) { // right-right
        		Node unbalanced  = (Node) stck.pop();
        		Node inserted  = (Node) stck.pop();
        		Node funbalanced = unbalanced.parent;
        		Node ffunbalanced = funbalanced.parent;
        		String side = "left";
        		if (ffunbalanced != null && ffunbalanced.right == funbalanced) {
        			side = "right";
        		}
        		if (funbalanced != root) { //
        			if (side == "left") {
        				ffunbalanced.left = this.rotateRight(funbalanced);
        			}
        			else {
        				ffunbalanced.right = this.rotateRight(funbalanced);
        			}
        			
        			
        			ffunbalanced.right.updateHeight();
        			removeNode(inserted);
        		
        			
        			
        		
        		}
   
        		else { 
        			root = this.rotateRight(root);
        			
        			removeNode(inserted);
        
        			
        			
        		}
        	}
        		
        	if (state == 3) { // left-right
            	Node unbalanced  = (Node) stck.pop();
            	Node inserted  = (Node) stck.pop();
            	Node funbalanced = unbalanced.parent;
            	Node ffunbalanced = funbalanced.parent;
            	
            	
            	if (funbalanced != root) { //
            		ffunbalanced.left = this.rotateLeft(inserted);
            		unbalanced.left = inserted.left;
            		unbalanced.updateHeight();

            	}
       
            	else { 
            		root = this.rotateLeft(root);
            		root.left = this.rotateRight(root.left);
            		removeNode(inserted);
            		root.updateHeight();
           		
            	}
            	

        	}
        	if (state == 4) { // right-left
            	Node unbalanced  = (Node) stck.pop();
            	Node inserted  = (Node) stck.pop();
            	Node funbalanced = unbalanced.parent;
            	Node ffunbalanced = funbalanced.parent;
            	
            	
            	if (funbalanced != root) { //
            		ffunbalanced.right = this.rotateRight(inserted);
            		unbalanced.right = inserted.right;
            		unbalanced.updateHeight();

            	}
       
            	else { 
            		root = this.rotateRight(root);
            		root.right = this.rotateLeft(root.right);
            		root.right.parent = root;
            		removeNode(inserted);
            
            			
            	}
        	}
        }
    }
    
    private void removeNode(Node toRemove) {
		Node heightUpdate = toRemove.parent;
		if (toRemove == root) {
			toRemove = null;
			root.size --;
		}
			
		
		else {
			if (toRemove.parent.left == toRemove) {
				toRemove.parent.left = null;
				
			}
				
			
			else {
				toRemove.parent.right = null;
			}
				
			toRemove.parent = null;
			toRemove.parent.size --;
		}
		while (heightUpdate != null) {
			heightUpdate.updateHeight();
			heightUpdate = heightUpdate.parent;
		}
		
	}
    
    //Change the list returned to a list of integers answering the requirements
    public static List<Integer> AVLTreeBacktrackingCounterExample() {
    	List<Integer> ans = new LinkedList<Integer>(); 
		ans.add(1);
		ans.add(2);
		ans.add(3);

		return ans;
    }
    
    //helper function for Select:
    public int Select(Node node, int index) {
    	if (node == null) {
    		return 1;
    	}
    	int add = 0;
    	if (node.left != null) {
    		add = node.left.size;
    	}
        int currentRank = add + 1;
        if (currentRank == index) {
        	return node.value;
        }
        else if (index < currentRank) {
        	return Select(node.left, index);
        }
        else {
        	return Select(node.right, index - currentRank);
        }
            		
            		
        
        		
    }
    
    public int Select(int index) {
        return Select(this.root,index);
    }
    
    //helper function for Rank:
    public int Rank(Node node, int value) {
    	if (node != null) {
        if (node.value == value) {
        	if(node.left == null) {
        		return 0;
        	}
        	else{
        		return node.left.size;
        	}
        	
        }
        else if (node.value > value){
        	return Rank(node.left,value);
        	
        }
        else {
        	int add = 0;
        	if (node.left != null) {
        		add = node.left.size;
        	}
        	return Rank(node.right,value) + add +1;
        }
    	}
    	return 0;
    }
    
    public int Rank(int value) {
   
    	return Rank(this.root,value);
    }
    
   
    

    }
