public class App {
    public static void main(String[] args) throws Exception {
        CaesarFrame cf = new CaesarFrame();
        cf.setVisible(true);
    }
    public static String caesarCode(String in, char offset) {
        offset -= 'A';
        String out = "";
        String proc_in = in.toUpperCase();
        for (int i = 0; i < in.length(); i++) {
            char cc = proc_in.charAt(i);
            cc += offset;
            if (cc < 91) {
                out += cc;
            } else {
                out += (char)(cc - (char)26);
            }
        }
        return out;
    }
}
