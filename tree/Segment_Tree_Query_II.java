/*

Segment Tree Query II


For an array, we can build a SegmentTree for it, each node stores an extra attribute count to denote the number of elements in the the array which value is between interval start and end. (The array may not fully filled by elements)

Design a query method with three parameters root, start and end, find the number of elements in the in array's interval [start, end] by the given root of value SegmentTree.

 Notice

It is much easier to understand this problem if you finished Segment Tree Buildand Segment Tree Query first.

Example
For array [0, 2, 3], the corresponding value Segment Tree is:

                     [0, 3, count=3]
                     /             \
          [0,1,count=1]             [2,3,count=2]
          /         \               /            \
   [0,0,count=1] [1,1,count=0] [2,2,count=1], [3,3,count=1]
query(1, 1), return 0

query(1, 2), return 1

query(2, 3), return 2

query(0, 2), return 2


解：
由于树已经给出，所以直接对左右两个孩子的count求和就行了。

*/

/**
 * Definition of SegmentTreeNode:
 * public class SegmentTreeNode {
 *     public int start, end, count;
 *     public SegmentTreeNode left, right;
 *     public SegmentTreeNode(int start, int end, int count) {
 *         this.start = start;
 *         this.end = end;
 *         this.count = count;
 *         this.left = this.right = null;
 *     }
 * }
 */
public class Solution {
    /**
     *@param root, start, end: The root of segment tree and 
     *                         an segment / interval
     *@return: The count number in the interval [start, end]
     */
    public int query(SegmentTreeNode root, int start, int end) {
        int mid;
        
        if (root == null || start > end || start > root.end || end < root.start) {
            return 0;
        } else if (start == root.start && end == root.end) {
            return root.count;
        }
        
        if (start < root.start) {
            start = root.start;
        }
        if (end > root.end) {
            end = root.end;
        }
        
        if (start > mid) {
            return query(root.right, start, end);
        } else if (end <= mid) {
            return query(root.left, start, end);
        } else {
            return query(root.left, start, mid) + query(root.right, mid + 1, end);
        }
    }
}
