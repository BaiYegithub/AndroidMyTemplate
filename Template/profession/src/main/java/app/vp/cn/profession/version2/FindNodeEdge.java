package app.vp.cn.profession.version2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindNodeEdge {
    //贪心方式: 右,下,左,上
    private int[] dr = new int[]{0, 1, 0, -1};
    private int[] dc = new int[]{1, 0, -1, 0};

    /**
     * @return 返回边界的一系列坐标
     */
    public List<List<int[]>> findEdges(Node[][] matrix) {
        System.out.println(System.currentTimeMillis());
        HashSet<Integer> visitedValue = new HashSet<>();
        Visitor visitor = new Visitor();

        List<List<int[]>> res = new ArrayList<>();
        for (int r = 0; r < matrix.length; ++r) {
            for (int c = 0; c < matrix[0].length; ++c) {
                List<Integer> values = matrix[r][c].get();
                for (Integer value : values) {
                    if (value != 0 && !visitedValue.contains(value)) {
                        visitedValue.add(value);
                        List<int[]> list = new ArrayList<>();
                        findEdges(r, c, value, matrix.length, matrix[0].length, visitor, matrix, list);
                        res.add(list);
                    }
                }
            }
        }
        System.out.println(System.currentTimeMillis());
        return res;
    }

    public void findEdges(int r, int c, int value, int rowLimit, int colLimit, Visitor visitor, Node[][] matrix, List<int[]> res) {
        if (r < 0 || c < 0 || r >= rowLimit || c >= colLimit || visitor.contains(matrix[r][c], value) || !matrix[r][c].contain(value))
            return;

        visitor.addRecord(matrix[r][c], value);
        res.add(new int[]{r, c});

        for (int i = 0; i < 4; ++i) {
            int curR = r + dr[i];
            int curC = c + dc[i];

            if (curR < 0 || curR >= rowLimit || curC >= colLimit || curC < 0)
                continue;

            if (!matrix[curR][curC].contain(value))
                continue;

            if (!visitor.contains(matrix[curR][curC], value) && edge(curR, curC, value, matrix)) {
                findEdges(curR, curC, value, rowLimit, colLimit, visitor, matrix, res);
                break;
            }
        }
    }

    private boolean edge(int r, int c, int value, Node[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        if (r == 0 || c == 0 || r == rowSize - 1 || c == colSize - 1)
            return true;

        Node node = matrix[r][c];
        if (node.size() > 1)
            return true;

        for (int i = 0; i < 4; ++i) {
            if (!matrix[r + dr[i]][c + dc[i]].contain(value))
                return true;
        }

        return false;
    }

    public Node[][] covert(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        Node[][] nodeMatrix = new Node[rowSize + 1][colSize + 1];

        for (int r = 0; r < rowSize; ++r) {
            for (int c = 0; c < colSize; ++c) {
                fillNode(r, c, matrix, nodeMatrix);
            }
        }
        return nodeMatrix;
    }

    public void fillNode(int r, int c, int[][] matrix, Node[][] nodeMatrix) {
        int value = matrix[r][c];

        fillNode(r, c, value, nodeMatrix);
        fillNode(r, c + 1, value, nodeMatrix);
        fillNode(r + 1, c, value, nodeMatrix);
        fillNode(r + 1, c + 1, value, nodeMatrix);
    }

    public void fillNode(int r, int c, int value, Node[][] matrix) {
        if (matrix[r][c] == null)
            matrix[r][c] = new Node();

        matrix[r][c].add(value);
    }
}
