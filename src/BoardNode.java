import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class BoardNode {

    public char[][] grid;
    public int rowsAndColumns;
    public intPair agent;
    public BoardNode parent;
    public int depth = 0;
    public int manhattanDistance;
    public int rowsAndColumnsCorrectness;
    public double pythagDistance;



    public BoardNode(int rowsAndColumns) {
        this.rowsAndColumns = rowsAndColumns;
        grid = new char[rowsAndColumns][rowsAndColumns];
        grid[rowsAndColumns - 4][rowsAndColumns - 3] = '!';
        grid[rowsAndColumns - 1][rowsAndColumns - 3] = 'C';
        grid[rowsAndColumns - 2][rowsAndColumns - 3] = 'B';
        grid[rowsAndColumns - 4][rowsAndColumns - 2] = 'A';
        checkGoalState();
        agent = new intPair(rowsAndColumns - 4, rowsAndColumns - 3);
        parent = null;
        manhattanDistance();
        rowsAndColumnsCorrectness();
        pythagDistance();
    }

    public BoardNode(BoardNode boardNode){
        this.rowsAndColumns = boardNode.rowsAndColumns;

        this.parent = boardNode;

        grid = new char[rowsAndColumns][rowsAndColumns];

        for(int i = 0; i< boardNode.grid.length; i++){
            for(int j = 0; j< boardNode.grid[0].length; j++){
                grid[i][j]= boardNode.grid[i][j];
            }
        }

       this.agent = new intPair(boardNode.agent.getRow(),boardNode.agent.getColumn());
        manhattanDistance();
        rowsAndColumnsCorrectness();
        pythagDistance();
        this.depth = parent.depth + 1;
    }

    public void printGrid() {
        System.out.println(Arrays.deepToString(this.grid).replace("], ", "]\n"));

    }

    public void printChildren() {
        for (BoardNode g : generateChildren()) {
            System.out.println(Arrays.deepToString(g.grid).replace("], ", "]\n"));
        }
    }

    // Method that checks if the board is in a goal state i.e. that A B C are stacked on top
    // of each other with A at the top and C at the bottom
    public boolean checkGoalState() {
        return grid[rowsAndColumns - 3][rowsAndColumns - 3] == 'A'
                && grid[rowsAndColumns - 2][rowsAndColumns - 3] == 'B'
                && grid[rowsAndColumns - 1][rowsAndColumns - 3] == 'C';

    }

    public Boolean movement(char direction) {
        if (direction == ('u') && agent.getRow() != 0) {
            this.grid[agent.getRow()][agent.getColumn()] = this.grid[agent.getRow() - 1][agent.getColumn()];
            this.grid[agent.getRow() - 1][agent.getColumn()] = '!';
            //   this.printGrid();
            agent.setRow(agent.getRow() - 1);
            agent.setColumn(agent.getColumn());
            manhattanDistance();
            rowsAndColumnsCorrectness();
            pythagDistance();
            return true;
        } else if (direction == ('d') && agent.getRow() < (rowsAndColumns - 1)) {
            this.grid[agent.getRow()][agent.getColumn()] = this.grid[agent.getRow() + 1][agent.getColumn()];
            this.grid[agent.getRow() + 1][agent.getColumn()] = '!';
            //      this.printGrid();
            agent.setRow(agent.getRow() + 1);
            agent.setColumn(agent.getColumn());
            manhattanDistance();
            rowsAndColumnsCorrectness();
            pythagDistance();
            return true;
        } else if (direction == ('l') && agent.getColumn() != 0) {
            this.grid[agent.getRow()][agent.getColumn()] = this.grid[agent.getRow()][agent.getColumn() - 1];
            this.grid[agent.getRow()][agent.getColumn() - 1] = '!';
            //     this.printGrid();
            agent.setRow(agent.getRow());
            agent.setColumn(agent.getColumn() - 1);
            manhattanDistance();
            rowsAndColumnsCorrectness();
            pythagDistance();
            return true;

        } else if (direction == ('r') && agent.getColumn() < rowsAndColumns - 1) {
            this.grid[agent.getRow()][agent.getColumn()] = this.grid[agent.getRow()][agent.getColumn() + 1];
            this.grid[agent.getRow()][agent.getColumn() + 1] = '!';
            //    this.printGrid();
            agent.setRow(agent.getRow());
            agent.setColumn(agent.getColumn() + 1);
            manhattanDistance();
            rowsAndColumnsCorrectness();
            pythagDistance();
            return true;
        }


        return false;
    }


    public ArrayList<BoardNode> generateChildren() {
        ArrayList<BoardNode> children = new ArrayList<>();
        BoardNode child1 = new BoardNode(this);
        if (child1.movement('u')) {
            children.add(child1);
         //   System.out.println(Arrays.deepToString( children.get(0).grid).replace("], ", "]\n"));

        }

        BoardNode child2 = new BoardNode(this);
        if (child2.movement('d')) {
            children.add(child2);
        }

        BoardNode child3 = new BoardNode(this);
        if (child3.movement('l')) {
            children.add(child3);

        }

        BoardNode child4 = new BoardNode(this);
        if (child4.movement('r')) {
            children.add(child4);
        }

        Collections.shuffle(children);

        return children;
    }

    public void manhattanDistance(){
    int rowA = 0;
    int columnA = 0;
    int rowB = 0;
    int columnB = 0;
    int rowC = 0;
    int columnC = 0;
        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if (grid[i][j] == 'A'){
                   rowA = Math.abs(i - (rowsAndColumns-3));
                   columnA = Math.abs(j - (rowsAndColumns-3));

                }

                if (grid[i][j] == 'B'){
                    rowB = Math.abs(i - (rowsAndColumns-2));
                    columnB = Math.abs(j- (rowsAndColumns-3));
                }

                if (grid[i][j] == 'C'){
                    rowC = Math.abs(i-(rowsAndColumns-1));
                    columnC = Math.abs(j - (rowsAndColumns-3));
                }

            }
        }

        this.manhattanDistance = (rowA +columnA + rowB + columnB + rowC + columnC);


    }

    public void rowsAndColumnsCorrectness(){
        int rowA = 1;
        int columnA = 1;
        int rowB = 1;
        int columnB = 1;
        int rowC = 1;
        int columnC = 1;
        for(int i = 0; i< grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][rowsAndColumns - 3] == 'A') {
                    columnA = 0;
                }

                if (grid[rowsAndColumns - 3][j] == 'A') {
                    rowA = 0;

                }

                if (grid[i][rowsAndColumns - 3] == 'B') {
                    columnB = 0;
                }

                if (grid[rowsAndColumns - 2][j] == 'B') {
                    rowB = 0;

                }

                if (grid[i][rowsAndColumns - 3] == 'C') {
                    columnC = 0;
                }

                if (grid[rowsAndColumns - 1][j] == 'C') {
                    rowC = 0;

                }

                this.rowsAndColumnsCorrectness = (rowA +columnA + rowB + columnB + rowC + columnC);


            }
        }
    }

    public void pythagDistance(){
        int rowA = 0;
        int columnA = 0;
        int rowB = 0;
        int columnB = 0;
        int rowC = 0;
        int columnC = 0;
        double pythagA =0;
        double pythagB =0;
        double pythagC = 0;
        double totalA;
        double totalB;
        double totalC;
        double squaredA =0;
        double squaredB =0;
        double squaredC =0;


        for(int i = 0; i< grid.length; i++){
            for(int j = 0; j< grid[0].length; j++){
                if (grid[i][j] == 'A'){
                    rowA = (i - (rowsAndColumns-3));
                    columnA = (j - (rowsAndColumns-3));
                    totalA = (double)(rowA + columnA);
                    squaredA = Math.pow(totalA,2);
                    pythagA = Math.sqrt(squaredA);
                }

                if (grid[i][j] == 'B'){
                    rowB = (i - (rowsAndColumns-2));
                    columnB = (j- (rowsAndColumns-3));
                    totalB = (double)(rowB + columnB);
                    squaredB = Math.pow(totalB,2);
                    pythagB = Math.sqrt(squaredB);
                }

                if (grid[i][j] == 'C'){
                    rowC = (i-(rowsAndColumns-1));
                    columnC = (j - (rowsAndColumns-3));
                    totalC = (double)(rowC + columnC);
                    squaredC = Math.pow(totalC,2);
                    pythagC = Math.sqrt(squaredC);

                }

            }
        }

        this.pythagDistance = (pythagA + pythagB + pythagC);


    }

    public int getCostWManhattan(){
        return (this.depth + this.manhattanDistance);
    }

    public int getCostWRACC(){
        return (this.depth + this.rowsAndColumnsCorrectness);
    }

    public double getCostPythagDistance(){
        return (this.depth + this.pythagDistance);
    }



}

class intPair {
    private int row;
    private int column;

    public intPair(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setRow(int row) {
        this.row = row;

    }

    public void setColumn(int column) {
        this.column = column;
    }
}
