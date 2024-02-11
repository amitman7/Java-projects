/*
I, <Amit Man> (<206961120>), assert that the work I submitted is entirely my own.
I have not received any part from any other student in the class,
nor did I give parts of it for use to others.
I realize that if my work is found to contain code that is not originally my own,
 a formal case will be opened against me with the BGU disciplinary committee.
*/

import java.util.LinkedList;
import java.util.Iterator;

public class BitList extends LinkedList<Bit> {
    
    private int numberOfOnes;

    // Do not change the constructor
    public BitList() {
        numberOfOnes = 0;
    }

    // Do not change the method
    public int getNumberOfOnes() {
        return numberOfOnes;
    }


//=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.1 ================================================

    public void addLast(Bit element) {
    	if (element == null) {
    		throw new IllegalArgumentException("null is not legal insert");
    	}
    	if (element.equals(Bit.ONE))  {
    		numberOfOnes = numberOfOnes +1;
    	}
    	super.addLast(element);
    	
    }
    

    public void addFirst(Bit element) {
    	if (element == null) {
    		throw new IllegalArgumentException("null is not legal insert");
    	}
    	if (element.equals(Bit.ONE))  {
    		numberOfOnes = numberOfOnes +1;
    	}
    	
    	super.addFirst(element);
    	
    }
    public Bit removeLast() {
    	Bit removeL = super.removeLast();
    	if (removeL.equals(Bit.ONE)) {
    		numberOfOnes = numberOfOnes -1;
    	}
    	
    	return removeL;
    }

    public Bit removeFirst() {
    	Bit removeF = super.removeFirst();
    	if (removeF.equals(Bit.ONE)) {
    		numberOfOnes = numberOfOnes -1;
    	}
    	
    	return removeF;
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.2 ================================================
    public String toString() {
    	Iterator<Bit> iter = iterator() ;
    	String output = "";
    	while (iter.hasNext()){
    		output =  iter.next()  + output;
    	}
    	
    	output = "<" + output + ">";
    	 
        return output;
    }
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.3 ================================================
    public BitList(BitList other) {
    	if (other == null) { 
        throw new IllegalArgumentException("null cant be an insert");
        }
    	
    	Iterator<Bit> iter = other.iterator() ;
    	while (iter.hasNext()) {
    		this.addLast(iter.next());
    	}
    	
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.4 ================================================
    public boolean isNumber() {
    	boolean ans  = false;
    	if (size() > 0 && (getLast().toInt() == 0 | numberOfOnes >1)) {
    		ans = true;
    	}
    
    	return ans;
    }
       
    
    
    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.5 ================================================
    public boolean isReduced() {
    	boolean ans = false;
    	
    	boolean isNum = false;
    	if (isNumber() == true) { 
    		isNum = true;
         }
    	
    	boolean firstCon = false;// the first of three conditions - the value of the Bit is -1 or 0 or 1.
    	if  ((isNum == true) && (toString().equals("<0>") | toString().equals("<01>") | toString().equals("<11>")))  {
    		firstCon = true;
    	}
    	
    	boolean secondCon = false;// the second of three conditions - at least 3 bits and the last 2 must be <01> or <10>. 
    	if ((isNum == true) && (size() > 2 &&  ((getLast().toInt() == 1 & get(size()-2).toInt() == 0) | (getLast().toInt() == 0 & get(size()-2).toInt() == 1)))) {
    		secondCon = true;
    	}
    	
    	boolean threeCon = false;// the three out of three conditions - at least 3 bits and there are only 2 ones and they are at the end .
    	if  ((isNum == true) && (size() > 2 && numberOfOnes == 2 & getLast().toInt() == 1 & get(size()-2).toInt() == 1) & (isNum == true)) {
    		threeCon = true;
    		
    	}
    	
    	if  (firstCon == true | secondCon == true | threeCon == true) {
    		ans = true;
    	}
    	
    	return ans;
    
    }
    

    public void reduce() {
    	while (isNumber() == true & isReduced() == false) {
    		removeLast();
    	}
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.6 ================================================
    public BitList complement() {
    	Iterator<Bit> iter = iterator() ;
    	BitList comp = new BitList();
    	while (iter.hasNext()) {
    		if (iter.next().toInt() == 1) {
    			comp.addLast(Bit.ZERO);
    		}
    		else {
    			comp.addLast(Bit.ONE);
    		}
    	}
    	
        return comp;
    }
   

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.7 ================================================
    public Bit shiftRight() {
    	Bit remove = null;
        if (size() > 0) {
    	   remove = removeFirst();   
       }
       return remove;
    }

    public void shiftLeft() {
    	addFirst(Bit.ZERO);
      
    }

    //=========================== Intro2CS 2021, ASSIGNMENT 4, TASK 2.8 ================================================
    public void padding(int newLength) {
    	if (size() == 0) {
    		throw new IllegalArgumentException("empty list cannot be pad");
    	}
     
    	int numOfBits = newLength - super.size();
    	if (numOfBits > 0) {
    		Bit bitToAdd = getLast();
    		for (int i = 0;i < numOfBits; i = i + 1) {
    			addLast(bitToAdd);
    		}
    	}
    }

    

    //----------------------------------------------------------------------------------------------------------
    // The following overriding methods must not be changed.
    //----------------------------------------------------------------------------------------------------------
    public boolean add(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public void add(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit remove(int index) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offer(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerFirst(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean offerLast(Bit e) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public Bit set(int index, Bit element) {
        throw new UnsupportedOperationException("Do not use this method!");
    }

    public boolean remove(Object o) {
        throw new UnsupportedOperationException("Do not use this method!");
    }
}
