## Common Error

### Sliding Window
1. Don't forget the last interval(right is n) [fruit into baskets](https://leetcode.com/problems/fruit-into-baskets/)

### Fenwich Tree
1. FenwichTree's boundary is [1 to n]. During the query process, limit should be (i > 0) instead of (i >= 0)

### Segment Tree
1. The lo and hi boundary is with the original array, so the mid should be calculate using lo and hi instead of i and j

### Compare Integer
using .equals instead of ==
JVM is cacheing Integer, == is used in -128->127

## Common
Arrays.asList() and Arrays.sort() both use Integer[] instead of int[]

public static int binarySearch(Object[] a, Object key)  
index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). 
The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. 

## Big O
http://souravsengupta.com/cds2016/lectures/Complexity_Cheatsheet.pdf


##

-2147483648 ~ 2147483647

### Graph Representation
```
# Unweighted
List<Integer>[] adj = new ArrayList<>();

# Weighted
Map<Integer, List<int[]>> adj = new HashMap<>();
```

Not operation
```
System.out.println(-2); // -2
System.out.println(~2); // -3
System.out.println(-3); // -3
System.out.println(~3); // -4
```

Bit 
n &= (n - 1) // remove the least significant 1-bit

numer = 0, denom


StringBuilder append 在后面， insert可以在特定位置


```
String s = "\\";
System.out.println(s.length()); // 1
// System.out.println(s.matches("\\")); // invalid
System.out.println(s.matches("\\\\")); // true

Step one: code to string
Step two: string to regex
```

[DP todo](https://leetcode.com/discuss/general-discussion/1050391/must-do-dynamic-programming-problems-category-wise)


```
# use 
Arrays.sort(points, (a, b)-> Integer.compare(a[0], b[0]));

# not 
// Arrays.sort(points, (a, b)-> a - b);

Error case:
[[-2147483646,-2147483645],[2147483646,2147483647]]
```

Don't use iterator and modify the collection at the same time



Integer.valueOf(b.charAt(0)) 是错误的！

        Arrays.fill(dp, new HashMap<>());太可怕了
        
        
                for (int i = 1; i < n; i++) { 要小心
                
                
                s1.compareTo(s1) 
                //String.compare(s1, s2): false
                
                
        char[] arr = String.valueOf(num).toCharArray();
        return list.toArray(new int[list.size()][2]);


### Number range
32 bit: -2<sup>31</sup> ~ 2<sup>31</sup> - 1 or -2*10<sup>9</sup> ~ 2*10<sup>9</sup>\
64 bit: -2<sup>63</sup> ~ -2<sup>63</sup> - 1 or -9 * 10<sup>18</sup> ~ 9 * 10<sup>18</sup>  
Common error: 
```
int a = 1000000000;
// long b = a * a; => Error!
long b = (long)a * a;
System.out.println(b);

```
1e-9, 1e9

 sb.deleteCharAt()


### Mathmatics
sum(1, 2, 3, ... n ) = n * (n + 1) / 2\
sum(1<sup>2</sup>, 2<sup>2</sup>, ..., n<sup>2</sup>) = n * (n + 1) * (2n + 1) / 6\
arithmetic progression: (a + b) * n / 2, For example(3, 7, 11, 15)\
geometric progression: a + ak + ak<sup>2</sup> + ak<sup>3</sup> + ...+ b = (bk - a) / (k - 1)\
Special case: 1 + 2 + 2<sup>3</sup> + 2<sup>4</sup> + ... + 2<sup>n-1</sup> = 2<sup>n</sup> - 1\
harmonic sum: 1 + 1/2 + 1/3 + ... + 1/n <= 1 + log2(n)

[Permutation](https://github.com/YangLiu0523/Algorithm/blob/master/src/matieral/math/PermutationFormula.java)\
C(k, n) = C(k - 1, n - 1)  + C(k, n - 1)\
A(k, n) = A(k - 1, n - 1) * A(1, n)\
H(k, n) = C(k, n + k - 1)

[Prim](https://github.com/YangLiu0523/Algorithm/blob/master/src/matieral/math/prim/Prim.java)

```
public int gcd(int a, int b) {
    return b > 0 ? gcd(b, a % b) : a;
}

public boolean isTwoPow(int i) {
    return (i & (i - 1)) == 0;
}
```

Remember binary search to validate the guess if no clue how to find the answer [example1](https://leetcode.com/problems/minimum-limit-of-balls-in-a-bag/), [example2](https://leetcode.com/problems/split-array-largest-sum/)

During backtracking, think what's the nature order to make the order [example](https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/)

### Time Complexity
a modern computer can perform some hundreds of millions of operations in a second.\
PriorityQueue: Insertion and removal take O(logn) time, and retrieval takes O(1) time.


1 << n

Make sure compare start value makes sense, especially when not start with the first elements
[example](https://leetcode.com/problems/maximum-subarray/)
```
int maxSum = nums[0], sum = nums[0]; // Error prone
```

Test Map contains don't forget to use containsKey instead of contains
```$xslt
cache.containsKey(target - nums[i])
```

Permutations: N!
Combination: Ckn
Subsets: 2^N

Can't suppose Tree length is lgn, because it might be unbalanced tree

Careful about update point status in BFS\
if (!udpated) ..   [Example](https://leetcode.com/problems/map-of-highest-peak/)

Take care of whether the array is sorted

Arrays.asList 不要加<>

Duplicate, remember to che whether is idx dup or value dup

If dealing with tree, prefer to use parent instead of visited array.\
just need to assert whether it is parent [Example](https://leetcode.com/problems/tree-of-coprimes/)

Distinguish between two pointer and sliding window

Sliding window, mention left boundary subtraction [Example](https://leetcode.com/problems/subarray-sum-equals-k/)

During mod operation, take care of negative element [Example](https://leetcode.com/problems/subarray-sums-divisible-by-k/)
```
sum = (a + sum) % K;
if (sum < 0) sum += K;
```

Array of Integer, think sorted / neg or pos

### Vocabulary
Ancestor
dividend / divisor = quotient ... remainder
numerator / denominator
sentinel
anchor



