package com.example.backgroundtask1;


import android.app.Activity;

class MyThread1 extends  Thread{
    @Override
    public void run(){

        try {
            System.out.println("Thread of MyThread 1 class");
            MyThread1.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread2 extends  Thread{
    @Override
    public void run(){

        try {
            System.out.println("Thread of MyThread 2 class");
            MyThread1.sleep(600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread3 extends  Thread{
    @Override
    public void run(){
        System.out.println("Thread of MyThread 3 class");
    }
}

public class ThreadsActivity {
    public void main(){
        MyThread1 myTh1 = new MyThread1();
        myTh1.start();

        MyThread2 myTh2 = new MyThread2();
        myTh2.start();
        try {
            myTh2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        MyThread3 myTh3 = new MyThread3();
        myTh3.start();
        try {
            myTh3.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

