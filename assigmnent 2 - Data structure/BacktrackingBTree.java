import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class BacktrackingBTree<T extends Comparable<T>> extends BTree<T> {
	// For clarity only, this is the default ctor created implicitly.
	public BacktrackingBTree() {
		super();
	}

	public BacktrackingBTree(int order) {
		super(order);
	}

	//You are to implement the function Backtrack.
	public void Backtrack() {
		if(!backStack.isEmpty()) {
			Deque <Object> lastInsstack = backStack.pop();
			T deleteKey = (T) lastInsstack.pop();
			Node<T> leaf = this.getNode(deleteKey);
			leaf.removeKey(deleteKey); //deleting the last inserted key from the last modified stack in the tree
			this.size = this.size -1;
			while(!lastInsstack.isEmpty()) {
				T splitIndex = (T) lastInsstack.pop();
				Node<T> curr = this.getNode((T) splitIndex);
				Node<T> left = curr.getChild(curr.indexOf(splitIndex));
				Node<T> right = curr.getChild(curr.indexOf(splitIndex)+1);
				
				//merging the left and right sons to one node
				for(int i =0; i <right.getNumberOfKeys(); i = i + 1) {
					left.addKey(right.getKey(i));
					if (right.getChild(i) != null) {
						left.addChild(right.getChild(i));
					}
					
				}
				if (right.getChild(right.getNumberOfKeys()) != null) {
				left.addChild(right.getChild(right.getNumberOfKeys()));
				}
				curr.removeChild(right);
				left.addKey(splitIndex); //adding the mid value from the parent to the newly merged node
				curr.removeKey(splitIndex);
				if (this.root.numOfKeys == 0) {
					this.root = left;
				}
			
			}
			
			
			if (this.root.numOfKeys == 0) {
				this.root = null;
			}
		
			
			
		}
    }
	
	//Change the list returned to a list of integers answering the requirements
	public static List<Integer> BTreeBacktrackingCounterExample(){
		List<Integer> ans = new LinkedList<Integer>(); 
		ans.add(4);
		ans.add(5);
		ans.add(6);
		ans.add(7);
		ans.add(3);
		ans.add(2);

		return ans;
		
		
	}
	


}

