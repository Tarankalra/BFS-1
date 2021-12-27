import java.util.*;

// TC - O(V+E) - because for each vertex, we are iterating through each edge to reduce it's indegrees count
// Space - O(V+E), we used queue taking O(V) space, Hashmap using O(V), and Map have O(V+E)
public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int m = prerequisites.length;
        if(numCourses == 1 || m ==0) {
            return true;
        }
        // Maintaining indegrees array to store the dependency count for a course
        int[] inDegree = new int[numCourses];

        // Storing the independent course(bi) to dependent course(ai - this is a list)
        Map <Integer, List <Integer>> map = new HashMap <>();

        for(int [] pre : prerequisites) {
            if(!map.containsKey(pre[1])) {
                map.put(pre[1], new ArrayList <>());
            }
            map.get(pre[1]).add(pre[0]);
            inDegree[pre[0]]++; // increasing the count of dependents for a course
        }

        Queue <Integer> q = new LinkedList<>();
        for(int i = 0; i< numCourses; i++) {
            if(inDegree[i] ==0) {
                q.add(i); // putting all the independent courses in the queue
            }
        }
        if(q.isEmpty()) {
            return false; // if there are no independent courses found then, we should return false because all are dependent
        }

        int temp;
        int count =0;
        while(!q.isEmpty()) {
            temp = q.poll();
            count++; //we are increasing count when the courses are taken out of queue
            List<Integer> list = map.get(temp);
            if(list!=null && !list.isEmpty()) {
                for (int i : list) {
                    inDegree[i]--;// reducing the indegrees count for a dependent course when the independent course is
                    // taken from the queue
                    if (inDegree[i] == 0) {
                        q.add(i); // adding in the queue if the dependencies of a course becomes 0
                    }
                }
            }
        }
        return count == numCourses; // return if all the courses are added into the queue and completed
    }
}
