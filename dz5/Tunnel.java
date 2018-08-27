package Java3.dz1.dz5;

import java.sql.SQLOutput;

import static Java3.dz1.dz5.Main.CARS_COUNT;
import static Java3.dz1.dz5.Main.tunnel;

public class Tunnel extends Stage {
    public Tunnel() {
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }
    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                while(true){
                    synchronized ((Object) tunnel){
                        if(tunnel<CARS_COUNT/2){
                            System.out.println(c.getName() + " начал этап: " + description);
                            tunnel++;
                            break;
                        }
                    }
                }
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                synchronized ((Object) tunnel){
                    System.out.println(c.getName() + " закончил этап: " + description);
                    tunnel--;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
