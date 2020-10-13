import java.util.LinkedList;

public class Fifo {
    LinkedList<String> data;

    public Fifo() {
        data = new LinkedList<String>();
    }

    synchronized public void put(String in) throws InterruptedException {
        while (data.size() > 10) {
            wait();
        }
        data.add(in);
        notify();
    }

    synchronized public String get() throws InterruptedException {
        while (data.size() == 0) {
            wait();
        }
        notify();
        return data.removeFirst();
    }
}
