package assignment;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Flow {

    private int[][] capacity;           
    private int[][] flow;               
    private int[][] residualCapacity;   
    private int[] parent;    

    private int nodeCount;              
    private int source;                 
    private int sink;                   
    private int maxFlow;                

    public Flow(int[][] array) {
        this.nodeCount = array.length;
        this.source = 0;
        this.sink = nodeCount - 1;
        this.capacity = new int[nodeCount][nodeCount];
        this.flow = new int[nodeCount][nodeCount];
        this.residualCapacity = new int[nodeCount][nodeCount];
        this.parent = new int[nodeCount];
        this.maxFlow = 0;


        for (int i = 0; i < nodeCount; i++) {
            System.arraycopy(array[i], 0, capacity[i], 0, nodeCount);
            System.arraycopy(array[i], 0, residualCapacity[i], 0, nodeCount);
        }
    }

    /**
     * Solves the maximum flow problem using the Edmonds-Karp algorithm.
     * @return The maximum flow from source to sink.
     */
    public int solve() {

        while (bfs()) {

            int pathFlow = Integer.MAX_VALUE;
            int intermediateSink = sink;

            while (intermediateSink != source) {

                int u = parent[intermediateSink];
                pathFlow = Math.min(pathFlow, residualCapacity[u][intermediateSink]);
                intermediateSink = u;
            }

            intermediateSink = sink;

            while (intermediateSink != source) {

                int u = parent[intermediateSink];
                flow[u][intermediateSink] += pathFlow;
                flow[intermediateSink][u] -= pathFlow;  
                residualCapacity[u][intermediateSink] -= pathFlow;
                residualCapacity[intermediateSink][u] += pathFlow;
                intermediateSink = u;
            }
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    
    private boolean bfs() {

        boolean[] visited = new boolean[nodeCount];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;
        parent[source] = -1;

        while (!queue.isEmpty()) {
            
            int u = queue.poll();

            for (int v = 0; v < nodeCount; v++) {

                if (!visited[v] && residualCapacity[u][v] > 0) {
                    queue.add(v);
                    parent[v] = u;
                    visited[v] = true;

                    if (v == sink) {
                        return true; 
                    }
                }
            }
        }

        return false;  
    }

    /**
     * Returns the minimal cut after the maximum flow has been found.
     * @return A list of vertices on the source side of the minimal cut.
     */
    public List<Integer> cut() {

        boolean[] visited = new boolean[nodeCount];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int u = queue.poll();

            for (int v = 0; v < nodeCount; v++) {
                if (!visited[v] && residualCapacity[u][v] > 0) {
                    queue.add(v);
                    visited[v] = true;
                }
            }
        }

        // All vertices that can be reached from source of rest
        List<Integer> cutList = new ArrayList<>();

        for (int i = 0; i < nodeCount; i++) {
            if (visited[i]) {
                cutList.add(i);
            }
        }

        return cutList;
    }

   
    public void printPath() {
        System.out.println("Flow over edge");
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (capacity[i][j] > 0) {
                    System.out.println(i + " -> " + j + ": " + flow[i][j] + "/" + capacity[i][j]);
                }
            }
        }
    }

    public static void main(String[] args) {

        int[][] array = {
            {0, 5, 1, 0, 0},
            {0, 0, 1, 4, 0},
            {0, 2, 0, 0, 6},
            {0, 0, 1, 0, 1},
            {0, 0, 0, 0, 0}
        };

        Flow flowSolver = new Flow(array);

        System.out.println("Max Flow: " + flowSolver.solve());
        System.out.println("Cut: " + flowSolver.cut());        
    }
}