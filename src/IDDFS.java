
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class IDDFS{

    LinkedList<BoardNode> q;
    int nodesGenerated = 0;
    int depthLimit = 1;
    BoardNode rootNode;

    public IDDFS(BoardNode start) {
        q = new LinkedList<>();

        rootNode = start;
        System.out.println(Arrays.deepToString(start.grid).replace("], ", "]\n"));
        search(start);


    }

    public char[][] search(BoardNode start){


            ArrayList<BoardNode> list = start.generateChildren();

        if (!(start.depth >= depthLimit)) {
            nodesGenerated = nodesGenerated + list.size();

            if ((list.isEmpty())) {
            } else {
                for (BoardNode grid : list) {
                    q.add(grid);
                }
            }
        } else {
            depthLimit = depthLimit +1;
            System.out.println("Depth limit increased to: " + depthLimit + " search restarting");
            System.out.println();
            q.clear();
            System.out.println(Arrays.deepToString(rootNode.grid).replace("], ", "]\n"));
            search(rootNode);
        }

        if (start.checkGoalState()){
            System.out.println("GOAL STATE REACHED!");
            System.out.println("current depth is: " + start.depth);
            System.out.println("depth limit is: " + depthLimit);
            System.out.println("Nodes Generated: " + nodesGenerated);
            return start.grid;
        }

        for (int i =0; i<list.size();i++ ) {
            System.out.println();
            System.out.println("Stack size is: " + q.size());
            System.out.println(Arrays.deepToString(q.getLast().grid).replace("], ", "]\n"));
            return search(q.pollLast());
        }

        return null;
    }
}
