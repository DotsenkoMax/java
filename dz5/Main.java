package Java3.dz1.dz5;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static int ready=0;
    public static final int CARS_COUNT = 4;
    public static int tunnel=0;
    public static int begin=0;
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }
        Thread[] mass=new Thread[cars.length];
        for (int i = 0; i < cars.length; i++) {
            mass[i]=new Thread(cars[i]);
        }
        for (int i = 0; i <mass.length ; i++) {
            mass[i].start();
        }
        for (int i = 0; i <mass.length ; i++) {
            try {
                mass[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

