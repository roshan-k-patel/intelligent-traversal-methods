
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class DFS{

    LinkedList<BoardNode> q;
    int nodesGenerated = 0;

    public DFS(BoardNode start) {
        q = new LinkedList<>();

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

        for (int i =0; i<list.size();i++ ) {
            System.out.println();
            System.out.println("depth is: " + list.get(i).depth);
            System.out.println("'stack' size is " + q.size());
            System.out.println(Arrays.deepToString(q.getLast().grid).replace("], ", "]\n"));
            return search(q.pollLast());
        }

        return null;
    }
}
