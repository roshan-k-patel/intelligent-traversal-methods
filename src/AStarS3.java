
import java.util.ArrayList;
import java.util.Arrays;

public class AStarS3{

    BoardNodePriorityQueue3 q;
    int nodesGenerated = 0;
    BoardNode lowestCost;

    public AStarS3(BoardNode start) {
        q = new BoardNodePriorityQueue3();

        lowestCost = start;
        System.out.println(Arrays.deepToString(start.grid).replace("], ", "]\n"));
        search(start);


    }

    public char[][] search(BoardNode start){

        ArrayList<BoardNode> list = start.generateChildren();

        nodesGenerated = nodesGenerated + list.size();

        if ((list.isEmpty())) {
        } else {
            for (BoardNode grid : list) {
                q.add(grid);
            }
        }

        if (start.checkGoalState()){
            System.out.println("Depth is: " + start.depth);
            System.out.println("Nodes Generated: " + nodesGenerated);
            return start.grid;
        }


        return search(q.pop());



    }
}

class BoardNodePriorityQueue3{

    ArrayList<BoardNode> queue;

    BoardNodePriorityQueue3(){
        queue = new ArrayList<>();
    }

    void add(BoardNode boardNode) {
        queue.add(boardNode);
    }

    BoardNode pop(){

        BoardNode lowest = queue.get(0);
        for (BoardNode x : queue){
            if (x.getCostPythagDistance() < lowest.getCostPythagDistance()){
                lowest = x;
            }
        }

        double pyD = lowest.rowsAndColumnsCorrectness;
        double depth = lowest.depth;

        System.out.println();
        System.out.println("PyD value is: " + pyD);
        System.out.println("Depth is: " + depth);
        System.out.println("Cost of next step is: " + (pyD + depth));
        System.out.println("Priority Queue size is: " + size());
        System.out.println(Arrays.deepToString(lowest.grid).replace("], ", "]\n"));

        queue.remove(lowest);

        return lowest;
    }

    int size(){
        return queue.size();
    }
}
