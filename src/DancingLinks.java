import java.util.Date;

/**
 * Created by celineyelle on 2017-03-04.
 */
public class DancingLinks {
    private int n;
    private int nsquared;
    private int numberOfCells;
    private int [][] board;
    private int [][] solution;

    public DancingLinks (Sudoku sudoku) {
        this.n = sudoku.getN();
        this.nsquared = sudoku.getNSquared();
        this.numberOfCells = sudoku.getTotalNumberOfCells();
        this.board = sudoku.getCopyOfBoard();
    }


    public Solution solve () {
        long lStartTime = new Date().getTime();





        //algorithm magic!




        //for now set the solution equal to board
        solution = board;

        long lEndTime = new Date().getTime();
        long time = lEndTime - lStartTime;
        return new Solution(board, solution, time);
    }
}
