public class App {
    public static void main(String[] args) throws Exception {
        Fifo fifo = new Fifo();
        Producer elso = new Producer("elso", fifo, 500);
        Consumer con = new Consumer(fifo, "cons", 1500);
        elso.start();
        con.start();
    }
}
