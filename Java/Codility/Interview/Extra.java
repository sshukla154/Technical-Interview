// PROBLEM 1

/*
You are playing a game with N cards. On each side of each card a positive integer is written. 
The score of the game is smallest positive integer that doesn't appear on the cards' front 
faces. You may decide which cards you want to flip over. Having flipped them, you then read 
the numbers on the front faces of all the cards. What is the minimum game score you can achieve? 
Write a function: class Solution { public int solution(int[] A, int[] B); } that, given two 
arrays of integers A and B, both of length N, describing the numbers written on the fronts and
 backs of all the cards, returns the minimum possible game score. For example, 
 given A = [1, 2, 4, 3] and B = [1, 3, 2, 3], your function should return 2, as we could 
 flip second card such that the front-facing numbers were [1, 3, 4, 3] and the smallest 
 positive integer excluded from this sequence is 2. Given A = [3, 2, 1, 6, 5] and 
 B = [4, 2, 1, 3, 3], your function should return 3, as we could flip first card such that 
 the front-facing numbers were [4, 2, 1, 6, 5]. Given A = [1, 2] and B = [1, 2] your function 
 should return 3, as no matter how we flip the cards the front-facing numbers will be [1, 2]. 
 Assume that: N is an integer within the range [1..100,000]; each element of arrays A, B is 
 an integer within the range [1..100,000]; input arrays are of equal size. Complexity: 
 expected worst-case time complexity is O(N); expected worst-case space complexity is O(N) 
 (not counting the storage required for input arguments).
*/


class Problem1{



    public static int solution(int[] A, int[] B) {

        int N = A.length;

        /*
         * if two arrays are equal,
         * */
        if (Arrays.equals(A, B)) {
            return findMissing(A);
        }

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {

            int store = A[i];
            A[i] = B[i];
            int value = findMissing(A);

            A[i] = store;

            min = value < min ? value : min;
        }

        return min;
    }


    /* Find the smallest positive missing
       number in an array that contains
       both positive and negative integers */
    public static int findMissing(int A[]) {

        int size = A.length;

        // First separate positive and
        // negative numbers
        int shift = segregate(A, size);
        int arr2[] = new int[size - shift];

        int j = 0;

        for (int i = shift; i < size; i++) {
            arr2[j] = A[i];
            j++;
        }
        
        // Shift the array and call
        // findMissingPositive for
        // positive part
        return findMissingPositive(arr2, j);
    }


    /* Utility function that puts all non-positive
       (0 and negative) numbers on left side of
       arr[] and return count of such numbers */
    public static int segregate(int arr[], int size) {
        int j = 0, i;
        for (i = 0; i < size; i++) {
            if (arr[i] <= 0) {
                int temp;
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                // increment count of non-positive
                // integers
                j++;
            }
        }

        return j;
    }

    /* Find the smallest positive missing
       number in an array that contains
       all positive integers */
    public static int findMissingPositive(int arr[], int size) {
        int i;

        // Mark arr[i] as visited by making
        // arr[arr[i] - 1] negative. Note that
        // 1 is subtracted because index start
        // from 0 and positive numbers start from 1
        for (i = 0; i < size; i++) {
            if (Math.abs(arr[i]) - 1 < size && arr[Math.abs(arr[i]) - 1] > 0)
                arr[Math.abs(arr[i]) - 1] = -arr[Math.abs(arr[i]) - 1];
        }

        // Return the first index value at which
        // is positive
        for (i = 0; i < size; i++)
            if (arr[i] > 0)
                return i + 1;  // 1 is added becuase indexes
        // start from 0

        return size + 1;
    }
}


// PROBLEM 2
/*
A network consisting of M cities and M − 1 roads connecting them is given. Cities are labeled 
with distinct integers within the range [0..(M − 1)]. Roads connect cities in such a way that 
each pair of distinct cities is connected either by a direct road or along a path consisting 
of direct roads. There is exactly one way to reach any city from any other city. In other words, 
cities and direct roads form a tree. The number of direct roads that must be traversed is called 
the distance between these two cities. For example, consider the following network consisting of 
ten cities and nine roads: Cities 2 and 4 are connected directly, so the distance between them 
is 1. Cities 4 and 7 are connected by a path consisting of the direct roads 4−0, 0−9 and 9−7; 
hence the distance between them is 3. One of the cities is the capital, and the goal is to count 
the number of cities positioned away from it at each of the distances 1, 2, 3, ..., M − 1. If 
city number 1 is the capital, then the cities positioned at the various distances from the capital 
would be as follows: 9 is at a distance of 1; 0, 3, 7 are at a distance of 2; 8, 4 are at a 
distance of 3; 2, 5, 6 are at a distance of 4. Write a function: 
class Solution { public int[] solution(int[] T); } that, given a non-empty array T consisting 
of M integers describing a network of M cities and M − 1 roads, returns an array consisting of 
M − 1 integers, specifying the number of cities positioned at each distance 1, 2, ..., M − 1. 
Array T describes a network of cities as follows: if T[P] = Q and P = Q, then P is the capital; 
if T[P] = Q and P ≠ Q, then there is a direct road between cities P and Q. For example, given 
the following array T consisting of ten elements: T[0] = 9 T[1] = 1 T[2] = 4 T[3] = 9 
T[4] = 0 T[5] = 4 T[6] = 8 T[7] = 9 T[8] = 0 T[9] = 1 the function should return 
[1, 3, 2, 3, 0, 0, 0, 0, 0], as explained above. Assume that: M is an integer within the range 
[1..100,000]; each element of array T is an integer within the range [0..M−1]; there is exactly 
one (possibly indirect) connection between any two distinct cities. Complexity: 

expected worst-case time complexity is O(M); expected worst-case space complexity is O(M) 
(not counting the storage required for input arguments).

*/


// PROBLEM 3
/*
Every week or so, John travels to another city to meet his business partners. He always uses the 
same highway, and since the trip is quite long, he stops at some rest areas to eat and refuel. 
He has a fixed habit of stopping exactly three times: the first time to eat some light salad, 
the second time to eat a pizza, and the third time to eat some nice cake. Different rest areas 
offer different kinds of food, and John knows the exact locations of his favorite places: There 
are N rest stops offering salads; the K-th stop is located at A[K] kilometers from the beginning 
of the highway. There are N stops offering pizzas; the K-th stop is located at B[K] kilometers. 
Finally, there are N stops offering cakes; the K-th stop is located at C[K] kilometers. Apart 
from his fixed habit, John likes to change small details, so he never visits the same three 
places more than once. He wonders how many journeys he must make before he is forced to repeat 
a triplet of rest stops that he has used previously. Write a function: 


class Solution { public int solution(int[] A, int[] B, int[] C); } that, given three non-empty 
arrays A, B, C consisting of N integers each, denoting the locations of rest stops offering 
different types of food, returns the number of ways John can pick three stops in different 
locations (two locations are different if they have different distance from the beginning of 
the highway), such that the first stop offers salad, the second one offers pizza, and the third 
one offers cake. If the calculated result is greater than 1,000,000,000 the function should 
return −1. Note that the rest stops can be given in any order and there could be many rest stops 
of the same kind at the same kilometer. For example, given N = 2, A = [29, 50], B = [61, 37], 
C = [37, 70], the function should return 3, since there are three ways to stop: (29, 37, 70), 
or (29, 61, 70), or (50, 61, 70). The first triplet means that John can stop for salad at the 
29th kilometer of his travel, for pizza at the 37th kilometer, and for cake at the 70th kilometer. 
Given N = 2, A = [29, 29], B = [61, 61], C = [70, 70], the function should return 8, since there 
are two ways of choosing each of the stops, making it 2*2*2 = 8. Given N = 1, A = [5], B = [5], 
C = [5], the function should return 0. Assume that: N is an integer within the range [1..40,000]; 
each element of arrays A, B, C is an integer within the range [1..1,000,000,000]. 

Complexity: expected worst-case time complexity is O(N*log(N)); expected worst-case space 
complexity is O(N) (not counting the storage required for input arguments).
*/
class Problem3 {




    public static int solution1(int[] A, int[] B, int[] C) {

        int N = A.length;
        int count = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < N; i++) {

            stack.push(A[i]);

            for (int j = 0; j < N; j++) {

                if (B[j] > stack.peek()) {
                    stack.push(B[j]);
                    System.out.println(Arrays.toString(stack.toArray()));
                }

                for (int k = 0; k < N; k++) {

                    if (C[k] > stack.peek()) {
                        stack.push(C[k]);
                        System.out.println(Arrays.toString(stack.toArray()));
                    }
                }
            }

            if (stack.size() == 3) {

                count++;
//                System.out.println(Arrays.toString(stack.toArray()));
            }
            stack.clear();


        }

        return count;
    }
}








public  class Extra {
	

    // ZooPlus AG test
    /*question: Given an array of values, design and code 
    an algorithm that returns whether there are two duplicates 
    within k indices of each other? k indices and within plus 
    or minus l (value) of each other? Do all, even the latter, 
    in O(n) running time and O(k) space.*/
    /*END OF SOLUTION*/



    /*  Running solution...
    Compilation successful.

    Example test:    '00-44  48 5555 8361' 
    WRONG ANSWER  (got 004-448-555-583-6- 8-361 expected 004-448-555-583-61) 

    Example test:    '0 - 22 1985--324' 
    WRONG ANSWER  (got 022-198-532-5---324 expected 022-198-53-24) 

    Example test:    '555372654' */

    /*solution-a*/
    public static String formatPhoneNumber(String S) {
        
        if(S == null || S.isEmpty() || S.length() < 2){
            return null;
        }
        
        String temp = "";
        
        for(int j = 0; j < S.length(); j++){
        
            char value = S.charAt(j);
        
            if(Character.isDigit(value) 
                && Character.getNumericValue(value) >= 0 
                && Character.getNumericValue(value) <= 200){
                
                temp += String.valueOf(value);
            }    
        }
        
        if(temp.length() == 2){
            return temp;
        }            
        
        String result = "";
        int len = temp.length();

        boolean bol = false; 

        if(len%3 == 0)
            bol = true;
        
        for(int index = 0; index < len; index++){
        
            int rest = len - (index+1);

            if(rest % 2 == 0 && rest %3 != 0 && rest/3 == 1 && !bol){

                System.out.println(rest);

                result += String.valueOf(temp.charAt(index)); 
                        
                String restString = temp.substring(index+1); 

                result +=  "-"+restString.substring(0, 2)+ "-" + restString.substring(2);
                break;
            }            
            
            if( (index+1) % 3 == 1 && index != 0) {
                result += "-";
            }        
           
           result += String.valueOf(temp.charAt(index));            
        
        }        

        return result;
    }
    /*END of solution-a*/


    /*solution-b*/
    public static String formatPhoneNumber(String s) {

        if (s == null) {
            return null;
        }

        return s.replaceAll("\\D", "")                // Discard all non-digits.
                .replaceAll("(\\d{2})(?=\\d{2}$)" +   // Final group of 4 digits
                            "|" +                     // ... or ...
                            "(\\d{3})(?!$)",          // non-final group of 3 digits,
                            "$1$2-");                 // insert separator.
    }
    /*END of solution-b*/






    /*solution-c*/
    // "Hello123 erere3435 efere 45 world.".replaceAll("[^\\d+]", "")
    public static String formatPhoneNumber3(String input) {

        // Guard
        if (input == null) {
            return input;
        }

        // Strip junk
        StringBuilder phone = new StringBuilder();

        IntStream.range(0, input.length())
                .filter(i -> Character.isDigit(input.charAt(i)))
                .forEachOrdered(i -> phone.append(Character.getNumericValue(input.charAt(i))));

        if (phone.length() <= 3) {
            return phone.toString();
        }

        // insert dashes... in reverse.
        // special cases for last group
        // set up the dash insert point for the end-of-groups.
        int dash = (phone.length() / 3) * 3;
        
        switch (phone.length() % 3) {

            case 0:
                // nothing to do for an exact grouping.
                break;
            case 1:
                // insert the dash making  2-2 groups instead of 3-1
                phone.insert(dash - 1, '-');
                break;
            case 2:
                // end up with a 3-2 group.
                phone.insert(dash, '-');
                break;
        }

        // easy cases for first groups.
        while (dash > 3) {
            dash -= 3;
            phone.insert(dash, '-');
        }

        return phone.toString();
    }
    /*END of solution-c*/







    /* eDreams ODIGEO */
    /*================*/

    /*A positive integer N is given. The goal is to construct the shortest possible 
    sequence of integers ending with N, using the following rules:

    the first element of the sequence is 1; more specifically: A[0] = 1,
    each of the following elements is generated by multiplying the previous element by 2 or 
    increasing it by 1; more precisely: A = A[i−1] * 2 or A = A[i−1] + 1, for i ≥ 1.

    For example, for N = 17, the shortest sequence is:

      A[0] = 1
      A[1] = 2
      A[2] = 4
      A[3] = 8
      A[4] = 16
      A[5] = 17

    Write a function:
    class Solution { public int solution(int N); }
    that, given a positive integer N, returns the length of the shortest possible 
    sequence of integers satisfying the above conditions and ending with N.
    For example, given N = 17, the function should return 6, as explained above.

    Assume that:.
    N is an integer within the range [1..2,147,483,647].
    Complexity:
    expected worst-case time complexity is O(log(N));
    expected worst-case space complexity is O(1).. 1po*/


    public static int shortestSequence(int n){

        List<Integer> list = new ArrayList<>();
        list.add(n);

        boolean flag = true; 

        while(flag){

            int temp = n%2;

            if (n == 1){
                break;
            }                

            if (temp == 0){
                n = n/2;
                list.add(0, n);
            }

            else if(temp == 1){
                n = n-1;
                list.add(0, n);
            }
        }

        // System.out.println(list.toString());
        return list.size();
    }
    /*END of solution*/



    /*An integer X and a non-empty zero-indexed array A consisting of N integers are given. 
    We are interested in which elements of A are equal to X and which are different from X. 
    The goal is to split array A into two parts, such that the number of elements equal to X 
    in the first part is the same as the number of elements different from X in the other part. 
    More formally, we are looking for an index K such that:

    0 ≤ K < N and
    the number of elements equal to X in A[0..K−1] is the same as the number of elements different 
    from X in A[K..N−1]. (For K = 0, A[0..K−1] does not contain any elements. For K = N, A[K..N-1] 
    does not contain any elements.)
    
    For example, given integer X = 5 and array A such that:

    A = [5, 5, 1, 7, 2, 3, 5]

    K equals 4, because:

    two of the elements of A[0..3] are equal to X, namely A[0] = A[1] = X, and
    two of the elements of A[4..6] are different from X, namely A[4] and A[5].
    Write a function:

    int solution(int X, int A[], int N);

    that, given an integer X and a non-empty zero-indexed array A consisting of N integers, 
    returns the value of index K satisfying the above conditions. It can be shown such index 
    K always exists and it is unique.

    For example, given integer X and array A as above, the function should return 4, as explained 
    above.

    Assume that:

    N is an integer within the range [1..100,000];
    X is an integer within the range [0..100,000];
    Each element of array A is an integer within the range [0..100,000].
    Complexity:

    Expected worst-case time complexity is O(N);
    Expected worst-case space complexity is O(1), beyond input storage (not counting the storage 
    required for input arguments).

    Elements of input arrays can be modified.
    Except of one edge case the solution is as simple as counting for a number of X within the A 
    and then calculating K as that number substracted from an array length... Just think 
    of it a bit:

    e.g. assume there're 4 occurences of X within a given A;
    divide an array by cutting off 4 element from the right;
    whatever remained is gonna be on the left;
    even if after such a cut some of X's was 'lost' on the right - not a big deal; logically 
    enough that after division every X found on the right side is gracefully leveled by a 
    non-X on the left side. [3,5,1,5,7,8,3,2,8] -> [3,5,1,5,7,8,3 | 2,8] Two of X on the left = two 
    of non-X on the right

    [3,5,1,5,7,8,5,5,8] -> [3,5,1,5,7 | 8,5,5,8] Are two of X 'trapped' on the right side? Not 
    problem at all! That only means that we have got for two less X on the left and for two less 
    non-X on the right, thus anyway 2 of X on the left = 2 of non-X on the right;

    As for an exception mentioned earlier, the described solution will fail when all the X values 
    are grouped in one rightmost sequence, e.g. [3,5,5,5,5] -> [3 | 5,5,5,5] is wrong because for 
    X=5 by cutting 4 elements from the right eventually we will get an index of 1 thus leaving 
    no elements equal to X on the left at all. But as you may remember according to the condition 
    it's the first (i.e. left) part of an array that must contain X in quantity equal to a quantity 
    of non-X's in the second part (i.e. right). And as for this very edge case we're gonna return 
    just length of an input array which is a sort of an acceptable answer due to this: 
    "for K = N, A[K..N-1] does not contain any elements".*/


    public int splitArray(int X, int[] A) {
        
        if(A == null || A.length == 0){
            return -1;
        }
        
        if(X < 0 || X > 100000) return -1;
        
        int N = A.length;
        
        if(N < 1 || N > 100000) return -1;

        int sum = 0, seg  = 0;

        for (int j = 0; j < N; j++){
            
            if(A[j]< 0 || A[j] > 100000)
                return -1;

            if(A[j] == X){
                sum++;
                seg++;
            }

            else
                seg = 0;
        }

        return (A[N-1]!=X || sum > seg) ? (N - sum) : N;
    }
    /*END of solution:*/
    /*eDreams ODIGEO*/
    /*================*/






    // ==============================
    // Hasso Plattner Institute (HPI)
    // ==============================



    /*
    Inclusion–exclusion principle
    -----------------------------

    Venn diagram showing the union of sets A and B as everything not in white
    In combinatorics (combinatorial mathematics), the inclusion–exclusion principle is a counting technique which generalizes the familiar method of obtaining the number of elements in the union of two finite sets; symbolically expressed as

    |A\cup B|=|A|+|B|-|A\cap B|,} |A\cup B|=|A|+|B|-|A\cap B|,
    where A and B are two finite sets and |S| indicates the cardinality of a set S 
    (which may be considered as the number of elements of the set, if the set is finite). 
    The formula expresses the fact that the sum of the sizes of the two sets may be too 
    large since some elements may be counted twice. The double-counted elements are those 
    in the intersection of the two sets and the count is corrected by subtracting the size 
    of the intersection.

    The principle is more clearly seen in the case of three sets, which for the sets A, B and C is given by

    |A\cup B\cup C|=|A|+|B|+|C|-|A\cap B|-|A\cap C|-|B\cap C|+|A\cap B\cap C|.} |A\cup B\cup 
    C|=|A|+|B|+|C|-|A\cap B|-|A\cap C|-|B\cap C|+|A\cap B\cap C|.
    */


    /*
        K caterpillars are eating their way through N leaves, each caterpillar falls from 
        leaf to leaf in a unique sequence, all caterpillars start at a twig at position 0 
        and falls onto the leaves at position between 1 and N. Each caterpillar j has as 
        associated jump number Aj. A caterpillar with jump number j eats leaves at positions 
        that are multiple of j. It will proceed in the order j, 2j, 3j…. till it reaches the 
        end of the leaves and it stops and build its cocoon. Given a set A of K elements 
        K<-15, N<=10^9, we need to determine the number of uneaten leaves.

        Input:

        N = No of uneaten leaves
        K = No. of caterpillars
        A = Array of integer jump numbers
        Output:

        The integer nu. Of uneaten leaves

        Sample Input:

        10
        3
        2
        4
        5
        Output:

        4
        Explanation:

        [2, 4, 5] is a j member jump numbers, all leaves which are multiple of 2, 4, and 5 are 
        eaten, leaves 1,3,7,9 are left, and thus the no. 4
    */



    // problem - l
    // -----------
    public static int countUneatenLeaves(int N, int[] A) {

        int total = 0;

        for (int i = 0; i < A.length; i++) {

            int multiplier = (int) Math.pow(-1, i);
            total += multiplier * combination(A, i + 1, N);
        }

        return N - total;
    }

    private static int calc(int[] combination, int[] elements, int num) {

        int eaten = 0;

        if (combination.length == 1) {
            eaten = (int) Math.floor(num / elements[combination[0]]);
        } 

        else {

            int lcm = lcm(elements[combination[0]], elements[combination[1]]);
            for (int i = 2; i < combination.length; i++) {
                lcm = lcm(lcm, elements[combination[i]]);
            }
            eaten = Math.abs((int) Math.floor(num / lcm));
        }
        return eaten;
    }

    private static int lcm(int a, int b) {
        return a * (b / findGCD(a, b));
    }

    private static int findGCD(int number1, int number2) {
        //base case
        if (number2 == 0) {
            return number1;
        }

        return findGCD(number2, number1 % number2);
    }

    public static int combination(int[] elements, int K, int num) {

        // get the length of the array
        // e.g. for {'A','B','C','D'} => N = 4 
        int N = elements.length;

        // get the combination by index 
        // e.g. 01 --> AB , 23 --> CD
        int combination[] = new int[K];

        // position of current index
        //  if (r = 1)              r*
        //  index ==>       0   |   1   |   2
        //  element ==>     A   |   B   |   C

        int r = 0;
        int index = 0;
        int total = 0;

        while (r >= 0) {

            // possible indexes for 1st position "r=0" are "0,1,2" --> "A,B,C"
            // possible indexes for 2nd position "r=1" are "1,2,3" --> "B,C,D"

            // for r = 0 ==> index < (4+ (0 - 2)) = 2
            if (index <= (N + (r - K))) {
                combination[r] = index;

                // if we are at the last position print and increase the index
                if (r == K - 1) {

                    //do something with the combination e.g. add to list or print
                    total += calc(combination, elements, num);
                    index++;
                } else {
                    // select index for next position
                    index = combination[r] + 1;
                    r++;
                }
            } else {
                r--;
                if (r > 0) index = combination[r] + 1;
                else index = combination[0] + 1;
            }
        }
        return total;
    }
    /* END ofsolution - l*/
    


    /*
    solution-b
    */

    private static int countUneatenLeaves(int n, int[] a)
    {

        int factors = 0;
        Map<Integer, List<Integer>> map = new HashMap<>();
        findFactorialsOfAllCombinations(0, a, 0, new int[a.length], map, n);
        for (int key : map.keySet()) {
            if ((key & 1) != 1) {
                List<Integer> list = map.get(key);
                for (int i : list) {
                    factors -= i;
                }
            } else {
                List<Integer> list = map.get(key);
                for (int i : list) {
                    factors += i;
                }
            }
        }

        return n - factors;
    }


    private static void findFactorialsOfAllCombinations(int start, int[] array, int K, int[] result, Map<Integer, List<Integer>> map, int N)
    {
        if (K > 0) {
            if (!map.containsKey(K)) {
                map.put(K, new ArrayList<Integer>());
            }
            List<Integer> list = map.get(K);
            int[] temp = new int[K];
            System.arraycopy(result, 0, temp, 0, K);
            int factors = (int) Math.floor(N / lcm(temp));
            list.add(factors);
        }
        for (int i = start; i < array.length; i++) {
            result[K] = array[i];
            findFactorialsOfAllCombinations(i + 1, array, K + 1, result, map, N);
        }
    }


    private static int lcm(int[] input)
    {
        int result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    private static int countUneatenLeavesBruteForce(int N, int[] A)
    {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            boolean found = false;
            for (int j : A) {
                if (i >= j && i % j == 0) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                count++;
            }
        }
        return count;
    }

    private static int lcm(int a, int b)
    {
        return a * (b / gcd(a, b));
    }

    private static int gcd(int a, int b)
    {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }



    /*
    solution-c
    */

    public static int findUneatenLeaves(int[] array, int n) {
    
        ArrayList<Integer> uneatenLeaves = new ArrayList<Integer>();
        ArrayList<Integer> eatenLeaves = new ArrayList<Integer>();
    
        for (int i = 1; i <= n; i++) {
            uneatenLeaves.add(i);
        }
    
        // 1. find the multiple of the eatenLeaves
        for (int i = 0; i < array.length; i++) {
            eatenLeaves.add(array[i]);
            for (int j = 1; j < uneatenLeaves.size(); j++) {
                if (array[i] * uneatenLeaves.get(j) <= n) {
                    eatenLeaves.add(array[i] * uneatenLeaves.get(j));
                }
            }
        }

        for (int i = 0; i < eatenLeaves.size(); i++) {
            for (int j = 1; j < uneatenLeaves.size(); j++) {
                if (eatenLeaves.get(i) == uneatenLeaves.get(j)) {
                    uneatenLeaves.remove(uneatenLeaves.get(j));
                }
            }
        }

        System.out.println(uneatenLeaves.size());
        return uneatenLeaves.size();
    }


    /*
    solution-d
    */

    static int countUneatenLeaves(int N, int[] A) {
        if(N<= 0)
            return 0;
        if(A == null || A.length == 0)
            return 0;

        int length = A.length;
        Set<Integer > eaten = new HashSet<Integer>();
        Arrays.sort(A);
        for(int i = 0 ; i < length; i++){
            if(A[i] == 1)
                return 0;
            else if(eaten.contains(A[i]))
                continue;
            else
                eatingLeaves(eaten,A[i],N);

        }
        return (N - eaten.size());
    }

    static void  eatingLeaves(Set<Integer> eaten , int catter , int numberOfLeaves){
        if(catter <= 0)
            return;

        int nextLeave = catter;
        while(nextLeave <=  numberOfLeaves){
            if(!eaten.contains(nextLeave))
                eaten.add(nextLeave);
            nextLeave += catter;
        }
    }
    /*
    */








    

    // problem - ll
    // ------------

    static String mergeStrings(String a, String b) {

        boolean eql =  a.length() ==  b.length();
        boolean less =  a.length() < b.length();
        // boolean greater =  a.length() > b.length();

        int min = less ? a.length(): b.length();

        String rst = "";
        
        for (int i = 0; i < min; i++){            
            
            rst +=  "" + a.charAt(i) + b.charAt(i);
            // rst +=   new StringBuilder().append(a.charAt(i)).append(b.charAt(i)).toString();
            // rst += String.valueOf(a.charAt(i)) + String.valueOf(b.charAt(i));
        } 

        if(eql){
            return rst;
        }

        else if(less){
            return rst += b.substring(min);
        }

        return rst += a.substring(min);
    }
    /* END ofsolution -  ll*/
     




    // problem - lll
    // -------------

    /*
        Victor 
        Veronica 
        Ryan 
        Dave 
        Maria 
        Maria 
        Farah 
        Farah
        Ryan 
        Veronica 

        ================
        answer: Veronica
        ================ 
    */

    // String[] votes = { "Victor", "Veronica", "Ryan", "Dave", "Maria",
    //                      "Maria", "Farah", "Farah","Ryan",  "Veronica"};

    public static String electionWinner(String[] votes) {

        // TreeMap will put the values in the ascending order 
        Map<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        List<String> keys = new ArrayList<String>();

        int max =  Collections.max(map.values());

        for(Map.Entry<String, Integer> entry : map.entrySet()){

            if(entry.getValue() == max){
                keys.add(entry.getKey());
            }
        }

        // the list is already sorded in the ascending order due 
        // to the use of TreeMap
        
        String rst = keys.get(keys.size() - 1);
        return rst;
    }


    public static String electionWinner1(String[] votes) {

        // TreeMap will put the values in the ascending order 
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();

        for (String vote  : votes) {
            
            String key =  vote;
            Integer value = map.containsKey(key) ? map.get(key) +1 : 1;

            map.put(key, value);
        }

        int max =  Collections.max(map.values());

        Iterator it = map.entrySet().iterator();

        while(it.hasNext()) {

            Map.Entry entry = (Map.Entry)it.next();
            
            if(!(entry.getValue()).equals(max)){
                it.remove();
            }
        }

        // System.out.println(map.lastEntry());
        
        return map.lastKey();
    }

    public static void printMap(Map<String, Integer> map) {

        for (Map.Entry<String, Integer> entry : map.entrySet()){
            System.out.println("Key : " + entry.getKey() + " Value : "+ entry.getValue());
        }
    }
    /* END ofsolution -III*/

    
    public static void mySetTest(){

        Set<Integer> set = new TreeSet<Integer>();

        set.add(3);
        set.add((int)3.0);
        set.add(2);
        set.add(2);

        set.add(new Integer(2));
        set.add(Integer.parseInt("2"));

        System.out.println(set);
        // [2, 3]
    }    
    /*END of solution*/


	public static void main(String[] args) {
		System.out.println();
	}
}