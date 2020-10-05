package Lesson4_Thread;

public class ThreadHW {
    private static final Object mon = new Object();
    private volatile static char curSym = 'A';

    public static void main(String[] args) {

        new Thread(()->{
            synchronized (mon){
                try {
                    for (int i = 0; i < 5; i++) {
                        while (curSym != 'A') {
                            mon.wait();
                        }
                        System.out.print('A');
                        curSym = 'B';
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (mon){
                try {
                    for (int i = 0; i < 5; i++) {
                        while (curSym != 'B') {
                            mon.wait();
                        }
                        System.out.print('B');
                        curSym = 'C';
                        mon.notify();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(()->{
            synchronized (mon){
                try {
                    for (int i = 0; i < 5; i++) {
                        while (curSym != 'C') {
                            mon.wait();
                        }
                        System.out.print('C');
                        curSym = 'A';
                        mon.notifyAll();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}