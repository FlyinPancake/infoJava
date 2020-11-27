import java.util.Comparator;

public class BusStop {
    private String name;
    private String oldName;
    private String wheelchair;
    private boolean valid;
    private double lat;
    private double log;

    public BusStop() {
        valid = false;
    }

    public BusStop(double lat, double log) {
        valid = false;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOldName(String oldName) {
        this.oldName = oldName;
    }

    public void setWheelchair(String wheelchair) {
        this.wheelchair = wheelchair;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean getValid() {
        return valid;
    }

    public double getLat() {
        return lat;
    }

    public double getLog() {
        return log;
    }

    @Override
    public String toString() {
        return "Megálló:\n\tNév:" + name + " (" + oldName + ")\n\tKerekesszék: " + wheelchair;
    }

    public double dist1(double lat1, double lon1) {
        double lat2 = lat;
        double lon2 = log;
        double R = 6371000; // metres
        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);
        double dphi = phi2-phi1;
        double dl = Math.toRadians(lon2-lon1);
        double a = Math.sin(dphi/2) * Math.sin(dphi/2) +
                Math.cos(phi1) * Math.cos(phi2) *
                        Math.sin(dl/2) * Math.sin(dl/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        double d = R * c;
        return d;
    }



}
