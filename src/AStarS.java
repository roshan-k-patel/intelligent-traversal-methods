
import java.util.ArrayList;
import java.util.Arrays;

public class AStarS{

    BoardNodePriorityQueue q;
    int nodesGenerated = 0;
    BoardNode lowestCost;

    public AStarS(BoardNode start) {
        q = new BoardNodePriorityQueue();

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

class BoardNodePriorityQueue{

    ArrayList<BoardNode> queue;

    BoardNodePriorityQueue(){
         queue = new ArrayList<>();
    }

    void add(BoardNode boardNode) {
        queue.add(boardNode);
    }

    BoardNode pop(){

        BoardNode lowest = queue.get(0);
        for (BoardNode x : queue){
            if (x.getCostWManhattan() < lowest.getCostWManhattan()){
                lowest = x;
            }
        }

        int mD = lowest.manhattanDistance;
        int depth = lowest.depth;

        System.out.println();
        System.out.println("Manhattan distance is: " + mD);
        System.out.println("Depth is: " + depth);
        System.out.println("Cost of next step is: " + (mD + depth));
        System.out.println("Priority Queue size is: " + size());
        System.out.println(Arrays.deepToString(lowest.grid).replace("], ", "]\n"));

        queue.remove(lowest);

        return lowest;
    }

    int size(){
       return queue.size();
    }
}
