/**
 * Created by celineyelle on 2017-03-04.
 */
import java.math.BigDecimal;
import java.util.*;
import java.util.ArrayList;


public class StochasticSearch {

    private int n;
    private int N;
    private int numberOfCell;
    private Sudoku problem;


    public StochasticSearch(Sudoku sudoku) {
        this.problem = sudoku;
        this.n = sudoku.getN();
        this.N = sudoku.getNSquared();
        this.numberOfCell = sudoku.getNumberOfCell();
    }

    public Solution solve() {


        long lStartTime = new Date().getTime();

        problem = getOptimizedBoard(problem);


        long lEndTime = new Date().getTime();
        BigDecimal time = BigDecimal.valueOf(lEndTime - lStartTime).divide(BigDecimal.valueOf(1000000));
        return new Solution(problem, time);
    }

    /** This method takes the initial board and solves the 'obvious' cells to obtain a better partially filled board. **/
    public Sudoku getOptimizedBoard(Sudoku sudoku){

        ArrayList<Integer> numList = new ArrayList<Integer>() ;
        for(int i = 0; i<N; i++){
            numList.add(i+1);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //System.out.print("Row: " + i + "\t");
                //System.out.println("Col: " + j);

                //See if the cell is empty
                if(sudoku.getNumber(i,j) == 0) {
                    ArrayList<Integer> tempNumList = new ArrayList<Integer>();
                    Integer[] tempNumArray = numList.toArray(new Integer[tempNumList.size()]);
                    int[] sBox = new int[]{-1, -1};

                    //Find box the cell is in
                    for (int k = n; k <= N; k += n) {
                        if (i < k && sBox[0] == -1) {
                            sBox[0] = k - n;
                        }
                        if (j < k && sBox[1] == -1) {
                            sBox[1] = k - n;
                        }
                        if (sBox[0] != -1 && sBox[1] != -1) {
                            break;
                        }
                    }
                    //System.out.println("Box: [" + sBox[0] + "][" + sBox[1]+"]");

                    //Find possible values
                    for(int k =0; k< N; k++){
                        if (sudoku.isAllowed(i,j, tempNumArray[k])) {
                            tempNumList.add(tempNumArray[k]);
                        }
                    }

                    //If only one value is possible, assign this value to the cell and restart the outer
                    // loop.
                    if (tempNumList.size() == 1) {
                        System.out.println("Added number in cell: " + tempNumList.get(0));
                        sudoku.setNumber(i, j, tempNumList.get(0));
                        i = 0;
                        j = 0;
                    }
                }
            }
        }

        return sudoku;
    }

    public boolean isSolution(Sudoku sudoku) {
        return true;

    }
}
