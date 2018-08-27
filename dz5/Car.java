package Java3.dz1.dz5;

import static Java3.dz1.dz5.Main.begin;
import static Java3.dz1.dz5.Main.ready;

public class Car implements Runnable {
    private static int CARS_COUNT;
    static {
        CARS_COUNT = 0;
    }
    private Race race;
    private int speed;
    private String name;
    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            ready++;
        } catch (Exception e) {
            e.printStackTrace();
        }
        while(true) {
            synchronized ((Object) ready){
                if(ready==4) {
                    synchronized ((Object) begin){
                        if(begin==0){
                            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
                            begin=1;
                        }
                    }
                    break;
                }
            }

        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
    }
}
