package assignment;

import java.util.HashSet;
import java.util.PriorityQueue;

public class AStar {

    public static void main(String[] args) {
        int[][] input = {
            {1, 2, 0},
            {4, 6, 3},
            {7, 5, 8}
        };
        AStar a = new AStar();

        String r = a.solve(input);

        System.out.println("Solution: " + r);
    }

    /**
     *
     * @param table initial state of the board
     * @return returns the string for the solution
     */
    public String solve(int[][] table) {

        String resultString = "";

        Node startNode = new Node(table, 0, 0, 0, -1, -1, "");
        startNode.findZeroPosition();

        PriorityQueue<Node> pq = new PriorityQueue<>();
        HashSet<String> visited = new HashSet<>();
        
        pq.add(startNode);
        visited.add(startNode.getBoardString());

        while (!pq.isEmpty()) {

            Node currentNode = pq.poll();

            if (currentNode.hValue == 0) {
                resultString = currentNode.direction;
                return resultString;
            }

            int[][] directions = {
                {0, -1},  // Left
                {0, 1},   // Right
                {-1, 0},  // Up
                {1, 0}    // Down
            };

            for (int[] direction : directions) {
                int newXNull = currentNode.x + direction[0];
                int newYNull = currentNode.y + direction[1];

                if (newXNull >= 0 && newXNull < currentNode.table.length &&
                    newYNull >= 0 && newYNull < currentNode.table[0].length) {

                    if (newXNull == currentNode.previousX && newYNull == currentNode.previousY) {
                        continue;
                    }

                    //Deep copy of current table and swap 
                    int[][] newTable = deepCopy(currentNode.table);
                    swap(newTable, currentNode.x, currentNode.y, newXNull, newYNull);

                    Node newChild = new Node(newTable, currentNode.depth + 1, newXNull, newYNull, currentNode.x, currentNode.y, currentNode.direction);

                    if (direction[0] == -1 && direction[1] == 0) {
                        newChild.direction += "U";
                    } else if (direction[0] == 1 && direction[1] == 0) {
                        newChild.direction += "D";
                    } else if (direction[0] == 0 && direction[1] == -1) {
                        newChild.direction += "L";
                    } else if (direction[0] == 0 && direction[1] == 1) {
                        newChild.direction += "R";
                    }

                    String boardString = newChild.getBoardString();

                    // Hasstate has already been visited
                    if (!visited.contains(boardString)) {
                        visited.add(boardString);
                        pq.add(newChild);
                    }
                }
            }
        }

        return "No solution found";
    }

    private void swap(int[][] table, int x1, int y1, int x2, int y2) {
        int temp = table[x1][y1];
        table[x1][y1] = table[x2][y2];
        table[x2][y2] = temp;
    }

    private int[][] deepCopy(int[][] original) {
        if (original == null) {
            return null;
        }

        int[][] result = new int[original.length][];

        for (int i = 0; i < original.length; i++) {
            result[i] = original[i].clone();
        }

        return result;
    }

    class Node implements Comparable<Node> {

        int[][] table;
        int hValue, depth, totalCost;
        int x, y;
        int previousX, previousY;
        String direction;

        public Node(int[][] tableFromParent, int depth, int x, int y, int previousX, int previousY, String direction) {
            this.table = tableFromParent;
            this.depth = depth;
            this.x = x;
            this.y = y;
            this.previousX = previousX;
            this.previousY = previousY;
            this.direction = direction;

            this.hValue = findHValue();
            this.totalCost = this.depth + this.hValue;
        }

        public int findHValue() {
            int manhattanDistance = 0;
            int rows = table.length;
            int cols = table[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    int value = table[i][j];

                    if (value != 0) {
                        int targetX = (value - 1) / cols; // row
                        int targetY = (value - 1) % cols; //column

                        manhattanDistance += Math.abs(i - targetX) + Math.abs(j - targetY);
                    }
                }
            }
            return manhattanDistance;
        }

        public void findZeroPosition() {
            int rows = table.length;
            int cols = table[0].length;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (table[i][j] == 0) {
                        x = i;  
                        y = j;  
                        return; 
                    }
                }
            }
        }

        public void printTable() {
            for (int[] row : table) {
                for (int value : row) {
                    System.out.print(value + " ");
                }
                System.out.println(); 
            }
        }

        public String getBoardString() {
            StringBuilder sb = new StringBuilder();
            for (int[] row : table) {
                for (int value : row) {
                    sb.append(value).append(",");
                }
            }
            return sb.toString();
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.totalCost, other.totalCost);
        }
    }
}