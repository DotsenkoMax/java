package Java3.dz1.dz4;

import java.io.File;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class Main {
    public  static String n="C";
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        task1();
        task2();
        MFU abc=new MFU();
        abc.PrintAndScan(50,true,true);
    }

    public static void task1() throws InterruptedException {
        Thread t1=new Thread(new Runnable() {
            @Override
            public void run() {
                while (n.length() <= 13) {
                    synchronized (n) {
                        if (n.charAt(n.length() - 1) == 'C') {
                            n += "A";
                        }
                    }
                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                while (n.length() <= 14) {
                    synchronized (n) {
                        if (n.charAt(n.length() - 1) == 'A') {
                            n += "B";
                        }
                    }
                }
            }
        });
        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                while (n.length() <= 15) {
                    synchronized (n) {
                        if (n.charAt(n.length() - 1) == 'B') {
                            n += "C";
                        }
                    }
                }
            }
        });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(n.substring(1,n.length()));
    }
    public static int count=1;
    public static void task2() throws FileNotFoundException, InterruptedException {
         Scanner  in=new Scanner(new FileInputStream("123/6.txt"));
        FileOutputStream out =new FileOutputStream(new File("123/7.txt"));

        Thread t1=new Thread(new Runnable() {
            public void run() {
                to: while(true) {
                    while (count != 1) {
                        if (count == 0) {
                            break to;
                        }
                    }
                    synchronized (in) {
                        if (in.hasNext()) {
                            System.out.println(in.next());
                            count = 2;
                        } else count = 0;
                    }
                }
        }});

        Thread t3=new Thread(new Runnable() {
            @Override
            public void run() {
                to: while (true) {
                    while (count != 3) {
                        if (count == 0) {
                            break to;
                        }
                    }
                    synchronized (in) {
                        if (in.hasNext()) {
                            System.out.println(in.next());
                            count = 1;
                        } else count = 0;
                    }

                }
            }
        });
        Thread t2=new Thread(new Runnable() {
            @Override
            public void run() {
                to: while(true) {
                    while (count != 2) {
                        if (count == 0) {
                            break to;
                        }
                    }
                    synchronized (in) {
                        if (in.hasNext()) {
                            System.out.println(in.next());
                            count = 3;
                        } else count = 0;
                    }
                }
        }});

        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();
    }
}

class MFU{


    public static int prpage;
    public static int scpage;
    public static void PrintAndScan(int page, boolean type1,boolean type2) throws InterruptedException {
        Thread t1=null,t2=null;
        if(type1){
            prpage=page;
            t1=new Thread(new Runnable() {
                @Override
                public void run() {
                    int i=0;
                    while(prpage-->0){
                        i++;
                        System.out.println("Отпечатано " +i + "страниц");
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

        if(type2){
            scpage=page;
            t2=new Thread(new Runnable() {
                @Override
                public void run() {
                    int i=0;
                    while(scpage-->0){
                        i++;
                        System.out.println("Отсканировано " +i + "страниц");
                        try {
                            Thread.sleep(5);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        if (t1 != null &&t2!=null) {
            t1.start();
            t2.start();
            t1.join();
            t2.join();
        }
        else if(t1!=null){
            t1.start();
            t1.join();

        }
        else if(t2!=null){
            t2.start();
            t2.join();

        }
    }
}
