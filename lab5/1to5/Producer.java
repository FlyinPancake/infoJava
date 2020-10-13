
public class Producer extends Thread {
    private String txt;
    private Fifo fifo;
    int wait;

    public Producer(String a, Fifo f, int n) {
        txt = a;
        fifo = f;
        wait = n;
    }

    public void go() {
        int i = 0;
        while (true) {
            try {
                fifo.put((txt + " " + i));
                System.out.println("produced " + txt + " " + i + " " + System.currentTimeMillis());
                sleep(wait);
                i = i + 1;
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
    }     
    @Override
    public void run() {
        go();
    }
}