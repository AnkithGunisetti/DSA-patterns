# DSA-patterns

1. Two Pointers 
2. Island (Matrix Traversal) 
3. Fast & Slow Pointers
4. Sliding Window
5. Merge Intervals
6. Cyclic Sort
7. In-place Reversal of a Linked List 
8. Tree Breadth First Search 
9. Tree Depth First Search 
10. Two Heaps
11. Subsets
12. Modified Binary Search 
13. Top K Elements
14. Bitwise XOR
15. Backtracking 
16. 0/1 Knapsack (Dynamic Programming) 
17. Topological Sort (Graph) 
18. K-way Merge
19. Monotonic Stack
20. Multi-threaded


Sure! Let's go through each algorithmic pattern with explanations and examples in Java.

### 1. Two Pointers

**Concept:** Use two pointers to iterate efficiently through data, often from both ends.

**Example:** Find pairs in an array that sum to a target.

```java
import java.util.Arrays;

public class TwoPointers {
    public static int[] twoSum(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{nums[left], nums[right]};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
```

### 2. Island (Matrix Traversal)

**Concept:** Traverse a matrix to find connected components, such as islands in a grid.

**Example:** Count the number of islands.

```java
public class IslandTraversal {
    public static int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    dfs(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '#';
        dfs(grid, i + 1, j);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i, j - 1);
    }
}
```

### 3. Fast & Slow Pointers

**Concept:** Use two pointers moving at different speeds to detect cycles.

**Example:** Detect a cycle in a linked list.

```java
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class FastSlowPointers {
    public static boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            if (slow == fast) return true;
            slow = slow.next;
            fast = fast.next.next;
        }
        return false;
    }
}
```

### 4. Sliding Window

**Concept:** Use a window that slides over data to find optimal solutions.

**Example:** Find the maximum sum of a subarray of size k.

```java
public class SlidingWindow {
    public static int maxSumSubarray(int[] arr, int k) {
        int maxSum = 0, windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }
}
```

### 5. Merge Intervals

**Concept:** Merge overlapping intervals.

**Example:** Merge a list of intervals.

```java
import java.util.*;

public class MergeIntervals {
    public static int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> result = new ArrayList<>();
        int[] currentInterval = intervals[0];
        result.add(currentInterval);
        for (int[] interval : intervals) {
            int currentEnd = currentInterval[1];
            int nextBegin = interval[0];
            int nextEnd = interval[1];
            if (currentEnd >= nextBegin) {
                currentInterval[1] = Math.max(currentEnd, nextEnd);
            } else {
                currentInterval = interval;
                result.add(currentInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
```

### 6. Cyclic Sort

**Concept:** Sort elements when numbers are in a known range (1 to n).

**Example:** Sort an array with numbers from 1 to n.

```java
public class CyclicSort {
    public static void cyclicSort(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correctIndex = nums[i] - 1;
            if (nums[i] != nums[correctIndex]) {
                swap(nums, i, correctIndex);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

### 7. In-place Reversal of a Linked List

**Concept:** Reverse a linked list in place.

**Example:** Reverse a singly linked list.

```java
public class ReverseLinkedList {
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null, current = head;
        while (current != null) {
            ListNode nextTemp = current.next;
            current.next = prev;
            prev = current;
            current = nextTemp;
        }
        return prev;
    }
}
```

### 8. Tree Breadth First Search

**Concept:** Traverse a tree level by level.

**Example:** Print level order traversal of a tree.

```java
import java.util.*;

class TreeNode {
    int val;
    TreeNode left, right;
    TreeNode(int x) { val = x; }
}

public class BFS {
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);
                if (currentNode.left != null) queue.add(currentNode.left);
                if (currentNode.right != null) queue.add(currentNode.right);
            }
            result.add(currentLevel);
        }
        return result;
    }
}
```

### 9. Tree Depth First Search

**Concept:** Traverse a tree depth-wise using recursion.

**Example:** Inorder traversal of a binary tree.

```java
public class DFS {
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderHelper(root, result);
        return result;
    }

    private static void inorderHelper(TreeNode node, List<Integer> result) {
        if (node == null) return;
        inorderHelper(node.left, result);
        result.add(node.val);
        inorderHelper(node.right, result);
    }
}
```

### 10. Two Heaps

**Concept:** Use two heaps to solve problems involving median or balance.

**Example:** Find the median of a data stream.

```java
import java.util.*;

public class MedianFinder {
    private PriorityQueue<Integer> minHeap;
    private PriorityQueue<Integer> maxHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty() || maxHeap.peek() >= num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        if (maxHeap.size() > minHeap.size() + 1) {
            minHeap.add(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.add(minHeap.poll());
        }
    }

    public double findMedian() {
        if (maxHeap.size() == minHeap.size()) {
            return (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
        return maxHeap.peek();
    }
}
```

### 11. Subsets

**Concept:** Generate all possible subsets of a set.

**Example:** Find all subsets of a set.

```java
import java.util.*;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        result.add(new ArrayList<>());
        for (int num : nums) {
            int size = result.size();
            for (int i = 0; i < size; i++) {
                List<Integer> subset = new ArrayList<>(result.get(i));
                subset.add(num);
                result.add(subset);
            }
        }
        return result;
    }
}
```


