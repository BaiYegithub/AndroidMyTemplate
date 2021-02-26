package app.vp.cn.profession.version2;

import java.util.List;

public class Main {
    /**
     * 女(冷)
     * {1, 1, 2, 3},
     * {1, 1, 4, 4},
     * {1, 1, 4, 4}
     * <p>
     * 女(寒冷)
     * {1, 1, 2, 2},
     * {1, 1, 3, 3},
     * {4, 4, 3, 3}
     * 女(凉)
     * {1, 1, 2, 2},
     * {1, 1, 2, 3},
     * {1, 1, 4, 4},
     */

    public static void main(String[] args) {
        FindNodeEdge findNodeEdge = new FindNodeEdge();
        int[][] matrix = {
                {1, 1, 2, 2},
                {1, 1, 3, 3},
                {4, 5, 0, 0}
        };
        Node[][] nodeMatrix = findNodeEdge.covert(matrix);

        List<List<int[]>> edges = findNodeEdge.findEdges(nodeMatrix);

        for (List<int[]> edge : edges) {
            System.out.println("=============================");
            for (int[] node : edge) {
                System.out.println("[" + node[0] + ", " + node[1] + "]");
            }
        }
    }


}
