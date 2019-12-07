package com.example.myapplication;

public class ColorTiles {

    private boolean[][] map;
    private boolean[][] result;

    public ColorTiles(boolean[][] map) {
        this.map = new boolean[4][4];
        this.result = new boolean[4][4];

        copyArr(map, this.map);
    }

    private void copyArr(boolean[][] from, boolean[][] to) {
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                to[i][j] = from[i][j];
    }

    private int count(boolean[][] steps) {
        int count = 0;
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                if (steps[i][j]) count++;
        return count;
    }

    private void mapChangeColor(int i, int j) {
        for (int q = 0; q < 4; q++) {
            map[i][q] = !map[i][q];
            map[q][j] = !map[q][j];
        }
        map[i][j] = !map[i][j];
    }

    private boolean hasWon() {
        int count = count(map);
        if (count == 16 || count == 0)
            return true;
        return false;
    }

    public boolean[][] compute() {
        int max = 65535;
        if (hasWon())
            return result;

        for (int i = 0; i < max; i++) {
            int pos = 0;
            for (int p = 1, j = 0; ; p *= 2, j++)
                // если i % 2^(j+1) == 2^j - 1
                if (i % (p*2) == p - 1) {
                    pos = j;
                    break;
                }
            result[pos / 4][pos % 4] = !result[pos / 4][pos % 4];
            mapChangeColor(pos /4, pos % 4);

            if (hasWon())
                break;
        }

        if (count(result) > 8) {
            System.out.println("YA");
            for (int i = 0; i < 4; i++)
                for (int j = 0; j < 4; j++)
                    result[i][j] = !result[i][j];
        }
        return result;
    }
}
