import java.util.Random;

public class Game {
    private Board board;
    private final static int DEFAULT_DIMENSIONS = 10;


    //Constructor for a game with randomly initialized board and default dimensions
    public Game(){
        this(DEFAULT_DIMENSIONS);
    }

    //Constructor for a game with randomly initialized board with set dimensions
    public Game(int dimensions){
        board = new Board(dimensions);

        //Toggle half of the cells alive randomly for a random start
        Random r = new Random();
        for(int i = 0; i < dimensions; i++){
            for(int j = 0; j < dimensions; j++){
                board.At(i,j, r.nextBoolean());
            }
        }
    }

    //Constructor for a game with a custom board
    public Game(Board board){
        this.board = board;
    }

    public void Play(int iterations, boolean showAllStages){
        //Print the initial board
        board.PrettyPrint();
        int runIterations = 0;
        while(runIterations < iterations){
            singleIteration();

            if(showAllStages){
                board.PrettyPrint();
                //Show iterations each 500ms
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    //Exit on possible error
                    System.exit(1);
                }
            }
            runIterations++;
        }
        //Print the finished board
        board.PrettyPrint();
    }

    //Play through a single iteration of the game of life
    private void singleIteration(){
        //Create a new board in which the updated values are set
        //to avoid prematurely updating cells
        Board newBoard = new Board(board.GetDimensions());

        //Go through each cell and check all the rules, and set the new value to the cell
        for(int i = 0; i < newBoard.GetDimensions(); i++){
            for(int j = 0; j < newBoard.GetDimensions(); j++){
                newBoard.At(i, j, checkCell(i, j));
            }
        }

        //Set the new board
        board = newBoard;
    }

    //Go through the rules and return the new status of the cell
    private boolean checkCell(int x, int y){
        int livingCellCount = getLivingNeighbours(x, y);

        //Check the rules in which case a cell lives
        //1) Any live cell with two or three live neighbours lives on to the next generation.
        if(board.At(x,y) &&  (livingCellCount == 2 || livingCellCount == 3)) return true;
        //2) Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
        else if(!board.At(x,y) && livingCellCount == 3) return true;
        //Otherwise the cell is dead
        else return false;
    }

    //Get the number living cells around cell x,y
    private int getLivingNeighbours(int x, int y){
        int livingCells = 0;
        //Check the surrounding cells
        for(int i = x-1; i <= x+1; i++){
            for(int j = y-1; j <= y+1; j++){
                //Check that the x,y is not over the limits
                if(i >= 0 && j >= 0 && i < board.GetDimensions() && j < board.GetDimensions()){
                    //If the cell is alive (and not the current cell), increment counter
                    if(board.At(i,j) && !(x == i && y == j)) {
                        livingCells++;
                    }
                }
            }
        }
        return livingCells;
    }
}
