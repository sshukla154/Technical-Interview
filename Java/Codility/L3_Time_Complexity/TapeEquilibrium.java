/*
	A non-empty zero-indexed array A consisting of N integers is given. Array A represents numbers on a tape.
	Any integer P, such that 0 < P < N, splits this tape into two non-empty parts: A[0], A[1], ..., A[P − 1] and A[P], A[P + 1], ..., A[N − 1].
	The difference between the two parts is the value of: |(A[0] + A[1] + ... + A[P − 1]) − (A[P] + A[P + 1] + ... + A[N − 1])|
	In other words, it is the absolute difference between the sum of the first part and the sum of the second part.
	For example, consider array A such that:
	  A[0] = 3
	  A[1] = 1
	  A[2] = 2
	  A[3] = 4
	  A[4] = 3
	We can split this tape in four places:
	P = 1, difference = |3 − 10| = 7 
	P = 2, difference = |4 − 9| = 5 
	P = 3, difference = |6 − 7| = 1 
	P = 4, difference = |10 − 3| = 7 
	Write a function:
	class Solution { public int solution(int[] A); }
	that, given a non-empty zero-indexed array A of N integers, returns the minimal difference that can be achieved.
	For example, given:
	  A[0] = 3
	  A[1] = 1
	  A[2] = 2
	  A[3] = 4
	  A[4] = 3
	the function should return 1, as explained above.
	Assume that:
	N is an integer within the range [2..100,000];
	each element of array A is an integer within the range [−1,000..1,000].
	Complexity:
	expected worst-case time complexity is O(N);
	expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
	Elements of input arrays can be modified.
 */

//SCORE: 100/100
package timecomplexity;


/*
	For example, consider array A such that:

	  A[0] = 3
	  A[1] = 1
	  A[2] = 2
	  A[3] = 4
	  A[4] = 3
	------------  
	ALL SUM = 13 


	We can split this tape in four places:

	P = 1, difference = |3 − 10| = 7 
	P = 2, difference = |4 − 9| = 5 
	P = 3, difference = |6 − 7| = 1 
	P = 4, difference = |10 − 3| = 7 

Given a non-empty array A of N integers, returns the minimal difference 
that can be achieved.
*/
public class TapeEquilibrium {
	

	/*
		In other words, it is the absolute difference between 
		the sum of the first part and the sum of the second part.

		Given a non-empty array A of N integers, returns 
		the minimal difference that can be achieved.
	*/


	/*
	  solution -a
	*/
	public static int solution(int[] A) {
	
		int res = Integer.MAX_VALUE;
	
		int tmp=0;
		int sum = 0;
	
		for (int i = 0; i < A.length; i++) {
			sum += A[i];
		}
	
		for (int i = 0; i < A.length-1; i++) {
			tmp+=A[i];
			res = Math.min(res, Math.abs(tmp - (sum - tmp)));
		}
		return res;
	}	





	/*
		solution -b
	*/
	public int solution(int[] A) {
    

        // write your code in Java SE 8
        if (A.length ==1) {
           return A[0];
        }
              
        int sum = 0;
        for (int i=0; i<A.length;i++) {
            sum += A[i];
        }
        
        int least = Math.abs(A[0]-(sum-A[0]));    
        System.out.println(least);
        int leftSum = 0;
        for (int i=1; i<A.length;i++) {
            for (int j = 0; j<i; j++){
               leftSum += A[j];
            }
            
            least = less(least, Math.abs(leftSum - (sum - leftSum)));
            leftSum = 0;
            
        }
    
        return least;
    }
    
    public int less (int i, int j) {
       if (i<=j){
          return i; 
        }else {
          return j;
        }    
    }





    public int solution(int[] A) {
        // write your code in Java SE 8

        if (A.length ==2) {
           return A[1]-A[0];
        }
              
        int sum = 0;
        for (int i=0; i<A.length;i++) {
            sum += A[i];
        }
        
        //P[N]is thje leftSum, P[1] = A[0]; P[2] = A[0] + A[1],P[0] is useless
        int [] P = new int[A.length];      
        for (int i=0; i<P.length;i++) {
            P[i] = 0;            
        }

        for (int i=1; i<A.length;i++) {
            P[i] = A[i-1] + P[i-1];            
        }
        
        int least = Math.abs(P[1]-(sum-P[1]));    
        
        for (int i=1; i<P.length;i++) {
            least = less(least, Math.abs(P[i]-(sum-P[i])));            
        }
    
        return least;
    }
    
    public int less (int i, int j) {
       if (i<=j){
          return i; 
        }else {
          return j;
        }    
    }








    /*
	* solution -c 
    */
    public int solution(int[] a) {
        
        int sum = 0;
        for (final int value : a) {
            sum += value;
        }

        int left = 0;
        int right = sum;
        int result = Integer.MAX_VALUE;
        
        for (int i = 1; i < a.length; i++) {
            left += a[i - 1];
            right -= a[i - 1];
            if (Math.abs(right - left) < result) {
                result = Math.abs(right - left);
            }
        }

        return result;
    }





	public static void main(String[] args) {
		int[] A = new int[]{3,1,2,4,3};
		System.out.println(solution(A));
	}
}