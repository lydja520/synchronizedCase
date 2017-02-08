package com.ydc.test;

/**
 * Created by ydc on 17-2-8.
 * 采用四个同时启动的线程来模拟四个同时买票的用户
 */
public class Test {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        Thread thread1 = new Thread() {
            @Override
            public void run() {
                try {
                    ticket.buyTicket("小明");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    ticket.buyTicket("小张");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread3 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(200);
                    ticket.buyTicket("小王");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread thread4 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                    ticket.buyTicket("小杨");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();

    }

}

/**
 * 出现线程安全问题，是由于 判断ticket > 0，与ticket--，这两句必需是一个线程执行完后才能释放给
 * 另外一个线程访问，否则就会出现线程安全问题
 */
class Ticket {
    public static int ticket = 2; //临界资源

    public synchronized void buyTicket(String name) throws InterruptedException { //此方法如果不用synchronized的话，就会出现线程安全问题
        if (ticket > 0) {
            Thread.sleep(300);
            ticket--;
            System.out.println(name + "已经抢到一张票，" + "还剩" + ticket + "张票");
        } else {
            System.out.println(name + "没有抢到票");
        }
    }
}

