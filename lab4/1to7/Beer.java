import java.io.Serializable;

public class Beer implements Serializable {
    private String name;
    private String style;
    private double strength;

    public Beer(String na, String st, double str) {
        name = na; 
        style = st;
        strength = str;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public double getStrength() {
        return strength;
    }

    @Override
    public String toString() {
        return name + " " + style + " " + strength;
    }
}
