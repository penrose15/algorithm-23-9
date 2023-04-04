package org.example.pgm;

public class pgm_42898_hsj {
    /*
    dfs 풀이방법, 그러나 시간초과

    int[] dx = {1,0};
    int[] dy = {0,1};
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m][n];
        for (int[] puddle : puddles) {
            int x = puddle[0]-1;
            int y = puddle[1]-1;
            dp[x][y] = -1;
        }

        dfs(m, n, dp, 0,0);
        return result;
    }

    int result = 0;
    public void dfs(int m, int n, int[][] dp, int x, int y) {
        if(x == m-1 && y == n-1) {
            result += 1;
            return;
        }

        for(int i = 0; i< dx.length; i++) {
            int tx = x + dx[i]; int ty = y + dy[i];
            if(tx >= 0 && tx < m && ty >= 0 && ty<n) {
                if(dp[tx][ty] != -1) {
                    dp[tx][ty] = 1;
                    dfs(m, n, dp, tx, ty);
                }
            }
        }

    }*/
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        for (int[] puddle : puddles) {
            int y = puddle[0]-1;
            int x = puddle[1]-1;
            dp[x][y] = -1;
        }

        dp[0][0] = 1;

        for(int i = 0; i<n; i++) {
            for(int j = 0; j<m; j++) {
                if(dp[i][j] != -1) {
                    if(i >= 1 && dp[i-1][j] != -1) {
                        dp[i][j] += dp[i-1][j];
                    }
                    if(j >= 1 && dp[i][j-1] != -1) {
                        dp[i][j] += dp[i][j-1];
                    }
                    if(dp[i][j] > 1_000_000_007) {
                        dp[i][j] %= 1_000_000_007;
                    }
                }

            }
        }
        return dp[n-1][m-1];
    }
}
