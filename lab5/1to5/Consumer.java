public class Consumer extends Thread {
    Fifo fifo;
    String txt;
    int ii;

    public Consumer(Fifo f, String s, Integer n) {
        fifo = f;
        txt = s;
        ii = n;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("consumed " + txt+ " "+ fifo.get() + " " + System.currentTimeMillis());
                sleep(ii);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}