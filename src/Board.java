
public class Board {
    private int dimensions;
    //The board represented as an array of booleans, true: alive, false: dead
    private boolean[][] board;

    public Board(int dimensions){
        this.dimensions = dimensions;
        //Init the whole board with falses (dead cells)
        board = new boolean[this.dimensions][this.dimensions];
    }

    public int getDimensions(){
        return dimensions;
    }

    //Get the value at position x,y
    public boolean at(int x, int y){
        if(x >= dimensions || y >= dimensions || x < 0 || y < 0){
            System.out.println("Illegal arguments with " + x + "," + y);
            throw new IllegalArgumentException();
        }
        return board[x][y];
    }

    //Set the value at position x,y to status
    public void at(int x, int y, boolean status){
        if(x >= dimensions || y >= dimensions || x < 0 || y < 0){
            throw new IllegalArgumentException();
        }
        board[x][y] = status;
    }

    //Print the board in a readable manner
    public void prettyPrint(){
        for(int i = 0; i < dimensions; i++){
            System.out.println();
            for(int j = 0; j < dimensions; j++){
                System.out.print(board[j][i] ? "X " : "- ");
            }
        }
        System.out.println();
    }
}
