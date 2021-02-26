package app.vp.cn.profession;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class FindEdge {
    int[] dx = new int[]{-1, 1, 0, 0, -1, -1, 1, 1};
    int[] dy = new int[]{0, 0, -1, 1, -1, 1, 1, -1};

    /**
     * 输入一个matrix将左上角的岛的边界输出
     */
    public List<List<int[]>> findEdge(int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        boolean[][] visited = new boolean[rowSize][colSize];
        HashSet<Integer> visitedValue = new HashSet<>();
        List<List<int[]>> res = new ArrayList<>();

        for (int i = 0; i < rowSize; ++i) {
            for (int j = 0; j < colSize; ++j) {
                if (visitedValue.contains(matrix[i][j]))
                    continue;

                visitedValue.add(matrix[i][j]);
                List<int[]> list = new ArrayList<>();
                findEdge(i, j, matrix.length, matrix[0].length, visited, matrix, list);
                res.add(list);
            }
        }

        return res;
    }

    public void findEdge(int r, int c, int rowLimit, int colLimit, boolean[][] visited, int[][] matrix, List<int[]> res) {
        if (r < 0 || c < 0 || r >= rowLimit || c >= colLimit || visited[r][c])
            return;

        visited[r][c] = true;
        res.add(new int[]{r, c});

        for (int i = 0; i < 8; ++i) {
            int curR = r + dx[i];
            int curC = c + dy[i];

            if (curR < 0 || curR >= rowLimit || curC >= colLimit || curC < 0)
                continue;

            if (matrix[r][c] != matrix[curR][curC])
                continue;

            if (!visited[curR][curC] && edge(curR, curC, matrix)) {
                findEdge(curR, curC, rowLimit, colLimit, visited, matrix, res);
                break;
            }
        }
    }

    private boolean edge(int r, int c, int[][] matrix) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        if (r == 0 || c == 0 || r == rowSize - 1 || c == colSize - 1)
            return true;

        int value = matrix[r][c];
        for (int i = 0; i < 8; ++i) {
            if (value != matrix[r + dx[i]][c + dy[i]])
                return true;
        }

        return false;
    }
}
