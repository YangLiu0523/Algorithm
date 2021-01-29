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

