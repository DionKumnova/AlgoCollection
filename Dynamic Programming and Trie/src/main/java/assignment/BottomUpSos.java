package assignment;

import java.util.ArrayList;
import java.util.Arrays;

public class BottomUpSos {
    private int[] sequence;
    private Boolean[][] table;

    public BottomUpSos(int[] sequence) {
        this.sequence = sequence;
        init();
    }

    private void init() {
        int totalSum = Arrays.stream(sequence).sum();

        table = new Boolean[sequence.length][totalSum +1];


        for (Boolean[] t : table) {
            for (int x = 0; x < t.length; x++) {
                t[x] = false;
            }
        }

        table[0][sequence[0]] = true;
        if (sequence[0] <= totalSum) {
            table[0][sequence[0]] = true;  
        }

        for (int y = 1; y < sequence.length; y++) {

            for (int x = 0; x <= totalSum; x++) {


                if (table[y - 1][x]) {
                    table[y][x] = true;  
                }

                //if x is larger then the value of y, you can form a sum smaller then itself 
                //AND if [y - 1][x - sequence[y]] is true, that means that [y] [x + seq[x]]
                //has to be true, since its simply adding another unused value that can help
                //acchieve the sum.  
                if (x >= sequence[y] && table[y - 1][x - sequence[y]]) {
                    table[y][x] = true;  
                }
            }
        }
    }

    public int[] checkSum(int k) {

        //Collects values from squence and makes it returnable/comparable
        int sum = 0;
        for (int num : sequence) {
            sum += num;
        }

        //if k is larger then sum it can be true || if bottom right is false, k cant be formed
        if (k > sum|| !table[sequence.length - 1][k]) {
            return null;  
        }

        ArrayList<Integer> result = new ArrayList<>();
        int currentMaxSum = k;


        
        for (int i = sequence.length - 1; i >= 0; i--) {
            if (i > 0 && table[i - 1][currentMaxSum]) { //if true, we dont need the value of i to get to currentMaxsum
                continue;
            }

            //looks for the next values needed to reach currentMaxsum, should be 0 by the end
            result.add(sequence[i]); 
            currentMaxSum -= sequence[i];

            if (currentMaxSum == 0) {
                break;
            }
        }


        int[] subsequence = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            subsequence[i] = result.get(i);
        }
        return subsequence;    
    }
}