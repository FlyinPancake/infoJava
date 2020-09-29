import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if (args.length == 1) {
            Scanner scn = new Scanner(System.in);
            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if(line.matches(args[0])) {
                    System.out.println(line);
                }
            }
        } else if (args.length == 6) {
            String input = null;
            String output = null;
            String pattern = "";
            for (int i = 0; i < args.length; i++) {
                if ((i+1 < args.length) && args[i].equals("-i")) {
                    i++;
                    input = args[i];
                } else if ((i+1 < args.length) && args[i].equals("-o")) {
                    i++;
                    output = args[i];
                } else if ((i+1 < args.length) && args[i].equals("-p")) {
                    i++;
                    pattern = args[i];
                }
            } 
            try {
                Scanner scn = new Scanner(new FileInputStream(input));
                FileWriter ofw = new FileWriter(new File(output));
                while (scn.hasNextLine()) {
                    String line = scn.nextLine();
                    if (line.matches(pattern)) {
                        ofw.write(line);
                        ofw.write('\n');
                    }
                }
                ofw.close();
            } catch (IOException fne) {
                System.out.println(fne.toString());
            }
            
        }
    }
}