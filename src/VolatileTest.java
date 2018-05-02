import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * volatile���������������
 * 
 * @author zzm
 */
public class VolatileTest {
	
    public static volatile int race = 0;
//    public static AtomicInteger race = new AtomicInteger(0);
    public static void increase() {
        race++;
    }
//    public static void increase() {
//    	race.incrementAndGet();
//    }
    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 10000; i++) {
                    	increase();	
                    }
                    System.out.println(Thread.currentThread().getName()+" add times: "+10000);
                }
            });
            threads[i].start();
        }
        // �ȴ������ۼ��̶߳�����
        while (Thread.activeCount() > 1)
            Thread.yield();

        System.out.println(race);
    }
}

