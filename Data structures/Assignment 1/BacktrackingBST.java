
import java.util.NoSuchElementException;

public class BacktrackingBST implements Backtrack, ADTSet<BacktrackingBST.Node> {
    private Stack stack;
    private Stack redoStack;
    private BacktrackingBST.Node root = null;
    private boolean backtracking = false; //"true" when a backtracking action is in action
   
    
    // Do not change the constructor's signature
    public BacktrackingBST(Stack stack, Stack redoStack) {
        this.stack = stack;
        this.redoStack = redoStack;
        
        
    }

    //returns the root of the tree
    public Node getRoot() {
    	if (root == null) {
    		throw new NoSuchElementException("empty tree has no root");
    	}
        return root;
    }
	
    //search node in the tree with key "k"
    public Node search(int k) {
        if (root == null) {
        	return null;
        }
    	return bstSearch(this.root,k); 
    }
    
    //helper function for search for bst
    private Node bstSearch(Node node, int key) {
    	
    	if (node != null && key == (int) node.getKey()) {
    		return node;
    	}
    	
    	if (node != null && (int) node.getKey() > key) {
    		if (node.left != null) {
    			return bstSearch(node.left,key);
    		}

    	}
    	else if (node.right != null) {
    		return bstSearch(node.right,key);
    	}

    	return null;
    }

    
    //inserting new node to the tree
    public void insert(Node node) {
    	if (node == null) { 
    		throw new IllegalArgumentException("cant insert null");
    	}
    	
    	Node temp = null;
    	Node tempRoot = this.root;
    	
    	while (tempRoot!=null) {
    		temp = tempRoot;
    		if (node.getKey() < tempRoot.getKey()) {
    			tempRoot = tempRoot.left;
    		}
    		else {
    			tempRoot = tempRoot.right;
    		}
    	}
    	node.parent = temp;
    	if (temp == null) {
    		this.root = node;
    	}
    	else if(node.getKey() < temp.getKey()) {
    		temp.left = node;
    	}
    	else {
    		temp.right = node;
    	}
    	
    	// pushing to the stack for the backtracking (will only push data to the backtracking stack if "backtracking" is false)
        if(!backtracking) {
        	
        	emptystack();
        	Object [] insert = new Object[2];
            insert[0] = 0;
            insert[1] = node.getKey();
            this.stack.push(insert);
        }
    }
 
    //deleting node from the tree
    //stage 1 = node has 0 children
    //stage 2 = node has 1 children
    //stage 3 = node has 2 children
    public void delete(Node node) {
    	
    	if (node == null) {
    		throw new IllegalArgumentException("cant insert null");
    	}
    	
    	int stage = 0; 
    	int side = 0; //parameter for the side of the child. side 0 = left, side 1 = right
    	BacktrackingBST.Node toDelete = search(node.getKey());
    	
    	// the the node has 0 children
    	if (toDelete.left == null & toDelete.right == null) {
    		stage = 1;
    		
    		if (toDelete.parent.left == toDelete) { //checking if the child is a left or right child to its parent
    			toDelete.parent.left = null;
    			side = 0;
    		}
    		else {
    			toDelete.parent.right = null;
    			System.out.println("after delete 22:");
    			System.out.println(toString());
    			side = 0;
    		}
    		
    		// pushing to the stack for the backtracking
    		if (!backtracking) {
    			emptystack();
    			
        		Object [] delete = new Object[4];
        		delete[0] = 1;
        		delete[1] = stage;
        		delete[2] = toDelete;
        		delete[3] = side;
        		this.stack.push(delete);
        	}
    	}
    	
    	// the node has 2 children
    	else if (toDelete.left != null & toDelete.right != null) {
    		boolean root = false;
    		if (toDelete == this.root) {
    			root = true;
    		}
    		BacktrackingBST.Node successor = successor(toDelete);
    		
    		//pushing to the stack for the backtracking
    		if (!backtracking) {
    			emptystack(); //emptying the "redo" stack because a non-backtrack action was done
    			
    			String sideOfSuccessor = "right";
    			if (successor.parent.left == successor) {
    				sideOfSuccessor = "left";
    			}
        		Object [] delete = new Object[6];
        		delete[0] = 1;
        		delete[1] = 3;
        		delete[2] = toDelete.getKey();
        		delete[3] = successor;
        		delete[4] = sideOfSuccessor;
        		delete[5] = root;
        		this.stack.push(delete);
        	}
    		backtracking = true;
    		delete(successor);
    		backtracking = false;
    		toDelete.key = successor.getKey();
    	}
    	
    	//The node has only 1 children
    	else if (toDelete.left != null | toDelete.right != null) {
    		stage = 2;
    		int sideOfMe = 0;
    		//getting the child
    		BacktrackingBST.Node child;
    		if (toDelete.left == null) {
    			child = toDelete.right;
    		}
    		else {
        		child = toDelete.left;
    		}
    		if (toDelete == root) { // node is the root
    			root = child;
    			sideOfMe = -1;
    		}
    		else { //node is not the root
    			if (toDelete.parent.left == toDelete) {
        			toDelete.parent.left = child;
        			child.parent = toDelete.parent; //changing the parent of the child
        			sideOfMe = 0;
        		}
        		else {
        			toDelete.parent.right = child;
        			child.parent = toDelete.parent; //changing the parent of the child
        			sideOfMe = 1;
        		}
    		}
    		
    		//pushing to the stack for the backtracking
    		if (!backtracking) {
    			
    			emptystack(); //emptying the "redo" stack because a non-backtrack action was done
        		Object [] delete = new Object[5];
        		delete[0] = 1;
        		delete[1] = stage;
        		delete[2] = toDelete;
        		delete[3] = sideOfMe; //side of toDelete as a son
        		delete[4] = child;
        		this.stack.push(delete);
        	}
    	}
    }

    
    //returns the node with the minimum value in the tree
    public Node minimum() {
    	if (this.root == null) {
    		throw new IllegalArgumentException("No minimum element in empty tree");
    	}
    	BacktrackingBST.Node currentNode = this.root;
        while (currentNode.left != null) {
        	currentNode = currentNode.left;
        }
        return currentNode;
        
    }
    
    //returns the node with the maximum value in the tree
    public Node maximum() {
    	if (this.root == null) {
    		throw new IllegalArgumentException("No maximum element in empty tree");
    	}
    	
    	BacktrackingBST.Node currentNode = this.root;
        while (currentNode.right != null) {
        	currentNode = currentNode.right;
        }
        return currentNode;
    }
    
    //returns the successor of "node" in the tree
    public Node successor(Node node) {
    	if (node == null) {
    		throw new IllegalArgumentException("No successor for null");	
    	}
    	if (maximum().getKey() == node.getKey()) {
    		throw new IllegalArgumentException("No successor for the maximum in the array");
    	}
    	
        if (search(node.getKey()).right != null) {
        	Node currentNode = search(node.getKey()).right;
            while (currentNode.left != null) {
            	currentNode = currentNode.left;
            }
            
           return currentNode;
        }
        
        BacktrackingBST.Node parent = search(node.getKey()).parent;
        while (parent != null & node == parent.right) {
        	node = parent;
        	parent = parent.parent;
        }
        
        return parent;
    }

    //returns the predecessor of "node" in the tree
    public Node predecessor(Node node) {
    	if (node == null) {
    		throw new IllegalArgumentException("No successor for null");	
    	}
    	if (minimum().getKey() == node.getKey()) {
    		throw new IllegalArgumentException("No predecessor for the minimum in the array");
    	}
        if (search(node.getKey()).left != null) {
        	Node currentNode = search(node.getKey()).left;
            while (currentNode.right != null) {
            	currentNode = currentNode.right;
            }
           
           return currentNode;
        }
        
        BacktrackingBST.Node parent = search(node.getKey()).parent;
        while (parent != null & search(node.getKey()) == parent.left) {
        	node = parent;
        	parent = parent.parent;
        }
        
        return parent;
    }

    @Override
    //backtrack the ADT to its stage before the last insert/delete action
    public void backtrack() {
    	
    	backtracking = true; //activating backtrack
    	
    	if (!stack.isEmpty()) { //checking if there are any action to backtrack
    		
    		
    		Object[] backtrack = (Object[]) this.stack.pop();
    		this.redoStack.push(backtrack);
    		 
    		 if ((int) backtrack[0] == 0) {
    			 delete(search((int) backtrack[1]));
    		 }
    		 else { //the last action performed was delete
    			 if ((int) backtrack[1] == 1) { // stage 1
    				 BacktrackingBST.Node child = (BacktrackingBST.Node) backtrack[2];
    				 //checking if the deleted node was a left child
    				 if ((int) backtrack[3] == 0) {
    					 child.parent.left = (BacktrackingBST.Node) backtrack[2];
    				 }
    				 else { //the deleted node was a right child
    					 child.parent.right = (BacktrackingBST.Node) backtrack[2];
    				 } 
    			 }
    			 if ((int) backtrack[1] == 2) { //stage 2 - the deleted node has only 1 child
    				 BacktrackingBST.Node deletedNode = (BacktrackingBST.Node) backtrack[2];
    				 BacktrackingBST.Node child;
    				 
    				 if (deletedNode.left != null) {
    					 deletedNode.left = search(deletedNode.left.getKey());
    				 }
    				 else {
    					 deletedNode.right = search(deletedNode.right.getKey());
    				 }
    				 if ((int)backtrack[3] == 0) {
    					 
    					 search(deletedNode.parent.getKey()).left = deletedNode;
    				 }
    				 if ((int)backtrack[3] == 1){
    					 search(deletedNode.parent.getKey()).right = deletedNode;
    				 }
    				 if ((int)backtrack[3] == -1){ //the deleted node was a root
    					 root = deletedNode;
    				 }
    				 
    			 }
    			 if ((int) backtrack[1] == 3) { //stage 3 - the deleted node had 2 children
    				 
    				 if ((boolean) backtrack[5]) { //the deleted node was a root
    					 BacktrackingBST.Node fatherOfFormerSuccessor = (BacktrackingBST.Node) backtrack[3];
    					 fatherOfFormerSuccessor = fatherOfFormerSuccessor.parent;
    					 root.key = (int) backtrack[2];
    					 if (backtrack[4] == "right") {
    						 fatherOfFormerSuccessor.right = (BacktrackingBST.Node) backtrack[3];
    					 }
    					 else {
    						 fatherOfFormerSuccessor.left = (BacktrackingBST.Node) backtrack[3];
    					 }
    					 
    				 }
    				 else {
    					 BacktrackingBST.Node originalSuccessor = (BacktrackingBST.Node) backtrack[3];
        				 BacktrackingBST.Node currentSuccessor = successor(search(originalSuccessor.getKey()));
        				 
        				 //checking if current successor is a left or right child to its parent
        				 if (backtrack[4] == "left") {
        					 search(currentSuccessor.getKey()).left = originalSuccessor;
        					 
        				 }
        				 else {
        					 search(currentSuccessor.getKey()).right = originalSuccessor;
        				 }
        				 search(originalSuccessor.getKey()).key = (int) backtrack[2]; //changing the key to the original one
    				 } 
    			 }
    		 }
    	}
    	backtracking = false;
    }

    @Override
    
    //redo the last backtrack that was done
    public void retrack() {
    	if (!this.redoStack.isEmpty()) {
    		Object[] retrack = (Object[]) this.redoStack.pop();
            
            //the last backtrack was on insert (need to insert back)
            if ((int) retrack[0] == 0) {
            	BacktrackingBST.Node toInsert = new BacktrackingBST.Node ((int) retrack[1], null);
            	insert(toInsert);
            }
            else { //the last backtrack was on delete (need to delete again)
            	BacktrackingBST.Node toDelete = (Node) retrack[2];
            	delete(toDelete);
            }
         }
    		
    	}
        
    
    
    //priting the tree is preOrder
    public void printPreOrder(){
        PreOrderRec(this.root);
    }
    
    //helper function for printPreOrder
    private void PreOrderRec(BacktrackingBST.Node Node) {
    	
    	if (Node != null) {
    		System.out.print(Node.getKey() + " ");
    		PreOrderRec(Node.left);
    		PreOrderRec(Node.right);
    	}
    	
    	
    	
    }

    @Override
    //printing the tree is preOrder
    public void print() {
    	printPreOrder();
    }
    
    //helper function to empty the "redo" stack
    private void emptystack() {
    	while (!this.redoStack.isEmpty()) {
    		this.redoStack.pop();
    	}
    }
    
    
	

    public static class Node {
    	// These fields are public for grading purposes. By coding conventions and best practice they should be private.
        public BacktrackingBST.Node left;
        public BacktrackingBST.Node right;
        
        private BacktrackingBST.Node parent;
        private int key;
        private Object value;

        public Node(int key, Object value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }
        
       
		
		
       
        
    }
    
    
    
   

}