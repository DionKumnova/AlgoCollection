package assignment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TopDownSos {
    private int[] sequence;
    private Boolean[][] table;
    
    /**
     * 
     * @param sequence an array of positive integers 
     */
    public TopDownSos(int[] sequence) {
        this.sequence = sequence;
        init();
    }

    /**
     * fills the first column and row
     */
    private void init() {
        int totalSum = Arrays.stream(sequence).sum();
        table = new Boolean[sequence.length][totalSum + 1];

        for (int y = 0; y < sequence.length; y++) {
            table[y][0] = true;  // Any sum of 0 is achievable by not selecting elements
        }
        if (sequence[0] <= totalSum) {
            table[0][sequence[0]] = true;  // If the first element can form a sum
        }
    }

    public boolean topDownRec(int y, int k) {
        System.out.println("topDownRec called for y = " + y + ", k = " + k);

        if (k == 0) return true;

        if (y == 0) return k == sequence[y];

        if (table[y][k] != null) return table[y][k];

        Boolean notNeeded = topDownRec(y - 1, k);

        Boolean needed = (k >= sequence[y]) && topDownRec(y - 1, k - sequence[y]);

        table[y][k] = notNeeded || needed;

        return table[y][k];
    }
    
    /**
     * Call a recursive function that fills in the necessary parts of the table.
     * @param k the target sum 
     * @return an int array of the subsequence that can make the number k, return null if it can't be made
     */
    public int[] checkSum(int k) {
        if (!topDownRec(sequence.length - 1, k)) {
            return null; 
        }

        List<Integer> result = new ArrayList<>();
        int y = sequence.length - 1;

        while (k > 0 && y >= 0) {
            if (y == 0) {

                if (k == sequence[y]) {
                    result.add(sequence[y]);
                }
                break;
            }


            if (table[y - 1][k] != null && table[y - 1][k]) {
                y--;


            } else {

                result.add(sequence[y]);
                k -= sequence[y];
                y--;
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    } 
}