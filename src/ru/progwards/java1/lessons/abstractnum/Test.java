package ru.progwards.java1.lessons.abstractnum;

public class Test {
    public static void main(String[] args) {
        IntNumber test1= new IntNumber(3);
        Figure3D cube1 = new Cube(test1);
        System.out.println(cube1.volume());

        DoubleNumber test2 = new DoubleNumber(3);
        Figure3D cube2 = new Cube(test2);
        System.out.println(cube2.volume());

        Figure3D pyramid1 = new Pyramid(test1);
        System.out.println(pyramid1.volume());

        Figure3D pyramid2 = new Pyramid(test2);
        System.out.println(pyramid2.volume());

    }
}
