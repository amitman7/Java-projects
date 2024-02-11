import java.util.Arrays;

public class Assignment2{
	

	/*-----------------------
	 *| Part A - tasks 1-11 |
	 * ----------------------*/
	
	// task 1
	public static boolean isSquareMatrix(boolean[][] matrix) {
		boolean res = true;
		if (matrix == null || matrix.length == 0 ) {
			res = false;	
		}
		for (int i=0;matrix !=null && i< matrix.length; i=i+1 ) {
			if (matrix [i] == null) {
				res = false;
			}
		}
		for (int i=0;(res == true) &&  (i < matrix.length); i= i+1) {
			if (matrix[i].length != matrix.length) {
				res = false;}
		}		
		return res;	
	}
	
	// task 2
	public static boolean isSymmetricMatrix(boolean[][] matrix) {
		boolean res = true;
		for (int i=0;(i<matrix.length -1) & (res == true); i=i+1) {
			for ( int j= i+1; j < matrix.length; j=j+1) {
				if (matrix[i][j] != matrix[j][i]) {
					res= false;	
				}
			}
			
		}
		return res;
	}

	// task 3
	public static boolean isAntiReflexiveMatrix(boolean[][] matrix) {
		boolean res = true;
		
		for (int i=0; (i<matrix.length) & (res == true);i=i+1 ) {
			if (matrix[i][i] == true)
				res = false;
		
		}		
		return res;
				
	}
	
	// task 4
	public static boolean isLegalInstance(boolean[][] matrix) {
		boolean res = false;
		if ((isSquareMatrix(matrix)) && (isSymmetricMatrix (matrix)) & (isAntiReflexiveMatrix(matrix)) == true) {
			res = true;
			
		}
		return res;
				
	}
	
	// task 5. 
	//using selectionSort to arrange the array from min to max and then going over every index.
	public static boolean isPermutation(int[] array) {
		boolean res = false;
		if  (array.length == 0 ) {
			res = true;}
		selectionSort(array);
		if (array.length != 0 ) {
			if ((array[0] == 0 ) & ( array[array.length -1] == array.length-1 )) {
				res= true;	
		}
			for (int i=1;i< array.length & res==true;i = i+1) {
				if (array[i] == array[i-1]) {
					res = false;
				}
					
			}
		}
		
		return res;
	}
	// assumes array!=null
		public static void selectionSort(int[] array){
			for (int i = 0; i<array.length-1; i=i+1){
				int minInd = minIndex(array,i);
				swap(array,i,minInd);
			}
		} // selectionSort

		// Assumes array!=null & from<a.length
		public static int minIndex(int[] array, int from) {
			int minIndex = from;
			for(int i = from+1; i<array.length; i=i+1) 
				if (array[i] < array[minIndex]) 
					minIndex = i;  
			return minIndex;
		} // minIndex

		//swap element array[i] with array[j]
		// Assumes array!=null
		public static void swap(int[] array, int i, int j){
			int tmp = array[i];
			array[i] = array[j];
			array[j] = tmp;
		} // swap
		
		
		
	// task 6
	public static boolean hasLegalSteps(boolean[][] flights, int[] tour) {
		int n= tour.length;
		boolean res = true;
		if (flights[tour[n-1]] [tour[0]] == false) {
			res = false;}
			
		for (int i=0;i<n-1;i=+i+1) {
			if (flights[tour[i]] [tour[i+1]] == false){
				res = false;
			}
		}
			
		return res;
		}		
			
	
	// task 7
	public static boolean isSolution(boolean[][] flights, int[] tour) {
		int n = flights.length; 
		if (tour.length != n) {
			throw new IllegalArgumentException("tour length should be n");}
		boolean res = false;
		if((tour[0] == 0) && (isPermutation(tour) == true) & (hasLegalSteps(flights,tour) == true)){
			res = true; 
			
		}
		return res;
	}
	
	
	// task 8
	// using 2 aid functions to evaluate if the cnf satisfactory. 
	public static boolean evaluate(int[][] cnf, boolean[] assign) {
		boolean res = true;
		for (int i =0; i<cnf.length & res ; i =i+1) {
			res = res & evaluateClause(cnf[i],assign);
		}
		return res; 
	}
	// find if every Literal is satisfactory
		public static boolean evaluateLiteral(int literal, boolean[] assign) {
			boolean res = false;
			if (literal>0) {
				res = assign [literal];
			}
			else
				res = !assign[-literal];
			return res;			
		}
		// taking the Literals and puting it inside the calsues. then check if every calsue satisfactory
		public static boolean evaluateClause(int[] clause, boolean[] assign) {
			boolean res = false;
			for (int i=0; i<clause.length; i= i+1) {
				int literal = clause[i];
				res= res | evaluateLiteral(literal,assign);	
			}
			return res;
	    }
		
		
			
	// task 9
	public static int[][] atLeastOne(int[] lits) {
		int numOfClasuses = 1;
		int [][] cnf =  new int [numOfClasuses][];
		cnf[0] = lits;
	
		return cnf;}
		
		
	

	// task 10
	public static int[][] atMostOne(int[] lits) {
		int n = lits.length;
		int numOfClasuses = n * (n-1) / 2;
		int [][] cnf =  new int [numOfClasuses][];
		
		int cnfIndex= 0;
		for (int i = 0; i< n; i = i+1) {
			for (int j = i + 1; j < n; j= j+1) {
				int [] clause = {-lits[i], -lits[j]};
				cnf [cnfIndex] = clause;
				cnfIndex = cnfIndex +1;
				}
			}
		return cnf;
		}
		
	
				
	
	
	// task 11
	//using  the functions atMostOne and atLeastOne to find the exactlyOne.
	public static int[][] exactlyOne(int[] lits) {
		int n = lits.length;
		int numOfClasuses = (n * (n-1) / 2) + 1;
		int [][] cnf =  new int [numOfClasuses][];
		int [][] cnf1 = new int [numOfClasuses -1][];
		int [][] cnf2 = new int [1][];
		cnf1 = atMostOne(lits);
		cnf2 = atLeastOne(lits);
		cnf = uniarrys(cnf1,cnf2);//fuction to unite to Arrays
		
		
		return cnf;
			}
			
	
	
	/*------------------------
	 *| Part B - tasks 12-20 |
	 * -----------------------*/
	
	// task 12a
	public static int map(int i, int j, int n) {
		int ans = 0;
		ans = (n * i) + j + 1;
		
		return ans;

	}
	
	// task 12b
	public static int[] reverseMap(int k, int n) {
		int [] ans = new int [2];
		int i = (k-1) / n;
		int j = (k-1) % n;
		
		ans [0] = i;
		ans [1] = j;
		
		return ans;
	}
	
	// task 13
	//using the function exactlyOne to check if in each part of the tour there is only one Satisfied city.
	public static int[][] oneCityInEachStep(int n) {
		int [] list = new int [n];
		int [][] cnf1 = new int [0] [];
		int [][] cnf = new int [0] [];
		for(int i =0;i<n; i= i+1) {
			for (int j=0;j<n;j=j+1) {
				list[j]= map (i,j,n);
			}
			cnf1 = exactlyOne(list);
			cnf = uniarrys(cnf1,cnf);
		}
		return cnf;
	}
	public static int[][] uniarrys (int[][] cnf1, int [][] cnf2){// function to unite 2 arrays.
		int [][] cnf = new int [cnf1.length + cnf2.length] [];
		for (int i=0; i<cnf1.length; i= i+1) {
			cnf [i]= cnf1[i];
		}
		for (int j = cnf1.length; j<(cnf1.length + cnf2.length); j=j+1) {
			int d= 0;//Variable to go over cnf2 index.
			cnf [j]= cnf2[d];
			d=d+1;
		}
		return cnf;
	}

	// task 14
	//using the function exactlyOne to check if visiting each city only once.
	public static int[][] eachCityIsVisitedOnce(int n) {
		int [] list = new int [n];
		int [][] cnf1 = new int [0] [];
		int [][] cnf = new int [0] [];
		for(int j =0;j<n; j= j+1) {
			for (int i=0;i<n;i=i+1) {
				list[i]= map (i,j,n);
			}
			cnf1 = exactlyOne(list);
			cnf = uniarrys(cnf1,cnf);
		}
		return cnf;
	}
	
	
	
	// task 15
	public static int[][] fixSourceCity(int n) {
		int [][] cnf = {{map(0,0,n)}};
		
		return cnf;
	}
		
		
	
	
	// task 16
	public static int[][] noIllegalSteps(boolean[][] flights) {
		int n = flights.length;
		int [] list = new int [2];
		int [][] cnf = new int [0] [];
		
		for (int i = 0; i < n; i = i + 1) {
			for (int j = 0; j < n; j = j + 1) {
				if ((flights[i][j] == false) & (i != j)){
					for (int d = 0; d < n; d = d + 1) {//the third loop is to go over the whole connections of the falses 
						list[0] = map(d,i,n);
						list[1] = map(((d + 1)%n),j,n);
						cnf = uniarrys(cnf,atMostOne(list));}//function to unite two arrays.
				}
			}
		}
		return cnf;
	}
	
	// task 17
	public static int[][] encode(boolean[][] flights) {
		int n= flights.length;
		int numOfClasuses= oneCityInEachStep(n).length +eachCityIsVisitedOnce(n).length + fixSourceCity(n).length + noIllegalSteps(flights).length;
		int [][] cnf =new int [numOfClasuses] [];
		cnf = uniarrys(oneCityInEachStep(n),eachCityIsVisitedOnce(n)) ;//function to unite two cnf.	
		cnf = uniarrys(cnf,fixSourceCity(n));
		cnf = uniarrys (cnf,noIllegalSteps(flights));
		
		return cnf;
		
		

		
	}
	
	// task 18
	public static int[] decode(boolean[] assignment, int n) {
		if (assignment == null) {
			throw new IllegalArgumentException("assignment cant be null");
		}
		if (assignment.length != ((n*n) +1 )){
			throw new IllegalArgumentException("assignment length must be ((n*n) +1)");
		}
		int [] tour = new int [n];
		
		for (int i=0; i<n; i= i+1) {
			for (int j=0; j<n; j= j+1) {
				if (assignment[map(i,j,n)] == true) {
					tour[i]=j;
					
					
				}
			}
		}
		return tour;
		
	}
	
	// task19
	public static int[] solve(boolean[][] flights) {
		int n= flights.length;
		int [] tour = null;
		int nVars = n*n;
		SATSolver.init(nVars);
		int [][] cnf = encode(flights);
		SATSolver.addClauses(cnf);
		boolean[] assignment = SATSolver.getSolution() ;
		
		if (assignment == null) {
			throw new IllegalArgumentException("TIMEOUT");}
		
		if (assignment.length == nVars+1) {
			 tour  = decode(assignment,n);
			if (isSolution(flights,tour) == true) {
				return tour;}
			else {
				throw new IllegalArgumentException("illgel soultion");}
			}
			
		else {
			return tour;
			}
				
		

	}

		
	
	// task20
	public static boolean solve2(boolean[][] flights) {
		throw new UnsupportedOperationException("Not Implemented yet.");		
	}
	
	
}
