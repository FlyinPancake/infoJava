import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

class NameComparator implements Comparator<Beer> {
    public int compare(Beer b1, Beer b2) {
        return b1.getName().compareTo(b2.getName());
    }
}

class StyleComparator implements Comparator<Beer> {
    public int compare(Beer b1, Beer b2) {
        return b1.getStyle().compareTo(b2.getStyle());
    }
}

class StrengthComperator implements Comparator<Beer> {
    public int compare(Beer b1, Beer b2) {
        double diff = b1.getStrength() - b2.getStrength();
        int rate;
        if (diff >= 0) {
            rate = (int)Math.ceil(diff); 
        } else {
            rate = (int)Math.floor(diff);
        }
        return rate;
    }
}

public class App {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        beerList = new ArrayList<Beer>();

        while (scn.hasNextLine()) {
            String[] intxt = scn.nextLine().split(" ");

            if (intxt[0].equals("exit")) {
                break;
            } else { 
                switch (intxt[0]) {
                    case "add":
                        add(intxt);
                        break;
                    case "list":
                        list(intxt);
                        break;
                    case "save":
                        save(intxt);
                        break;
                    case "load":
                        load(intxt);
                        break;
                    case "search":
                        search(intxt);
                        break;
                    case "find":
                        find(intxt);
                        break;
                    case "delete":
                        delete(intxt);
                        break;
                    default:
                    System.out.println("Rossz parancs");
                        break;
                }
            }
        }
        scn.close();
    }

    public static ArrayList<Beer> beerList;

    public static void add(String[] args) {
       Beer newBeer = new Beer(args[1],args[2],Double.parseDouble(args[3]));
       beerList.add(newBeer);
    }

    public static void list(String[] args) {
        if (args.length > 1) {
            switch (args[1]) {
                case "name":
                    Collections.sort(beerList, new NameComparator());
                    break;
                case "style":
                    Collections.sort(beerList, new StyleComparator());
                    break;
                case "strength":
                    Collections.sort(beerList, new StrengthComperator());
                    break;
                default:
                    System.out.println("Hibás sorrend paraméter!");
                    return;
            }
        }
        for (Beer beer : beerList) {
            System.out.println(beer.toString());
        }
    }

    public static void save(String[] args) { 
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(args[1]));
            out.writeObject(beerList);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void load(String[] args) {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(args[1]));
            beerList = (ArrayList)in.readObject();
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void search(String[] args) { 
        if (args.length > 1) {
            for (Beer beer : beerList) {
                if (beer.getName().equals(args[1])) {
                    System.out.println(beer);
                }
            }
        } else {
            System.out.println("Nincs eleg param");
        }
    }

    public static void find(String[] args) {
        if (args.length > 1) {
            for (Beer beer : beerList) { 
                if (beer.getName().contains(args[1])) { 
                    System.out.println(beer);
                }
            }
        } else {
            System.out.println("Nincs eleg param");
        }
    }

    public static void delete(String[] args) {
        if (args.length > 1) {
            Iterator<Beer> biter = beerList.iterator();
            while (biter.hasNext()) {
                Beer beer = biter.next();
                if (beer.getName().equals(args[1])) {
                    biter.remove();
                    return;
                }
            }
            System.out.println("Nincs ilyen elem");
        } else {
            System.out.println("Nincs eleg param");
        }
    }
}
