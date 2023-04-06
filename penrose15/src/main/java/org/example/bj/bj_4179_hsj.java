package org.example.bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class bj_4179_hsj {
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        boolean[][] visited = new boolean[R][C];
        char[][] fire_map = new char[R][C];

        Queue<Fire> fires= new LinkedList<>();
        for(int i = 0; i<R; i++) {
            char[] chars = br.readLine().toCharArray();
            for(int j = 0; j< chars.length; j++) {
                map[i][j] = chars[j];

                if(map[i][j] == 'F') {
                    fires.add(new Fire(i,j));
                    fire_map[i][j] = 'F';
                }
                if(map[i][j] == '#') {
                    fire_map[i][j] = '#';
                }
            }
        }

        Queue<Jihun> jihuns = new LinkedList<>();

        Jihun jihun = new Jihun(0,0);
        for(int i = 0; i<R; i++) {

            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'J') {
                    jihun = new Jihun(i, j);
                }
            }
        }
        jihuns.add(jihun);

        int count = 0;

        outer :
        while (true) {
            count++;

            int fireCount = fires.size();
            while (fireCount-- >0) {
                Fire f = fires.poll();
                int fx = f.x; int fy = f.y;
                for(int i = 0; i<dx.length; i++) {
                    int tx = fx + dx[i]; int ty = fy + dy[i];
                    if(tx >= 0 && tx <R && ty >= 0 && ty < C) {
                        if(fire_map[tx][ty] != 'F' && fire_map[tx][ty] != '#') {
                            fire_map[tx][ty] = 'F';
                            fires.add(new Fire(tx, ty));
                        }
                    }
                }
            }

            int jCount = jihuns.size();
            while (jCount-- >0) {
                Jihun jihun1 = jihuns.poll();
                int x = jihun1.x; int y = jihun1.y;

                for(int i = 0; i< dx.length; i++) {
                    int tx = x + dx[i]; int ty = y + dy[i];
                    if(tx < 0 || tx >= R || ty < 0 || ty >= C) {
                        System.out.println(count);
                        return;
                    }
                    if(fire_map[tx][ty] != 'F' && map[tx][ty] != '#') {
                        if(!visited[tx][ty]) {
                            visited[tx][ty] = true;
                            jihuns.add(new Jihun(tx, ty));
                        }
                    }
                }
            }
            if(jihuns.isEmpty()) {
                System.out.println("IMPOSSIBLE");
                return;
            }
        }

    }



    public static class Jihun {
        int x;
        int y;

        public Jihun(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Fire {
        int x;
        int y;

        public Fire(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
