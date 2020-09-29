import java.io.*;

public class Main {

    private static File wd = new File(System.getProperty("user.dir"));

    private static boolean running = true;

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (running) {
            try {
                String[] iargs = br.readLine().split(" ");

                switch (iargs[0]) {
                    case "exit":
                        exit(iargs);
                        break;
                    case "pwd":
                        pwd(iargs);
                        break;
                    case "cd":
                        cd(iargs);
                        break;
                    case "ls":
                        ls(iargs);
                        break;
                    case "cp":
                        cp(iargs);
                        break;
                    case "head":
                        head(iargs);
                        break;
                }

            } catch (IOException exception) {
                System.out.println("brrr");
            }

        }
    }

    protected static void exit(String[] args) {
        running = false;
    }

    protected static void pwd(String[] args) {
        try {
            System.out.println(wd.getCanonicalPath());
        } catch (IOException exception) {
            System.out.println("Something went wrong");
        }
    }

    protected static void cd(String[] args) {
        if (args.length == 1) {
            wd = new File(System.getProperty("user.dir"));
            return;
        }
        File f;
        if (args[1].equals("..")) {
            f = wd.getParentFile();
        } else {
            f = new File(wd, args[1]);
        }
        if (f.exists()) {
            wd = f;
        } else {
            System.out.println("No such file or dir!");
        }
    }

    protected static void ls(String[] args) {
        File[] files = wd.listFiles();
        if (args.length == 1) {
            for (File f : files) {
                System.out.println(f.getName());
            }
        } else if (args[1].equals("-l")) {
            for (File f : files) {
                System.out.print(f.getName() + " " + f.length() + "b ");
                if (f.isFile()) {
                    System.out.println("f");
                } else {
                    System.out.println("d");
                }
            }
        }
    }

    protected static void cp(String[] args) {
        if (args.length < 3) {
            System.out.println("Nincs eleg argumentum");
            return;
        }
        File copied = new File(args[2]);
        File original = new File(args[1]);
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(original);
            os = new FileOutputStream(copied);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } catch (IOException ex) {
            System.out.println(ex.toString());
        } finally {
            try {
                is.close();
                os.close();
            } catch (Exception ioException) {
                System.out.println(ioException.toString());
            }
        }
    }

    protected static void head(String[] args) {
        int n = 10;
        if (args.length >=3) {
            n = Integer.parseInt(args[2]);
        }
        try {
            File toRead = new File(wd, args[args.length -1]);
            if (toRead.exists()) {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(toRead));
                for (int ii = 0; ii < n; ii++) {
                    String line = bufferedReader.readLine();
                    if (line == null) {
                        break;
                    } else {
                        System.out.println(line);
                    }
                }
            } else {
                System.out.println("Nincs ilyen file");
            }
        } catch (IOException ioException) {
            System.out.println(ioException.toString());
        }
    }

    protected static void 
}
