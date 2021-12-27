// Did this code successfully run on Leetcode : Yes
// Any problem you faced while coding this : No

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        // Using BFS approach
        // Time Complexity : O(n)
        // Space Complexity : O(n), though the space is O(n/2) because the number of elements in the queue will be around n/2
        public List<List <Integer>> levelOrder(TreeNode root) {
            if(root == null) {
                return new ArrayList <>(new ArrayList <>());
            }

            List<List<Integer>> result = new ArrayList <>();
            Queue<TreeNode> queue = new LinkedList <>();
            queue.add(root);

            while(!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> levelList = new ArrayList <>();
                for(int i =0; i< size; i++) { // iterating over the queue elements in the same level
                    root = queue.poll(); // polling from queue and adding into the local list with same level elements
                    if(root !=null) {
                        queue.add(root.left);
                        queue.add(root.right);
                        levelList.add(root.val);
                    }
                }
                if(!levelList.isEmpty()){ // only adding the list if its not empty
                    result.add(levelList);
                }
            }
            return result;
        }

        // Using DFS approach
        // Time Complexity : O(n)
        // Space - O(logn)
        public List<List <Integer>> levelOrderUsingDFS(TreeNode root) {
            if(root == null) {
                return new ArrayList <>(new ArrayList <>());
            }

            List<List<Integer>> result = new ArrayList <>();
            levelOrderUsingDFS(root, 0, result);
            return result;
        }

        public List<List <Integer>> levelOrderUsingDFS(TreeNode root, int level, List<List <Integer>> result) {
            if(root == null) {
                return result;
            }
            // if the level is equal to the result size, that means its a new level and we need to create a new list into the result
            // because the level is starting from index 0, and the size will be just equal to the level, when new level is encountered
            if(level >= result.size()) {
                result.add(new ArrayList <>());
            }

            result.get(level).add(root.val); // adding the value in the list with index level
            levelOrderUsingDFS(root.left, level+1, result); // call right and left for the DFS
            levelOrderUsingDFS(root.right, level+1, result);
            // level variable should be local because after
            // calling on the left subtree, we need the same value for the right subtree, if the value is made global
            // we will loose the original value for the right subtree because it's at the same level with left
            return result;
        }
    }
}
