package ru.progwards.java2.lessons.recursion;

import java.util.List;

public class HanoiTower {
    int size; // size кольцами (1..size).
    int pos; // pos - номер начального штыря (0,1,2)
    boolean trace; // включает отладочную печать состояния игрового поля после каждого шага алгоритма (метода move)
    int[][] tower;


    public HanoiTower(int size, int pos) {
        this.size = size;
        this.pos = pos;
        int[] t0 = new int[size];
        int[] t1 = new int[size];
        int[] t2 = new int[size];
        for (int i = 0; i < size; i++) {
            t0[i] = i + 1;
            t1[i] = 0;
            t2[i] = 0;
        }
        if (pos == 0)
            tower = new int[][]{t0, t1, t2};
        else if (pos == 1)
            tower = new int[][]{t1, t0, t2};
        else
            tower = new int[][]{t2, t1, t0};
    }

    void incOrDec(int number, int napr) {
        for (int i = 0; i < size; i++) {
            tower[number][i] += napr;
        }
    }
    public void move(int from, int to){
        if (tower[from][size-1]!=0)
            moveTower(size,from,to, trace);
    }
    public void moveTower(int rings, int from, int to, boolean trace) {
        if (rings == 1) {
            moveRing(from, to);
            if (trace) print();
        } else {
            int tmp = 3 - from - to;

            moveTower(rings-1, from, tmp, trace);

            moveRing(from, to);
            if (trace) print();
            moveTower(rings-1, tmp, to, trace);
        }
    }


    void moveRing(int from, int to) { // перемещаем кольцо с from на to
        int i = size - 1;              // находим первое "не пустое" кольцо на from и помещаем в первое "пустое" поле to
        int k = 0;
        while (tower[to][i] != 0) {
            i--;
        }
        while (tower[from][k] == 0) {
            k++;
        }
        tower[to][i] = tower[from][k];
        tower[from][k] = 0;
    }

    void print() {
        for (int i = 0; i < size; i++) {
            if (tower[0][i] != 0)
                System.out.printf("<%03d>", tower[0][i]);
            else
                System.out.print("  I  ");
            if (tower[1][i] != 0)
                System.out.printf(" <%03d>", tower[1][i]);
            else
                System.out.print("   I  ");
            if (tower[2][i] != 0)
                System.out.printf(" <%03d>\n", tower[2][i]);
            else
                System.out.print("   I  \n");
        }
        System.out.println("=================");
    }

    void setTrace(boolean on) {
        this.trace = on;
    }

    public static void main(String[] args) {
        HanoiTower ht1 = new HanoiTower(3, 1);

        ht1.print();
        ht1.setTrace(false);
        ht1.move(1,2);
        ht1.print();

    }

}
